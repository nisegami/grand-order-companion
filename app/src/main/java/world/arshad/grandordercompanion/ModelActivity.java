package world.arshad.grandordercompanion;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import world.arshad.grandordercompanion.database.RoomMigrations;
import world.arshad.grandordercompanion.database.ServantDatabase;
import world.arshad.grandordercompanion.database.ServantRepository;

public abstract class ModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ServantRepository.getInstance().isValid()) {
            populateRepository();
        }
    }

    private void populateRepository() {
        ServantDatabase database = Room.databaseBuilder(
                getApplicationContext(),
                ServantDatabase.class,
                "servant-database")
                .allowMainThreadQueries()
                .addMigrations(RoomMigrations.MIGRATION_2_3)
                .build();

        boolean NAOnly = getSharedPreferences("goc", Context.MODE_PRIVATE).getBoolean("na_only", true);
        ServantRepository.getInstance().setBackingDatabase(database.servantDao(), NAOnly);
    }
}
