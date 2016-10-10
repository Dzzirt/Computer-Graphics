package model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import utils.Constants;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public abstract class ShapeModel implements ITransformable {

    protected Body m_body;

    public ShapeModel(Body body) {
        m_body = body;
    }

    @Override
    public void setPosition(float x, float y) {
        m_body.setTransform(new Vec2(x, y), m_body.getAngle());
    }

    @Override
    public void setRotation(float angle) {
        m_body.setTransform(m_body.getPosition(), angle * Constants.DEGREES_TO_RADIANS);
    }

    @Override
    public Vec2 getPosition() {
        return m_body.getPosition();
    }

    @Override
    public float getRotation() {
        return m_body.getAngle();
    }
}
