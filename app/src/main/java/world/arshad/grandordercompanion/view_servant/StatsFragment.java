package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;

/**
 *
 */
public class StatsFragment extends Fragment {

    @BindView(R.id.stats_gamepress_button)
    FloatingActionButton gamepressButton;

    @BindView(R.id.stats_list)
    RecyclerView statsList;

    private ServantViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ServantViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        gamepressButton.setOnClickListener(view1 -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.getServant().getGamepressURL()));
            startActivity(browserIntent);
        });

        statsList.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (0 < dy)
                    gamepressButton.hide();
                else if (0 > dy)
                    gamepressButton.show();
            }
        });

        statsList.setHasFixedSize(true);
        statsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final StatsAdapter adapter = new StatsAdapter(viewModel.getServant());
        statsList.setAdapter(adapter);
    }
}
