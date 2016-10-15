package view;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import model.WorldModel;
import observer.IDrawable;
import org.jbox2d.callbacks.DebugDraw;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4i;
import utils.Color4;
import utils.Config;
import utils.Constants;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita.kuzin on 10/12/16.
 */
public class WorldView {

    private Config m_config;
    private GLCanvas m_glDrawable;
    private List<WallView> m_wallViews;
    private List<ObstacleView> m_obstacleViews;
    private List<BallView> m_ballViews;
    private CannonView m_cannonView;

    public WorldView(Config config) {
        m_config = config;
        List<IDrawable> drawables = new ArrayList<>();
        m_wallViews = new ArrayList<>();
        m_obstacleViews = new ArrayList<>();
        m_ballViews = new ArrayList<>();
        m_cannonView = new CannonView(m_config.CANNON_POS, Constants.CANNON_CIRCLE_RADIUS, m_config.COLORS.get("Cannon"));
        drawables.add(m_cannonView);
        for (int i = 0; i < 4; i++) {
            WallView wall = new WallView(m_config.COLORS.get("Wall" + i));
            ObstacleView obstacle = new ObstacleView(m_config.COLORS.get("Obstacle" + i));
            m_wallViews.add(wall);
            m_obstacleViews.add(obstacle);
            drawables.add(wall);
            drawables.add(obstacle);
        }
        m_glDrawable = createCanvas(drawables);
    }

    public void addMouseListener(MouseListener listener) {
        m_glDrawable.addMouseListener(listener);
    }

    public void display() {
        if (!m_glDrawable.isVisible()) {
            m_glDrawable.setVisible(true);
        }
        m_glDrawable.display();
    }

    public List<WallView> getWallViews() {
        return m_wallViews;
    }

    public List<ObstacleView> getObstacleViews() {
        return m_obstacleViews;
    }

    public List<BallView> getBallViews() {
        return m_ballViews;
    }

    public CannonView getCannonView() {
        return m_cannonView;
    }

    private WallView createWallView(Color4 color) {
        return new WallView(color);
    }

    private CannonView createCannonView(Vector2f pos, float circleRadius, Color4 color) {
        return new CannonView(pos, circleRadius, color);
    }

    private ObstacleView createObstacleView(Color4 color) {
        return new ObstacleView(color);
    }

    private BallView createBallView(float radius, Color4 color) {
        return new BallView(radius, color);
    }

    private GLCanvas createCanvas(List<IDrawable> drawables) {
        Vector4i worldBoundingBox = new Vector4i(0, 0, m_config.WORLD_SIZE.x, m_config.WORLD_SIZE.y);
        OpenGLImpl glListener = new OpenGLImpl(drawables, worldBoundingBox);
        GLCanvas window = createWindow(m_config.SCREEN_SIZE, m_config.FULLSCREEN);
        window.addGLEventListener(glListener);
        if (m_config.DEBUG_DRAW) {
            WorldModel.world.setDebugDraw(createJoglDebugDraw(window));
        }
        return window;

    }

    private static GLCanvas createWindow(Vector2i size, boolean fullScreen) {
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        GLCanvas glCanvas = new GLCanvas(glCapabilities);
        Frame frame = new Frame();
        frame.add(glCanvas);
        frame.setSize(size.x, size.y);
        frame.setVisible(true);
        return glCanvas;
    }

    private static DebugDraw createJoglDebugDraw(GLCanvas glCanvas) {
        JoglDebugDraw debugDraw = new JoglDebugDraw(glCanvas);
        debugDraw.setFlags(DebugDraw.e_shapeBit);
        return debugDraw;
    }
}
