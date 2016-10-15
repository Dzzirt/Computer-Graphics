package model;

import observer.IPositionObservable;
import observer.IPositionObserver;
import observer.IVertsObserver;
import org.jbox2d.common.Vec2;
import utils.Constants;

import java.util.ArrayList;

/**
 * Created by nikita.kuzin on 10/8/16.
 */
public class BallModel extends ShapeModel implements IMoveable, IPositionObservable{

    private ArrayList<IPositionObserver> m_observers;

    public BallModel(float radius) {
        super(BodyFactory.createCircle(radius, Constants.BALL_PHYSICS_PARAMS));
        m_observers = new ArrayList<>();
    }

    @Override
    public void push(Vec2 power, Vec2 point) {
        m_body.applyForce(power, point);
    }

    @Override
    public void notifyObservers() {
        for (IPositionObserver o : m_observers) {
            o.update(getPosition().x, getPosition().y);
        }
    }

    @Override
    public void addObserver(IPositionObserver o) {
        if (!m_observers.contains(o)) {
            m_observers.add(o);
        }
    }

    @Override
    public void deleteObserver(IPositionObserver o) {
        m_observers.remove(o);
    }

    @Override
    public void deleteObservers() {
        m_observers.clear();
    }
}
