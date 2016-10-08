package model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import utils.Parameters;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class WallModel extends ShapeModel {

    public WallModel(Vec2 size) {
        super(BodyFactory.createComposite(new Shape[] { new PolygonShape() {{setAsBox(size.x / 2.f, size.y / 2.f);}} },
                new Parameters(BodyType.STATIC, 0, 1, 1)));
    }
}
