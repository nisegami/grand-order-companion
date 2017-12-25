package world.arshad.grandordercompanion.servant_info_list;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;

public class ServantInfoListActivity extends AppCompatActivity {

    @BindView(R.id.servant_info_list) RecyclerView servantInfoList;
    private ServantInfoViewModel mViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private ServantInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant_info_list);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(ServantInfoViewModel.class);

        adapter = new ServantInfoAdapter(mViewModel.getServants());
        servantInfoList.setAdapter(adapter);
        servantInfoList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        servantInfoList.setLayoutManager(layoutManager);
    }
}
