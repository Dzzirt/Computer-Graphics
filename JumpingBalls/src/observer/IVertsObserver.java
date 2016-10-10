package observer;

import org.joml.Vector2d;
import org.joml.Vector2f;

/**
 * Created by nikita.kuzin on 10/10/16.
 */
public interface IVertsObserver {
    void update(Vector2f[] verts);
}
