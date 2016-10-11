package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.pooling.normal.DefaultWorldPool;
import utils.Config;
import utils.VertUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita.kuzin on 10/11/16.
 */
public class WorldModel {

    public static World world;

    private CannonModel m_cannon;
    private List<WallModel> m_walls;
    private List<ObstacleModel> m_obstacles;
    private Config m_config;

    public WorldModel(Config config) {
        m_config = config;
        JsonObject jsonRoot = m_config.getJsonRoot();
        world = createWorld();
        m_walls = createWalls(jsonRoot);
        m_obstacles = createObstacles(jsonRoot);
        m_cannon = createCannon(jsonRoot);
    }

    public CannonModel getCannonModel() {
        return m_cannon;
    }

    public List<WallModel> getWallModels() {
        return m_walls;
    }

    public List<ObstacleModel> getObstacleModels() {
        return m_obstacles;
    }

    private World createWorld() {
        return new World(new Vec2(m_config.GRAVITY.x, m_config.GRAVITY.y), new DefaultWorldPool(100, 100));
    }

    private CannonModel createCannon(JsonObject object) {
        final CannonModel cannonModel = new CannonModel();
        final JsonObject jsonCannon = object.getAsJsonObject("Cannon");
        final JsonArray pos = jsonCannon.getAsJsonArray("pos");
        cannonModel.setPosition(pos.get(0).getAsFloat(), pos.get(1).getAsFloat());
        return cannonModel;
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
                wall.setRotation(angle.getAsFloat());
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



}
