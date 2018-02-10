package world.arshad.grandordercompanion.data.user_data.sources;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import world.arshad.grandordercompanion.data.user_data.TrackedAscension;
import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;

/**
 * Created by arshad on 09/02/2018.
 */

@Database(entities = {TrackedAscension.class, TrackedSkillUp.class}, version = 3, exportSchema = false)
public abstract class UserDataDatabase extends RoomDatabase {
    public abstract TrackedAscensionDao trackedAscensionDao();
    public abstract TrackedSkillUpDao trackedSkillUpDao();
}
