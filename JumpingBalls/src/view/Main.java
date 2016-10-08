package view;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import model.BallModel;
import model.CannonModel;
import model.ObstacleModel;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.IViewportTransform;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.pooling.normal.DefaultWorldPool;
import org.joml.Vector2i;
import org.joml.Vector4i;
import utils.Parameters;
import utils.VertUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by nikita.kuzin on 9/25/16.
 */
public class Main {

    public static World world;

    private java.util.List<ObstacleModel> obstacles;

    public Main() {
        world = new World(new Vec2(0, -9.8f), new DefaultWorldPool(100, 100));
        obstacles = new ArrayList<>();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.createModels();
        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
        Frame window = createWindow(new Vector2i(screenResolution.width, screenResolution.height));
        GLJPanel glDrawable = createGlDrawable(new Vector4i(0, 0, 100, 100));
        window.add(glDrawable);
        window.setVisible(true);
        glDrawable.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                world.step(1 / 60.f, 10, 10);
                glDrawable.display();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        world.setDebugDraw(createJoglDebugDraw(glDrawable));
    }

    private void createModels() {
        CannonModel cannonModel = new CannonModel();
        cannonModel.setPosition(50, 50);
        for (int i = 0; i < 4; i++) {
            ObstacleModel model = new ObstacleModel(VertUtils.getRandomVerts(10, 15, 8));
            obstacles.add(model);
        }
        obstacles.get(0).setPosition(25, 55);
        obstacles.get(1).setPosition(25, 25);
        obstacles.get(2).setPosition(55, 25);
        obstacles.get(3).setPosition(55, 55);
    }
    private static Frame createWindow(Vector2i size) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(size.x, size.y);
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
