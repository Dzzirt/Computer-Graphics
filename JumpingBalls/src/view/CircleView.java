package view;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import observer.IDrawable;
import org.joml.Math;
import org.joml.Vector2f;
import utils.Color4;

/**
 * Created by nikita.kuzin on 10/12/16.
 */
public class CircleView implements IDrawable {
    private Vector2f m_pos;
    private float m_radius;
    private Color4 m_color;

    public CircleView(Vector2f pos, float radius, Color4 color) {
        m_pos = pos;
        m_radius = radius;
        m_color = color;
    }

    public void setPosition(float x, float y) {
        m_pos.set(x, y);
    }


    @Override
    public void draw(GL2 gl) {
        gl.glColor4d(m_color.getGLRed(), m_color.getGLRGreen(), m_color.getGLBlue(), m_color.getGLAlpha());
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glVertex2d(m_pos.x, m_pos.y);
        for (int i = 0; i <= 360; i++) {
            double x = m_radius * Math.cos(2 * Math.PI * i / 360) + m_pos.x;
            double y = m_radius * Math.sin(2 * Math.PI * i / 360) + m_pos.y;
            gl.glVertex2d(x, y);
        }
        gl.glEnd();
    }
}
