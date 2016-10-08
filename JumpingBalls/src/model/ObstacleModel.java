package model;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import utils.FloatRect;
import utils.Parameters;
import view.Main;

/**
 * Created by nikita.kuzin on 10/3/16.
 */
public class ObstacleModel extends ShapeModel{

    public ObstacleModel(Vec2[] verticies) {
        super(BodyFactory.createPolygon(verticies, new Parameters(BodyType.STATIC, 0, 1, 1)));
    }

}
