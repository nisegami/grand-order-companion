package world.arshad.grandordercompanion.tracked_entries;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;

public class TrackedEntriesActivity extends SidebarActivity implements ActivityWithRefresh {

    @BindView(R.id.tracked_entry_list_swipe)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tracked_entry_list)
    RecyclerView trackedEntryList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TrackedEntryViewModel viewModel;
    private TrackedEntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_entries);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(TrackedEntryViewModel.class);

        adapter = new TrackedEntryAdapter(this, this);
        trackedEntryList.setAdapter(adapter);
        trackedEntryList.setHasFixedSize(true);
        trackedEntryList.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            refresh();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void refresh() {
        viewModel.refreshData();
        adapter.setData(viewModel.getItems());
        swipeRefreshLayout.setRefreshing(false);
    }
}
