package world.arshad.grandordercompanion.data;

/**
 * Created by arsha on 19/03/2018.
 */

public class Model {
    private static final Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    private Model() {
    }

    // Database

    private ServantDatabase database;


    public ServantDatabase getDatabase() {
        return database;
    }

    public void setDatabase(ServantDatabase database) {
        this.database = database;
    }

    // Refresh Needed Materials
    private boolean refreshNeededMaterials = true;

    public boolean isRefreshNeededMaterials() {
        return refreshNeededMaterials;
    }

    public void setRefreshNeededMaterials(boolean refreshNeededMaterials) {
        this.refreshNeededMaterials = refreshNeededMaterials;
    }
}
