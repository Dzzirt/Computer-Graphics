package view;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.*;
import org.joml.Math;

/**
 * Created by nikita.kuzin on 9/25/16.
 */
public class JoglDebugDraw extends DebugDraw {

    private GLCanvas m_gl;

    public JoglDebugDraw(GLCanvas gl) {
        super(new OBBViewportTransform());
        m_gl = gl;
    }

    @Override
    public void drawPoint(Vec2 vec2, float v, Color3f color3f) {

    }


    @Override
    public void drawSolidPolygon(Vec2[] vec2s, int i, Color3f color3f) {
        GL2 gl2 = m_gl.getGL().getGL2();
        gl2.glColor4d(color3f.x, color3f.y, color3f.z, 0.5);
        gl2.glBegin(GL2.GL_POLYGON);
        for (int counter = 0; counter < i; counter++) {
            gl2.glVertex2d(vec2s[counter].x, vec2s[counter].y);
        }
        gl2 .glEnd();
        gl2.glColor4d(color3f.x, color3f.y, color3f.z, 1);
        gl2.glBegin(GL2.GL_LINE_LOOP);
        for (int counter = 0; counter < i; counter++) {
            gl2.glVertex2d(vec2s[counter].x, vec2s[counter].y);
        }
        gl2 .glEnd();
    }

    @Override
    public void drawCircle(Vec2 vec2, float v, Color3f color3f) {

    }

    @Override
    public void drawSolidCircle(Vec2 vec2, float v, Vec2 vec21, Color3f color3f) {
        GL2 gl2 = m_gl.getGL().getGL2();
        gl2.glColor4d(color3f.x, color3f.y, color3f.z, 0.5);
        gl2.glBegin(GL.GL_TRIANGLE_FAN);
        gl2.glVertex2d(vec2.x, vec2.y);
        for (int i = 0; i < 360; i++) {
            double x = v * Math.cos(2 * Math.PI * i / 360) + vec2.x;
            double y = v * Math.sin(2 * Math.PI * i / 360) + vec2.y;
            gl2.glVertex2d(x, y);
        }
        gl2.glEnd();
        gl2.glColor4d(color3f.x, color3f.y, color3f.z, 1);
        gl2.glBegin(GL.GL_LINE_STRIP);
        gl2.glVertex2d(vec2.x, vec2.y);
        for (int i = 0; i < 360; i++) {
            double x = v * Math.cos(2 * Math.PI * i / 360) + vec2.x;
            double y = v * Math.sin(2 * Math.PI * i / 360) + vec2.y;
            gl2.glVertex2d(x, y);
        }
        gl2.glEnd();
    }

    @Override
    public void drawSegment(Vec2 vec2, Vec2 vec21, Color3f color3f) {
        GL2 gl2 = m_gl.getGL().getGL2();
        gl2.glColor3f(color3f.x, color3f.y, color3f.z);
        gl2.glBegin(GL.GL_LINES);
        gl2.glVertex2d(vec2.x, vec2.y);
        gl2.glVertex2d(vec21.x, vec21.y);
        gl2.glEnd();
    }

    @Override
    public void drawTransform(Transform transform) {

    }

    @Override
    public void drawString(float v, float v1, String s, Color3f color3f) {

    }
}
