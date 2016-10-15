package view;

import com.jogamp.opengl.GL2;
import observer.IDrawable;
import org.joml.Vector2f;
import utils.Color4;

/**
 * Created by nikita.kuzin on 10/12/16.
 */
public class VertexView implements IDrawable {

    private Vector2f[] m_verts;
    private Color4 m_color;

    public VertexView(Vector2f[] verts, Color4 color) {
        setVerts(verts);
        setColor(color);
    }

    public void setColor(Color4 color) {
        m_color = color;
    }

    public void setVerts(Vector2f[] verts) {
        m_verts = verts;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glColor4d(m_color.getGLRed(), m_color.getGLRGreen(), m_color.getGLBlue(), m_color.getGLAlpha());
        gl.glBegin(GL2.GL_POLYGON);
        for (Vector2f m_gunVert : m_verts) {
            gl.glVertex2d(m_gunVert.x, m_gunVert.y);
        }
        gl.glEnd();
    }
}
