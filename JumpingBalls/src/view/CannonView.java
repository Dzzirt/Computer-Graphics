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

    private Vector2f m_pos;
    private float m_circleRadius;
    private Vector2f[] m_gunVerts;
    private Color4 m_color;

    public CannonView(Vector2f pos, float circleRadius, Color4 color) {
        m_pos = pos;
        m_circleRadius = circleRadius;
        m_color = color;
    }

    @Override
    public void update(Vector2f[] verts) {
        m_gunVerts = verts;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glColor4d(m_color.getGLRed(), m_color.getGLRGreen(), m_color.getGLBlue(), m_color.getGLAlpha());
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2d(m_pos.x, m_pos.y);
        for (int i = 0; i <= 360; i++) {
            double x = m_circleRadius * Math.cos(2 * Math.PI * i / 360) + m_pos.x;
            double y = m_circleRadius * Math.sin(2 * Math.PI * i / 360) + m_pos.y;
            gl.glVertex2d(x, y);
        }
        gl.glEnd();
        gl.glBegin(GL2.GL_POLYGON);
        for (Vector2f m_gunVert : m_gunVerts) {
            gl.glVertex2d(m_gunVert.x, m_gunVert.y);
        }
        gl.glEnd();
    }
}
