package world.arshad.grandordercompanion;

import android.app.Application;
import android.arch.persistence.room.Room;

import world.arshad.grandordercompanion.data.Model;
import world.arshad.grandordercompanion.data.ServantDatabase;

/**
 * I'm using this as a God object because no one can give me a good reason not to.
 * Created by arshad on 18/03/2018.
 */

public class GOCApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ServantDatabase database = Room.databaseBuilder(
                        getApplicationContext(),
                        ServantDatabase.class,
                        "servant-database")
                .allowMainThreadQueries()
                .build();

        Model.getInstance().setDatabase(database);
    }
}
