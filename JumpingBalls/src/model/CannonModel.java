package model;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import rx.Observable;
import rx.Subscriber;
import utils.Constants;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class CannonModel extends ShapeModel {
    public CannonModel() {
        super(BodyFactory.createComposite(new Shape[]
                        {
                                new CircleShape() {{
                                    setRadius(Constants.CANNON_CIRCLE_RADIUS);
                                }},
                                new PolygonShape() {{
                                    setAsBox(Constants.CANNON_GUN_HALF_SIZE.x,
                                            Constants.CANNON_GUN_HALF_SIZE.y,
                                            Constants.CANNON_GUN_PIVOT,
                                            Constants.CANNON_GUN_ANGLE);
                                }}
                        },
                Constants.CANNON_PHYSICS_PARAMS));
    }

    @Override
    public Observable getObservable() {
        PolygonShape shape = ((PolygonShape) m_body.getFixtureList().getShape());
        return Observable.create(new Observable.OnSubscribe<Vec2[]>() {
            @Override
            public void call(Subscriber<? super Vec2[]> subscriber) {
                final int vertexCount = shape.getVertexCount();
                final Vec2[] vertices = shape.getVertices();
                final Matrix4f transform = new Matrix4f()
                        .translate(m_body.getPosition().x, m_body.getPosition().y, 0)
                        .rotate(m_body.getAngle(), new Vector3f(m_body.getLocalCenter().x, m_body.getLocalCenter().y, 0));
                final Vec2[] output = new Vec2[vertexCount];
                for (int i = 0; i < vertexCount; i++) {
                    final Vector3f v = new Vector3f(vertices[i].x, vertices[i].y, 0);
                    transform.transformPosition(v);
                    output[i].set(v.x, v.y);
                }
                subscriber.onNext(vertices);
            }
        });
    }
}
