package world.arshad.grandordercompanion.data.user_data.sources;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import world.arshad.grandordercompanion.data.user_data.TrackedAscension;

/**
 * Created by arshad on 09/02/2018.
 */

@Database(entities = {TrackedAscension.class}, version = 1, exportSchema = false)
public abstract class UserDataDatabase extends RoomDatabase {
    public abstract TrackedAscensionDao trackedAscensionDao();
}
