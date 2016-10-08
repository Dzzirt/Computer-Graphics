package utils;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.BodyType;

/**
 * Created by nikita.kuzin on 10/3/16.
 */
public class Parameters {
    public float restitution;
    public float density;
    public float friction;
    public BodyType type;

    public Parameters(BodyType type, float restitution, float density, float friction) {
        this.restitution = restitution;
        this.density = density;
        this.friction = friction;
        this.type = type;
    }
}
