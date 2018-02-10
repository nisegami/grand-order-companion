package world.arshad.grandordercompanion;

import android.app.Application;
import android.arch.persistence.room.Room;

import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataDatabase;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;

/**
 * Created by arshad on 03/01/2018.
 */

public class GrandOrderCompanionApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Populate read only data into singleton

        DomainDataSingleton.getInstance().loadDomainData(this);

        // TODO: dont run db on main thread
        UserDataSingleton.getInstance().setRoomDB(Room.databaseBuilder(getApplicationContext(),
                UserDataDatabase.class, "goc_user_data").allowMainThreadQueries().build());

    }
}
