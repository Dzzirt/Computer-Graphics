package model;

import observer.IVertsObservable;
import observer.IVertsObserver;
import org.jbox2d.common.Vec2;
import utils.Constants;
import utils.VertUtils;

import java.util.ArrayList;

/**
 * Created by nikita.kuzin on 10/3/16.
 */
public class ObstacleModel extends ShapeModel implements IVertsObservable {

    private ArrayList<IVertsObserver> m_observers;


    public ObstacleModel(Vec2[] vertices) {
        super(BodyFactory.createPolygon(vertices, Constants.OBSTACLE_PHYSICS_PARAMS));
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
