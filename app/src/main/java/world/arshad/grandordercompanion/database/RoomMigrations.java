package world.arshad.grandordercompanion.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;

public final class RoomMigrations {
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE servant "
                    + " ADD COLUMN color INTEGER NOT NULL DEFAULT 0");
        }
    };

    private RoomMigrations() {
    }
}
