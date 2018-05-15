package world.arshad.grandordercompanion.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

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
            version = 3,
            exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ServantDatabase extends RoomDatabase {

    public abstract ServantDao servantDao();
}
