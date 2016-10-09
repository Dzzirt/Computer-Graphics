package model;

import org.jbox2d.common.Vec2;
import rx.Observable;
import utils.Constants;

/**
 * Created by nikita.kuzin on 10/3/16.
 */
public class ObstacleModel extends ShapeModel {

    public ObstacleModel(Vec2[] vertices) {
        super(BodyFactory.createPolygon(vertices, Constants.OBSTACLE_PHYSICS_PARAMS));
    }

    @Override
    public Observable getObservable() {
        return null;
    }
}
