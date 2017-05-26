package rh.calorietracker;

public abstract class Presenter<V> {

    protected final V view;

    public Presenter(V view) {
        this.view = view;
    }
}
