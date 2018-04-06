package world.arshad.grandordercompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import world.arshad.grandordercompanion.all_servants.AllServantsActivity;

public class SplashActivity extends AppCompatActivity {

    private Activity a = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Update the database if necessary.

        SharedPreferences prefs = getSharedPreferences("goc", MODE_PRIVATE);
        int currVersion = prefs.getInt("database_contents_version", 0);

        DatabaseUpdater updaterTask = new DatabaseUpdater(a);
        updaterTask.execute(currVersion);
    }

    public void onDatabaseUpdated(int newVersion) {

        SharedPreferences prefs = getSharedPreferences("goc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("database_contents_version", newVersion);
        editor.apply();

        Intent intent = new Intent(this, AllServantsActivity.class);
        startActivity(intent);
        finish();
    }


}
