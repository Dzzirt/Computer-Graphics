package observer;

import org.joml.Vector2f;

/**
 * Created by nikita.kuzin on 10/10/16.
 */
public interface IVertObservable {
    void notifyObservers();
    void addObserver(IVertsObserver o);
    void deleteObserver(IVertsObserver o);
    void deleteObservers();
}
