package observer;

/**
 * Created by nikita.kuzin on 10/15/16.
 */
public interface IObservervable<T> {
    void notifyObservers();
    void addObserver(T o);
    void deleteObserver(T o);
    void deleteObservers();
}
