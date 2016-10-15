package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jbox2d.common.Vec2;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Nikita on 09.10.2016.
 */
public class Config {

    public int VELOCITY_ITERATIONS;
    public int POSITION_ITERATIONS;
    public float FRAME_RATE;
    public Vec2 GRAVITY;
    public Vector2i WORLD_SIZE;
    public Vector2i SCREEN_SIZE;
    public boolean DEBUG_DRAW;
    public boolean FULLSCREEN;
    public Vector2f CANNON_POS;
    public Map<String, Color4> COLORS;

    private JsonObject m_jsonRoot;

    public Config() {
        final String projectDir = System.getProperty("user.dir");
        final JsonParser jsonParser = new JsonParser();
        final String json = FileUtils.getWholeText(projectDir + "/src/config.json");
        m_jsonRoot = jsonParser.parse(json).getAsJsonObject();
        initWorld(m_jsonRoot);
        initScreen(m_jsonRoot);
        initColors(m_jsonRoot);
    }

    public JsonObject getJsonRoot() {
        return m_jsonRoot;
    }

    private void initWorld(JsonObject object) {
        JsonObject world = object.getAsJsonObject("World");
        final JsonArray gravity = world.getAsJsonArray("gravity");
        final float xGravity = gravity.get(0).getAsFloat();
        final float yGravity = gravity.get(1).getAsFloat();
        GRAVITY = new Vec2(xGravity, yGravity);
        VELOCITY_ITERATIONS = world.get("velocity_iterations").getAsInt();
        POSITION_ITERATIONS = world.get("position_iterations").getAsInt();
        FRAME_RATE = 1 / world.get("frame_rate").getAsFloat();

        JsonObject cannon = object.getAsJsonObject("Cannon");
        JsonArray pos = cannon.getAsJsonArray("pos");
        CANNON_POS = new Vector2f(pos.get(0).getAsFloat(), pos.get(1).getAsFloat());
    }

    private void initScreen(JsonObject object) {
        final JsonObject screenObj = object.getAsJsonObject("Screen");
        final JsonObject worldObj = object.getAsJsonObject("World");
        DEBUG_DRAW = worldObj.get("debug_draw").getAsBoolean();
        FULLSCREEN = screenObj.get("full_screen").getAsBoolean();
        final JsonArray screenSize = screenObj.getAsJsonArray("size");
        final JsonArray worldSize = worldObj.getAsJsonArray("size");
        final int worldWidth = worldSize.get(0).getAsInt();
        final int worldHeight = worldSize.get(1).getAsInt();
        final int screenWidth = screenSize.get(0).getAsInt();
        final int screenHeight = screenSize.get(1).getAsInt();
        WORLD_SIZE = new Vector2i(worldWidth, worldHeight);
        SCREEN_SIZE = new Vector2i(screenWidth, screenHeight);
    }

    private void initColors(JsonObject object) {
        COLORS = new HashMap<>();
        object.entrySet().forEach(stringJsonElementEntry -> {
            JsonObject entity = stringJsonElementEntry.getValue().getAsJsonObject();
            if (entity.has("color")) {
                JsonArray color = entity.getAsJsonArray("color");
                COLORS.put(stringJsonElementEntry.getKey(),
                        new Color4(color.get(0).getAsInt(), color.get(1).getAsInt(),
                                color.get(2).getAsInt(), color.get(3).getAsFloat()));
            }
        });
    }
}
