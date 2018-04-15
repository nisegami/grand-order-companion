package world.arshad.grandordercompanion.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * This is the database that backs the application.
 * Created by arshad on 19/03/2018.
 */

@Database(entities = {
                Servant.class,
                Ascension.class,
                AscensionEntry.class,
                SkillUp.class,
                SkillUpEntry.class
            },
            version = 2,
            exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ServantDatabase extends RoomDatabase {

    public abstract ServantDao servantDao();
}
