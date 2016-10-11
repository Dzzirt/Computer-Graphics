package utils;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Random;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class VertUtils {

    //Returns verts in range no closer and further than min/max radius of circle with center in [0; 0]
    public static Vec2[] getRandomVerts(float minRadius, float maxRadius, int count) {
        Random random = new Random();
        Vec2[] verts = new Vec2[count];
        for (int i = 0; i < count; i++) {
            float randX = random.nextInt((int) maxRadius * 10) * 0.1f;
            float randY = random.nextInt((int) maxRadius * 10) * 0.1f;
            double sum = Math.pow(randX, 2) + Math.pow(randY, 2);
            if (sum <= Math.pow(maxRadius, 2) && sum >= Math.pow(minRadius, 2)) {
                verts[i] = new Vec2(randX, randY);
            } else {
                i--;
            }
        }
        return verts;
    }

    public static Vector2f[] getWorldVerts(Body body) {
        PolygonShape shape = ((PolygonShape) body.getFixtureList().getShape());
        final int vertexCount = shape.getVertexCount();
        final Vec2[] vertices = shape.getVertices();
        final Matrix4f transform = new Matrix4f()
                .translate(body.getPosition().x, body.getPosition().y, 0)
                .rotate(body.getAngle(), new Vector3f(0, 0, 1));
        final Vector2f[] output = new Vector2f[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            final Vector3f v = new Vector3f(vertices[i].x, vertices[i].y, 0);
            transform.transformPosition(v);
            output[i] = new Vector2f(v.x, v.y);
        }
        return output;
    }
}
