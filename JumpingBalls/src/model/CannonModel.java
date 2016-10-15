package model;

import javafx.beans.Observable;
import observer.IVertsObservable;
import observer.IVertsObserver;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import utils.Constants;
import utils.VertUtils;

import java.util.ArrayList;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class CannonModel extends ShapeModel implements IVertsObservable {

    private ArrayList<IVertsObserver> m_observers;

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
        m_observers = new ArrayList<>();
    }

    @Override
    public void notifyObservers() {
        for (IVertsObserver o : m_observers) {
            o.update(VertUtils.getWorldVerts(m_body));
        }
    }

    @Override
    public void addObserver(IVertsObserver o) {
        if (!m_observers.contains(o)) {
            m_observers.add(o);
            o.update(VertUtils.getWorldVerts(m_body));
        }
    }

    @Override
    public void deleteObserver(IVertsObserver o) {
        m_observers.remove(o);
    }

    @Override
    public void deleteObservers() {
        m_observers.clear();
    }
}
