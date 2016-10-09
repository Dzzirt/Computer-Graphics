package model;

import org.jbox2d.common.Vec2;
import utils.Constants;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class BallModel extends ShapeModel implements IMoveable {

    public BallModel(float radius) {
        super(BodyFactory.createCircle(radius, Constants.BALL_PHYSICS_PARAMS));
    }

    @Override
    public void push(Vec2 power, Vec2 point) {
        m_body.applyForce(power, point);
    }
}
