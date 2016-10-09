package view;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import model.CannonModel;
import model.ObstacleModel;
import model.WallModel;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.pooling.normal.DefaultWorldPool;
import org.joml.Vector2i;
import org.joml.Vector4i;
import utils.Config;
import utils.Constants;
import utils.VertUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita.kuzin on 9/25/16.
 */
public class Main {

    public static World world;

    private Config m_config;
    private List<ObstacleModel> m_obstacleModels;
    private List<WallModel> m_wallModels;
    private CannonModel m_cannonModel;

    public Main() {
        m_config = new Config();
        createWorld(m_config.getJsonRoot());
        createCanvas();
        m_obstacleModels = createObstacles(m_config.getJsonRoot());
        m_wallModels = createWalls(m_config.getJsonRoot());
        m_cannonModel = createCannon(m_config.getJsonRoot());
    }

    public static void main(String[] args) {
        new Main();
    }

    private void createWorld(JsonObject object) {
        final JsonArray gravity = object.getAsJsonObject("World").getAsJsonArray("gravity");
        final float xGravity = gravity.get(0).getAsFloat();
        final float yGravity = gravity.get(1).getAsFloat();
        world = new World(new Vec2(xGravity, yGravity), new DefaultWorldPool(100, 100));
    }

    private CannonModel createCannon(JsonObject object) {
        final CannonModel cannonModel = new CannonModel();
        final JsonObject jsonCannon = object.getAsJsonObject("Cannon");
        final JsonArray pos = jsonCannon.getAsJsonArray("pos");
        cannonModel.setPosition(pos.get(0).getAsFloat(), pos.get(1).getAsFloat());
        return cannonModel;
    }

    private void createCanvas() {
        Frame window = createWindow(m_config.SCREEN_SIZE, m_config.FULLSCREEN);
        GLJPanel glDrawable = createGlDrawable(new Vector4i(0, 0, m_config.WORLD_SIZE.x, m_config.WORLD_SIZE.y));
        window.add(glDrawable);
        window.setVisible(true);
        if (m_config.DEBUG_DRAW) {
            world.setDebugDraw(createJoglDebugDraw(glDrawable));
        }
    }

    private List<WallModel> createWalls(JsonObject object) {
        List<WallModel> wallModelList = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            final JsonObject jsonWall = object.getAsJsonObject("Wall" + i);
            final JsonArray halfSizeArr = jsonWall.getAsJsonArray("half_size");
            final JsonArray posArr = jsonWall.getAsJsonArray("pos");
            final JsonElement angle = jsonWall.get("angle");
            final int halfWidth = halfSizeArr.get(0).getAsInt();
            final int halfHeight = halfSizeArr.get(1).getAsInt();
            final float x = posArr.get(0).getAsFloat();
            final float y = posArr.get(1).getAsFloat();
            final WallModel wall = new WallModel(new Vec2(halfWidth, halfHeight));
            if (angle != null) {
                wall.setRotation(angle.getAsFloat() * Constants.DEGREES_TO_RADIANS);
            }
            wall.setPosition(x, y);
            wallModelList.add(wall);
        }
        return wallModelList;
    }

    private List<ObstacleModel> createObstacles(JsonObject object) {
        List<ObstacleModel> wallModelList = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            final JsonObject jsonObstacle = object.getAsJsonObject("Obstacle" + i);
            final JsonArray posArr = jsonObstacle.getAsJsonArray("pos");
            final float minScatterRadius = jsonObstacle.get("min_scatter_radius").getAsFloat();
            final float maxScatterRadius = jsonObstacle.get("max_scatter_radius").getAsFloat();
            final int vertexCount = jsonObstacle.get("vertex_count").getAsInt();
            final float x = posArr.get(0).getAsFloat();
            final float y = posArr.get(1).getAsFloat();
            final ObstacleModel obstacle = new ObstacleModel(VertUtils.getRandomVerts(minScatterRadius, maxScatterRadius, vertexCount));
            obstacle.setPosition(x, y);
            wallModelList.add(obstacle);
        }
        return wallModelList;
    }

    private static Frame createWindow(Vector2i size, boolean fullScreen) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(size.x, size.y);
        if (fullScreen) {
            jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jFrame.setUndecorated(true);
        }
        return jFrame;
    }

    private static GLJPanel createGlDrawable(Vector4i worldBoundingBox) {
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        GLJPanel gljPanel = new GLJPanel(glCapabilities);
        gljPanel.addGLEventListener(new OpenGLImpl(worldBoundingBox));
        return gljPanel;
    }

    private static DebugDraw createJoglDebugDraw(GLJPanel glDrawable) {
        JoglDebugDraw debugDraw = new JoglDebugDraw(glDrawable);
        debugDraw.setFlags(DebugDraw.e_shapeBit);
        return debugDraw;
    }
}
