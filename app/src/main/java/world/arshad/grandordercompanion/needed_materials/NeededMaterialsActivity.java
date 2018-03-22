package world.arshad.grandordercompanion.needed_materials;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;

public class NeededMaterialsActivity extends SidebarActivity {

    @BindView(R.id.needed_material_list)
    RecyclerView neededMaterialsList;

    private NeededMaterialAdapter adapter;
    private NeededMaterialsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed_materials);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(NeededMaterialsViewModel.class);

        adapter = new NeededMaterialAdapter(this, viewModel.getCounts(), viewModel.getStrings());
        neededMaterialsList.setAdapter(adapter);
        neededMaterialsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setAutoMeasureEnabled(false);
        neededMaterialsList.setLayoutManager(llm);
    }
}
