package world.arshad.grandordercompanion.needed_ascension_materials;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;

public class NeededAscensionMaterialsActivity extends SidebarActivity {

    @BindView(R.id.needed_materials_list) RecyclerView materialList;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private NeededAscensionMaterialViewModel mViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private NeededMaterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed_ascension_materials);

        super.setUpSidebar();

        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(NeededAscensionMaterialViewModel.class);
        refreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
        toolbar.setTitle("Needed Materials");
    }

    private void refreshData() {
        adapter = new NeededMaterialAdapter(mViewModel.getEntries(), this);
        materialList.setAdapter(adapter);
        materialList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        materialList.setLayoutManager(layoutManager);
    }
}
