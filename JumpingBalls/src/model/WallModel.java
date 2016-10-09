package model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import utils.Constants;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class WallModel extends ShapeModel {

    public WallModel(Vec2 size) {
        super(BodyFactory.createComposite(new Shape[]{
                        new PolygonShape() {{
                            setAsBox(size.x, size.y, new Vec2(0, -size.y), 0);
                        }}
                },
                Constants.WALL_PHYSICS_PARAMS));
    }
}
