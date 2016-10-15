package view;

import com.jogamp.opengl.GL2;
import observer.IDrawable;
import observer.IPositionObserver;
import org.joml.Vector2f;
import utils.Color4;

/**
 * Created by nikita.kuzin on 10/12/16.
 */
public class BallView implements IDrawable, IPositionObserver {


    private CircleView m_circleView;

    public BallView(float radius, Color4 color) {
        m_circleView = new CircleView(null, radius, color);
    }

    @Override
    public void update(float x, float y) {
        m_circleView.setPosition(x, y);
    }

    @Override
    public void draw(GL2 gl) {
        m_circleView.draw(gl);
    }
}
