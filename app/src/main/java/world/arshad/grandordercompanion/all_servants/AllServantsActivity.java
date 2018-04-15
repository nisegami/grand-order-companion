package world.arshad.grandordercompanion.all_servants;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.Servant;

public class AllServantsActivity extends SidebarActivity {

    @BindView(R.id.servant_list)
    RecyclerView servantInfoList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.servant_info_sort_button)
    ImageButton sortButton;

    @BindView(R.id.servant_info_reverse_button)
    ImageButton reverseButton;

    private AllServantsViewModel viewModel;
    private ServantAdapter adapter;

    String[] sortOptions = {"ID", "Name", "Class", "Rarity", "Attack", "HP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_servants);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(AllServantsViewModel.class);

        adapter = new ServantAdapter(viewModel.getServants(), this);
        servantInfoList.setAdapter(adapter);
        servantInfoList.setHasFixedSize(true);
        servantInfoList.setLayoutManager(new LinearLayoutManager(this));

        sortButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setItems(sortOptions, (dialogInterface, j) -> {
                viewModel.sortItems(Servant.Comps.valueOf(sortOptions[j].toUpperCase()));
                adapter.setData(viewModel.getServants());
            });
            builder.create().show();
        });

        reverseButton.setOnClickListener(view -> {
            viewModel.reverseItems();
            adapter.setData(viewModel.getServants());
            reverseButton.setImageDrawable(viewModel.getReverseButtonDrawable());
        });

        reverseButton.setImageDrawable(viewModel.getReverseButtonDrawable());
    }
}
