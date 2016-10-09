package model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import utils.Constants;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class CannonModel extends ShapeModel {
    public CannonModel() {
        super(BodyFactory.createComposite(new Shape[]
                        {
                                new CircleShape() {{
                                    setRadius(Constants.CANNON_CIRCLE_RADIUS);
                                }},
                                new PolygonShape() {{
                                    setAsBox(Constants.CANNON_GUN_HALF_SIZE.x,
                                            Constants.CANNON_GUN_HALF_SIZE.y,
                                            Constants.CANNON_GUN_PIVOT,
                                            Constants.CANNON_GUN_ANGLE);
                                }}
                        },
                Constants.CANNON_PHYSICS_PARAMS));
    }
}
