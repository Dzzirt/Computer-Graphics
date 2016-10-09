package utils;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

/**
 * Created by Nikita on 09.10.2016.
 */
public class Constants {
    public static final float DEGREES_TO_RADIANS = (float) (Math.PI / 180);

    public static final Parameters BALL_PHYSICS_PARAMS = new Parameters(BodyType.DYNAMIC, 1, 1, 1);
    public static final Parameters WALL_PHYSICS_PARAMS = new Parameters(BodyType.STATIC, 0, 1, 1);
    public static final Parameters CANNON_PHYSICS_PARAMS = new Parameters(BodyType.KINEMATIC, 0, 1, 1);
    public static final Parameters OBSTACLE_PHYSICS_PARAMS = new Parameters(BodyType.STATIC, 0, 1, 1);

    public static final float CANNON_CIRCLE_RADIUS = 3;
    public static final Vec2 CANNON_GUN_HALF_SIZE = new Vec2(1, 4);
    public static final Vec2 CANNON_GUN_PIVOT = new Vec2(0, 4);
    public static final float CANNON_GUN_ANGLE = 0;
}
