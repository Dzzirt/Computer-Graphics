package model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import utils.Parameters;
import view.Main;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class BodyFactory {

    public static Body createPolygon(Vec2[] verts, Parameters params) {
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(verts, verts.length);
        return createBody(new Shape[] { polygonShape }, params);
    }

    public static Body createCircle(float radius, Parameters params) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        return createBody(new Shape[] { circleShape }, params);
    }

    public static Body createComposite(Shape[] shapes, Parameters params) {
        return createBody(shapes, params);
    }

    private static Body createBody(Shape[] shapes , Parameters params) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = params.type;
        Body body = Main.world.createBody(bodyDef);
        for (Shape shape : shapes) {
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = params.density;
            fixtureDef.friction = params.friction;
            fixtureDef.restitution = params.restitution;
            body.createFixture(fixtureDef);
        }
        return body;
    }
}
