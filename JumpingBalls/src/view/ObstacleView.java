package view;

import com.jogamp.opengl.GL2;
import observer.IDrawable;
import observer.IVertsObserver;
import org.joml.Vector2f;
import utils.Color4;

/**
 * Created by nikita.kuzin on 10/12/16.
 */
public class ObstacleView implements IDrawable, IVertsObserver {

    private VertexView m_vertexView;

    public ObstacleView(Color4 color) {
        m_vertexView = new VertexView(null, color);
    }

    @Override
    public void draw(GL2 gl) {
        m_vertexView.draw(gl);
    }

    @Override
    public void update(Vector2f[] verts) {
        m_vertexView.setVerts(verts);
    }
}
