package nl.saxion.ap.drawtalk.model;

/**
 * Created by ear01 on 25/04/16.
 */
public class Model {
    private static Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    private Model() {
    }
}
