package view;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import model.CannonModel;
import model.WallModel;
import model.WorldModel;
import observer.IDrawable;
import org.jbox2d.callbacks.DebugDraw;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4i;
import utils.Color4;
import utils.Config;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita.kuzin on 9/25/16.
 */
public class Main {

    private GLJPanel m_glDrawable;
    private Config m_config;
    private CannonModel cannonModel;

    public Main() {
        m_config = new Config();
        WorldModel worldModel = new WorldModel(m_config);
        cannonModel = worldModel.getCannonModel();
        List<WallModel> wallModels = worldModel.getWallModels();
        cannonModel.setRotation(45);
        Vector2f viewPos = new Vector2f(cannonModel.getPosition().x, cannonModel.getPosition().y);
        ArrayList<IDrawable> drawables = new ArrayList<>();
        CannonView cannonView = new CannonView(viewPos, Constants.CANNON_CIRCLE_RADIUS,
                new Color4(142, 68, 173, 1.f));
        drawables.add(cannonView);
        cannonModel.addObserver(cannonView);
        for (WallModel wallModel : wallModels) {
            WallView o = new WallView(new Color4(26, 188, 156, 1f));
            drawables.add(o);
            wallModel.addObserver(o);
        }
        createCanvas(drawables);
    }

    public static void main(String[] args) {
        new Main();
    }

    private GLEventListener createCanvas(List<IDrawable> drawables) {
        Frame window = createWindow(m_config.SCREEN_SIZE, m_config.FULLSCREEN);
        Vector4i worldBoundingBox = new Vector4i(0, 0, m_config.WORLD_SIZE.x, m_config.WORLD_SIZE.y);
        OpenGLImpl glListener = new OpenGLImpl(drawables, worldBoundingBox);
        m_glDrawable = createGlDrawable(glListener);
        window.add(m_glDrawable);
        window.setVisible(true);
        if (m_config.DEBUG_DRAW) {
            WorldModel.world.setDebugDraw(createJoglDebugDraw(m_glDrawable));
        }
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                WorldModel.world.step(m_config.FRAME_RATE, m_config.VELOCITY_ITERATIONS, m_config.POSITION_ITERATIONS);
                cannonModel.notifyObservers();
                m_glDrawable.display();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        return glListener;

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

    private static GLJPanel createGlDrawable(GLEventListener listener) {
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        GLJPanel gljPanel = new GLJPanel(glCapabilities);
        gljPanel.addGLEventListener(listener);
        return gljPanel;
    }

    private static DebugDraw createJoglDebugDraw(GLJPanel glDrawable) {
        JoglDebugDraw debugDraw = new JoglDebugDraw(glDrawable);
        debugDraw.setFlags(DebugDraw.e_shapeBit);
        return debugDraw;
    }
}
