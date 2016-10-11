package model;

import observer.IVertsObservable;
import observer.IVertsObserver;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.joml.Vector2f;
import utils.Constants;
import utils.VertUtils;

import java.util.ArrayList;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class WallModel extends ShapeModel implements IVertsObservable{

    private ArrayList<IVertsObserver> m_observers;


    public WallModel(Vec2 size) {
        super(BodyFactory.createComposite(new Shape[]{
                        new PolygonShape() {{
                            setAsBox(size.x, size.y, new Vec2(0, -size.y), 0);
                        }}
                },
                Constants.WALL_PHYSICS_PARAMS));
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
