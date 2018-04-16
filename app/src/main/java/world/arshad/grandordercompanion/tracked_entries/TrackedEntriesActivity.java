package world.arshad.grandordercompanion.tracked_entries;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.Servant;

public class TrackedEntriesActivity extends SidebarActivity {

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

        adapter = new TrackedEntryAdapter(this);
        trackedEntryList.setAdapter(adapter);
        trackedEntryList.setHasFixedSize(true);
        trackedEntryList.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refreshData();
            new LoadDataTask().execute();
        });
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

    private class LoadDataTask extends AsyncTask<Void, Void, List<Object>> {
        protected void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);
            trackedEntryList.setVisibility(View.INVISIBLE);
        }

        protected List<Object> doInBackground(Void ... params) {
            return viewModel.getItems();
        }

        protected void onPostExecute(List<Object> items) {
            adapter.setData(items);
            swipeRefreshLayout.setRefreshing(false);
            trackedEntryList.setVisibility(View.VISIBLE);
        }
    }
}
