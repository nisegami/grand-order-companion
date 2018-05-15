package world.arshad.grandordercompanion.needed_materials;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.model.Material;

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
        adapter = new NeededMaterialAdapter(this);
        neededMaterialsList.setAdapter(adapter);
        neededMaterialsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setAutoMeasureEnabled(false); // Idk what this is for but im not touching it
        neededMaterialsList.setLayoutManager(llm);
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.refreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadDataTask().execute();
    }

    private class LoadDataTask extends AsyncTask<Void, Void, Map<Material, NeededMaterialEntry>> {
        protected void onPreExecute() {
            neededMaterialsList.setVisibility(View.INVISIBLE);
        }

        protected Map<Material, NeededMaterialEntry> doInBackground(Void ... params) {
            return viewModel.getItems();
        }

        protected void onPostExecute(Map<Material, NeededMaterialEntry> items) {
            adapter.setData(items);
            neededMaterialsList.setVisibility(View.VISIBLE);
        }
    }
}
