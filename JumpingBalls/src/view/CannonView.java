package view;


import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import observer.IDrawable;
import observer.IRotationObserver;
import observer.IVertsObserver;
import org.jbox2d.common.Vec2;
import org.joml.Math;
import org.joml.Vector2d;
import org.joml.Vector2f;
import utils.Color4;

/**
 * Created by Nikita on 09.10.2016.
 */
public class CannonView implements IVertsObserver, IDrawable {

    private CircleView m_circleView;
    private VertexView m_vertexView;

    public CannonView(Vector2f pos, float circleRadius, Color4 color) {
        m_vertexView = new VertexView(null, color);
        m_circleView = new CircleView(pos, circleRadius, color);
    }

    @Override
    public void update(Vector2f[] verts) {
        m_vertexView.setVerts(verts);
    }

    @Override
    public void draw(GL2 gl) {
        m_vertexView.draw(gl);
        m_circleView.draw(gl);
    }
}
