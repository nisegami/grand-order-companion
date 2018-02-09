package world.arshad.grandordercompanion.tracked_ascension_management;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;
import world.arshad.grandordercompanion.data.user_data.TrackedAscension;

public class TrackedAscensionsActivity extends SidebarActivity {


    @BindView(R.id.tracked_ascensions_recycler_view)
    RecyclerView trackedAscensionRecyclerView;

    private TrackedAscensionsViewModel mViewModel;
    private TrackedAscensionAdapter trackedAscensionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_ascensions);

        super.setUpSidebar();

        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(TrackedAscensionsViewModel.class);

        List<TrackedAscensionAdapter.TrackedAscensionParent> trackedAscensionParents = new ArrayList<>();
        List<TrackedAscension> trackedAscensions = mViewModel.getTrackedAscensions();

        int i = 0;
        for (TrackedAscension trackedAscension : trackedAscensions) {
            trackedAscensionParents.add(i, new TrackedAscensionAdapter.TrackedAscensionParent(trackedAscension));
        }

        trackedAscensionAdapter = new TrackedAscensionAdapter(this, trackedAscensionParents);
        trackedAscensionRecyclerView.setAdapter(trackedAscensionAdapter);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        trackedAscensionRecyclerView.setLayoutManager(layoutManager1);
    }
}