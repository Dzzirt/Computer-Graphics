package utils;

import org.jbox2d.common.Vec2;

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
}
