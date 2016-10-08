package model;

import org.jbox2d.common.Vec2;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public interface IMoveable {
    void push(Vec2 power, Vec2 point);
}
