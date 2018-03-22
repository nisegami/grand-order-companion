package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.Servant;

public class SkillUpsFragment extends Fragment {

    @BindView(R.id.skill_up_list)
    RecyclerView skillUpList;

    @BindView(R.id.skill_fab)
    FloatingActionButton skillFab;

//    @BindView(R.id.skill_1_button)
//    Button skillUp1Button;
//
//    @BindView(R.id.skill_2_button)
//    Button skillUp2Button;
//
//    @BindView(R.id.skill_3_button)
//    Button skillUp3Button;

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
                if (dy > 0)
                    skillFab.hide();
                else if (dy < 0)
                    skillFab.show();
            }
        });

        skillFab.setOnClickListener(view1 -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setItems(viewModel.getSkillNames(), (dialogInterface, j) -> {
                adapter.setData(viewModel.getServant(), j + 1);
            });
            builder.create().show();
        });

        adapter = new SkillUpAdapter(getActivity());

        adapter.setData(viewModel.getServant(), 1);
        skillUpList.setNestedScrollingEnabled(false);
        skillUpList.setAdapter(adapter);
        skillUpList.setHasFixedSize(true);
        skillUpList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
