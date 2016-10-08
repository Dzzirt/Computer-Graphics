package model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import utils.Parameters;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class BallModel extends ShapeModel implements IMoveable {

    public BallModel(float radius) {
        super(BodyFactory.createCircle(radius, new Parameters(BodyType.DYNAMIC, 1, 1, 1)));
    }

    @Override
    public void push(Vec2 power, Vec2 point) {
        m_body.applyForce(power, point);
    }
}
