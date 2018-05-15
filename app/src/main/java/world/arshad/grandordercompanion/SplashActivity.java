package world.arshad.grandordercompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import world.arshad.grandordercompanion.all_servants.AllServantsActivity;
import world.arshad.grandordercompanion.database.DatabaseUpdater;
import world.arshad.grandordercompanion.database.ServantRepository;

public class SplashActivity extends AppCompatActivity implements DatabaseUpdater.PostUpdateCallback {

    private Activity a = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Update the database if necessary.

        SharedPreferences prefs = getSharedPreferences("goc", Context.MODE_PRIVATE);
        int currVersion = prefs.getInt("database_version", 0);

        DatabaseUpdater updaterTask = new DatabaseUpdater(this, this, ServantRepository.getInstance().getDatabase().servantDao());
        updaterTask.execute(currVersion);
    }

    public void onDatabaseUpdated(int newVersion) {

        SharedPreferences prefs = getSharedPreferences("goc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("database_version", newVersion);
        editor.apply();

        ServantRepository.getInstance().populate();

        Intent intent = new Intent(this, AllServantsActivity.class);
        startActivity(intent);
        finish();
    }


}
