package world.arshad.grandordercompanion;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

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
                .fallbackToDestructiveMigration()
                .build();

        Model.getInstance().setDatabase(database);

        SharedPreferences prefs = getSharedPreferences("goc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("use_colors", true);
        editor.apply();
    }
}
