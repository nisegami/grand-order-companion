package world.arshad.grandordercompanion;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.database.RoomMigrations;
import world.arshad.grandordercompanion.database.ServantDatabase;

/**
 * Stuff used to be here, but has been moved to SplashActivity.
 * Created by arshad on 18/03/2018.
 */

public class GOCApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
