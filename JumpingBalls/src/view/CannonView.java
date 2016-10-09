package view;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import org.jbox2d.common.Vec2;
import rx.Observable;

/**
 * Created by Nikita on 09.10.2016.
 */
public class CannonView {
    Observable m_subject;

    public CannonView(Observable observable) {
        m_subject = observable;
    }

    private void draw(GL2 gl) {
        m_subject.subscribe(o -> {
            final Vec2[] verts = (Vec2[]) o;
            gl.glBegin(GL.GL_LINE_LOOP);
            for (int i = 0; i < verts.length; i++) {
                gl.glVertex2d(verts[i].x, verts[i].y);
            }
            gl.glEnd();
        }).unsubscribe();
    }
}
