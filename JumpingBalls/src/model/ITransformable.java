package model;

import org.jbox2d.common.Vec2;

/**
 * Created by nikita.kuzin on 10/3/16.
 */
public interface ITransformable {
    void setPosition(float x, float y);
    Vec2 getPosition();
    void setRotation(float angle);
    float getRotation();
}
