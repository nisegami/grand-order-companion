package world.arshad.grandordercompanion.servant_info_list;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;

public class ServantInfoListActivity extends SidebarActivity {

    @BindView(R.id.servant_info_list) RecyclerView servantInfoList;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private ServantInfoListViewModel mViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private ServantInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant_info_list);

        super.setUpSidebar();

        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(ServantInfoListViewModel.class);

        adapter = new ServantInfoAdapter(mViewModel.getServants(), this);
        servantInfoList.setAdapter(adapter);
        servantInfoList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        servantInfoList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("All Servants");
    }
}
