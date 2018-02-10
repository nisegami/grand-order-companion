package world.arshad.grandordercompanion.tracked_skill_up_management;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;

public class TrackedSkillUpsActivity extends SidebarActivity {


    @BindView(R.id.tracked_skill_ups_recycler_view)
    RecyclerView trackedSkillUpRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private TrackedSkillUpsViewModel mViewModel;
    private TrackedSkillUpAdapter trackedSkillUpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracked_skill_ups);

        super.setUpSidebar();

        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(TrackedSkillUpsViewModel.class);
        refreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
        toolbar.setTitle("Tracked Skill Ups");
    }

    void refreshData() {
        List<TrackedSkillUpAdapter.TrackedSkillUpParent> trackedSkillUpParents = new ArrayList<>();
        List<TrackedSkillUp> trackedSkillUps = mViewModel.getTrackedSkillUps();

        int i = 0;
        for (TrackedSkillUp trackedSkillUp : trackedSkillUps) {
            trackedSkillUpParents.add(i, new TrackedSkillUpAdapter.TrackedSkillUpParent(trackedSkillUp));
        }

        trackedSkillUpAdapter = new TrackedSkillUpAdapter(this, trackedSkillUpParents);
        trackedSkillUpRecyclerView.setAdapter(trackedSkillUpAdapter);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        trackedSkillUpRecyclerView.setLayoutManager(layoutManager1);
    }
}