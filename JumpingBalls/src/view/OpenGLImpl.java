package view;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import observer.IDrawable;
import org.joml.Vector4i;

import java.util.List;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES1.GL_POINT_SMOOTH;
import static com.jogamp.opengl.GL2ES1.GL_POINT_SMOOTH_HINT;
import static com.jogamp.opengl.GL2GL3.GL_POLYGON_SMOOTH;
import static com.jogamp.opengl.GL2GL3.GL_POLYGON_SMOOTH_HINT;

/**
 * Created by nikita.kuzin on 9/27/16.
 */
public class OpenGLImpl implements GLEventListener {

    private Vector4i m_orthoRect;
    private List<IDrawable> m_drawables;

    public OpenGLImpl(List<IDrawable> drawables, Vector4i worldBoundingBox) {
        m_orthoRect = worldBoundingBox;
        m_drawables = drawables;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl2 = glAutoDrawable.getGL().getGL2();
        gl2.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        gl2.glEnable(GL_BLEND);
        gl2.glEnable(GL_LINE_SMOOTH);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl2 = glAutoDrawable.getGL().getGL2();
        gl2.glClear(GL_COLOR_BUFFER_BIT);
        Main.world.drawDebugData();
        for (IDrawable drawable : m_drawables) {
            drawable.draw(gl2);
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        GL2 gl2 = glAutoDrawable.getGL().getGL2();
        gl2.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl2.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluOrtho2D(m_orthoRect.x, m_orthoRect.w, m_orthoRect.y, m_orthoRect.z); //fixed size
        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl2.glLoadIdentity();
        gl2.glViewport((i2 - i3) / 2, 0, i3, i3);
    }
}
