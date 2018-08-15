package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;

public class SkillUpsFragment extends Fragment {

    @BindView(R.id.skill_up_list)
    RecyclerView skillUpList;

    @BindView(R.id.skill_fab)
    SpeedDialView skillFab;

    private SkillUpAdapter adapter;

    private ServantViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ServantViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skill_ups, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        skillUpList.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if (0 < dy)
                    skillFab.hide();
                else if (0 > dy)
                    skillFab.show();
            }
        });

        skillFab.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_skill_1, R.drawable.ic_filter_1_black_24dp)
                        .setLabel(viewModel.getServant().getSkill1())
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(Color.BLACK)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), android.R.color.black, getActivity().getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), android.R.color.white, getActivity().getTheme()))
                        .create()
        );

        skillFab.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_skill_2, R.drawable.ic_filter_2_black_24dp)
                        .setLabel(viewModel.getServant().getSkill2())
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(Color.BLACK)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), android.R.color.black, getActivity().getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), android.R.color.white, getActivity().getTheme()))
                        .create()
        );

        skillFab.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_skill_3, R.drawable.ic_filter_3_black_24dp)
                        .setLabel(viewModel.getServant().getSkill3())
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(Color.BLACK)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), android.R.color.black, getActivity().getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), android.R.color.white, getActivity().getTheme()))
                        .create()
        );

        skillFab.setOnChangeListener(new SpeedDialView.OnChangeListener() {
            @Override
            public boolean onMainActionSelected() {
                return false;
            }

            @Override
            public void onToggleChanged(boolean isOpen) {
            }
        });

        skillFab.setOnActionSelectedListener(speedDialActionItem -> {
            switch (speedDialActionItem.getId()) {
                case R.id.fab_skill_1:
                    adapter.setData(viewModel.getServant(), 1);
                    skillFab.close();
                    break;
                case R.id.fab_skill_2:
                    adapter.setData(viewModel.getServant(), 2);
                    skillFab.close();
                    break;
                case R.id.fab_skill_3:
                    adapter.setData(viewModel.getServant(), 3);
                    skillFab.close();
                    break;
            }
            return false;
        });

        skillUpList.setHasFixedSize(true);
        skillUpList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SkillUpAdapter(getActivity());
        adapter.setData(viewModel.getServant(), 1);
        skillUpList.setAdapter(adapter);
    }
}
