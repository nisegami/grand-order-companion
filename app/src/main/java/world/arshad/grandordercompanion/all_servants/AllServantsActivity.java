package world.arshad.grandordercompanion.all_servants;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.List;

import br.com.liveo.searchliveo.SearchLiveo;
import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.SplashActivity;
import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.model.Servant;

public class AllServantsActivity extends SidebarActivity implements SearchLiveo.OnSearchListener {

    @BindView(R.id.servant_list)
    RecyclerView servantInfoList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.servant_info_sort_button)
    ImageButton sortButton;

    @BindView(R.id.servant_info_reverse_button)
    ImageButton reverseButton;

    @BindView(R.id.servants_progressbar)
    ProgressBar progressBar;

    @BindView(R.id.servant_list_search)
    SearchLiveo searchLiveo;

    @BindView(R.id.servant_list_fab)
    FloatingActionButton searchButton;

    private AllServantsViewModel viewModel;
    private ServantAdapter adapter;

    String[] sortOptions = {"ID", "Name", "Class", "Rarity", "Attack", "HP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_servants);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);

        searchLiveo
                .with(this)
                .removeMinToSearch()
                .removeSearchDelay()
                .hideSearch(() -> searchButton.show())
                .build();

        searchLiveo.hide();

        viewModel = ViewModelProviders.of(this).get(AllServantsViewModel.class);

        adapter = new ServantAdapter(this);
        servantInfoList.setAdapter(adapter);
        servantInfoList.setHasFixedSize(true);
        servantInfoList.setLayoutManager(new LinearLayoutManager(this));

        updateAdapter();

        sortButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setItems(sortOptions, (dialogInterface, j) -> {
                viewModel.sortItems(Servant.Comps.valueOf(sortOptions[j].toUpperCase()));
                updateAdapter();
            });
            builder.create().show();
        });

        reverseButton.setOnClickListener(view -> {
            viewModel.reverseItems();
            updateAdapter();
            reverseButton.setImageDrawable(viewModel.getReverseButtonDrawable());
        });

        reverseButton.setImageDrawable(viewModel.getReverseButtonDrawable());

        searchButton.setOnClickListener(view -> {
            searchLiveo.show();
            searchButton.hide();
        });

        servantInfoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (0 < dy) {
                    if (searchButton.isShown()) {
                        searchButton.hide();
                    }
                }
                else if (0 > dy) {
                    if (!searchButton.isShown()) {
                        searchButton.show();
                    }
                }
            }
        });
    }

    private void updateAdapter() {
        servantInfoList.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        adapter.setData(viewModel.getServants());
        servantInfoList.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void changedSearch(CharSequence text) {
        viewModel.filterItems(text);
        updateAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        searchButton.show();
    }
}
