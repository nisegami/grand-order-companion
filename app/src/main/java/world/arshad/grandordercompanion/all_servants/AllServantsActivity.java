package world.arshad.grandordercompanion.all_servants;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import br.com.liveo.searchliveo.SearchLiveo;
import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
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

    SharedPreferences prefs;

    private boolean showThumbnails;

    private AllServantsViewModel viewModel;
    private ServantAdapter adapter;

    String[] sortOptions = {"ID", "Name", "Class", "Rarity", "Attack", "HP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_servants);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = prefs.edit();

        // A few people have asked for this, so I'm informing them.
        if (!prefs.getBoolean("thumbnail_setting_dialog_shown", false)) {
            android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(a)
                    .setTitle("New Setting")
                    .setMessage("You can now disable servant thumbnails in the settings menu (in the sidebar).")
                    .create();
            alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "Dismiss",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            editor.putBoolean("thumbnail_setting_dialog_shown", true);
            editor.apply();
            alertDialog.show();
        }

        showThumbnails = prefs.getBoolean("pref_thumbs", true);

        searchLiveo
                .with(this)
                .removeMinToSearch()
                .removeSearchDelay()
                .hideSearch(() -> searchButton.show())
                .build();

        searchLiveo.hide();

        viewModel = ViewModelProviders.of(this).get(AllServantsViewModel.class);

        servantInfoList.setHasFixedSize(true);
        servantInfoList.setLayoutManager(new LinearLayoutManager(this));

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

        updateAdapter();
    }

    private void updateAdapter() {
        servantInfoList.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        if (showThumbnails) {
            adapter = new ServantAdapterWithThumbnails(this);
        } else {
            adapter = new ServantAdapterNoThumbnails(this);
        }

        servantInfoList.setAdapter(adapter);
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

        if (showThumbnails != prefs.getBoolean("pref_thumbs", true)) {
            showThumbnails = !showThumbnails;
            updateAdapter();
        }

        searchButton.show();
    }
}
