package model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import utils.Parameters;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class CannonModel extends ShapeModel {
    public CannonModel() {
        super(BodyFactory.createComposite(new Shape[]
                {
                    new CircleShape() {{setRadius(3);}},
                        new PolygonShape() {
                            {
                                setAsBox(1, 4, new Vec2(0, 4), 0);
                            }
                    }
                },
                new Parameters(BodyType.KINEMATIC, 0, 1, 1)));
    }
}
