package view;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import controller.WorldController;
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


    public Main() {
        Config config = new Config();
        WorldModel worldModel = new WorldModel(config);
        WorldView worldView = new WorldView(config);
        WorldController worldController = new WorldController(worldModel, worldView, config);
        worldController.sumulate();
    }

    public static void main(String[] args) {
        new Main();
    }


}
