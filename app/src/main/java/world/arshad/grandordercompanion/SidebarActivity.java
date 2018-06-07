package world.arshad.grandordercompanion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import world.arshad.grandordercompanion.all_servants.AllServantsActivity;
import world.arshad.grandordercompanion.all_servants.ModelActivity;
import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.needed_materials.NeededMaterialsActivity;
import world.arshad.grandordercompanion.tracked_entries.TrackedEntriesActivity;

/**
 * Created by arsha on 20/03/2018.
 */

public abstract class SidebarActivity extends ModelActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected Activity a = this;
    protected Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setupSidebar(int id) {
        Toolbar toolbar = findViewById(id);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navigationView = findViewById(R.id.nav_view);
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Switch to another activity
     * @param cls The class of the activity to be displayed
     */
    public void showScreen(Class cls) {
        Intent in = new Intent(this, cls);
        startActivity(in);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        switch (id) {
            case R.id.nav_servant_list:
                showScreen(AllServantsActivity.class);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_needed_materials:
                showScreen(NeededMaterialsActivity.class);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tracked_entries:
                showScreen(TrackedEntriesActivity.class);
                drawer.closeDrawer(GravityCompat.START);
                break;
            default:
                drawer.closeDrawer(GravityCompat.START);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
