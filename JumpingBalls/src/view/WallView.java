package view;

import com.jogamp.opengl.GL2;
import observer.IDrawable;
import observer.IVertsObserver;
import org.joml.Vector2f;
import utils.Color4;

/**
 * Created by nikita.kuzin on 10/11/16.
 */
public class WallView implements IVertsObserver, IDrawable {

    private Vector2f[] m_verts;
    private Color4 m_color;

    public WallView(Color4 color) {
        m_color = color;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glColor4d(m_color.getGLRed(), m_color.getGLRGreen(), m_color.getGLBlue(), m_color.getGLAlpha());
        gl.glBegin(GL2.GL_POLYGON);
        for (Vector2f vert : m_verts) {
            gl.glVertex2d(vert.x, vert.y);
        }
        gl.glEnd();
    }

    @Override
    public void update(Vector2f[] verts) {
        m_verts = verts;
    }
}
