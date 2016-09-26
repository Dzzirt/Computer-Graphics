import com.jogamp.opengl.GLEventListener;
import org.jbox2d.common.IViewportTransform;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.pooling.normal.DefaultWorldPool;

/**
 * Created by nikita.kuzin on 9/25/16.
 */
public class Main {

    public static void main(String[] args) {
        World world = new World(new Vec2(0, -9.8f), new DefaultWorldPool(100, 100));
        IViewportTransform viewportTransform = new OBBViewportTransform();
        viewportTransform.setExtents(400, 300);
        viewportTransform.setCenter(0, 0);
        world.setDebugDraw(new JoglDebugDraw(viewportTransform));
        world.drawDebugData();
    }
}
