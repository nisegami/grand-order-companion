package world.arshad.grandordercompanion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Map;

import world.arshad.grandordercompanion.needed_ascension_materials.NeededAscensionMaterialsActivity;
import world.arshad.grandordercompanion.servant_info_list.ServantInfoListActivity;


/**
 * Created by arshad on 04/01/2018.
 */

public class SidebarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setUpSidebar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_servant_list) {
            showScreen(ServantInfoListActivity.class);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_needed_materials) {
            showScreen(NeededAscensionMaterialsActivity.class);
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }

        return false;
    }

    /**
     * Switch to another activity
     * @param cls The class of the activity to be displayed
     */
    protected void showScreen(Class cls) {
        Intent in = new Intent(this, cls);
        startActivity(in);
    }

    /**
     * Switch to another activity
     * @param cls The class of the activity to be displayed
     * @param arg_key the key of an argument to be passed along to the new activity
     * @param arg_value the value of an argument to be passed along to the new activity
     */
    protected void showScreen(Class cls, String arg_key, String arg_value) {
        Intent in = new Intent(this, cls);
        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        in.putExtra(arg_key, arg_value);
        startActivity(in);
    }

    /**
     * Switch to another activity
     * @param cls The class of the activity to be displayed
     * @param arg_key the key of an argument to be passed along to the new activity
     * @param arg_value the value of an argument to be passed along to the new activity
     */
    protected void showScreen(Class cls, String arg_key, Parcelable arg_value) {
        Intent in = new Intent(this, cls);
        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        in.putExtra(arg_key, arg_value);
        startActivity(in);
    }

    /**
     * Switch to another activity
     * @param cls The class of the activity to be displayed
     * @param args a mapping of labels to values to be passed in as extras
     */
    protected void showScreen(Class cls, Map<String, String> args) {
        Intent in = new Intent(this, cls);
        in.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        for(String key : args.keySet()) {
            in.putExtra(key, args.get(key));
        }
        startActivity(in);
    }


}
