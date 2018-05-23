package world.arshad.grandordercompanion;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import world.arshad.grandordercompanion.all_servants.AllServantsActivity;
import world.arshad.grandordercompanion.database.DatabaseUpdater;
import world.arshad.grandordercompanion.database.RoomMigrations;
import world.arshad.grandordercompanion.database.ServantDatabase;
import world.arshad.grandordercompanion.database.ServantRepository;

public class SplashActivity extends AppCompatActivity implements DatabaseUpdater.PostUpdateCallback {

    private Activity a = this;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ServantDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefs = getSharedPreferences("goc", Context.MODE_PRIVATE);
        editor = prefs.edit();

        database = Room.databaseBuilder(
                getApplicationContext(),
                ServantDatabase.class,
                "servant-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations(RoomMigrations.MIGRATION_2_3)
                .build();

        // Update the database if necessary.

        final int currVersion = prefs.getInt("database_version", 0);

        DatabaseUpdater updaterTask = new DatabaseUpdater(this, this, database.servantDao());
        updaterTask.execute(currVersion);
    }

    public void onDatabaseUpdated(int newVersion) {

        editor.putInt("database_version", newVersion);
        editor.apply();

        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... unused) {
                ServantRepository.getInstance().setBackingDatabase(database);
                return null;
            }
            protected void onPostExecute(Void unused) {
                Intent intent = new Intent(a, AllServantsActivity.class);
                startActivity(intent);
                finish();
            }
        }.execute();
    }

}
