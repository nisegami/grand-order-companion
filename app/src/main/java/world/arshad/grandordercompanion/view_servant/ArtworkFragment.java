package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.Utilities;

public class ArtworkFragment extends Fragment {

    @BindView(R.id.artwork_1)
    ImageView artwork1;

    @BindView(R.id.artwork_2)
    ImageView artwork2;

    @BindView(R.id.artwork_3)
    ImageView artwork3;

    @BindView(R.id.artwork_4)
    ImageView artwork4;

    private ServantViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ServantViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artwork, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        artwork1.setImageDrawable(Utilities.loadDrawableFromAssets(viewModel.getServant().getArtworkPath(1), getActivity().getAssets()));
        artwork2.setImageDrawable(Utilities.loadDrawableFromAssets(viewModel.getServant().getArtworkPath(2), getActivity().getAssets()));
        artwork3.setImageDrawable(Utilities.loadDrawableFromAssets(viewModel.getServant().getArtworkPath(3), getActivity().getAssets()));
        artwork4.setImageDrawable(Utilities.loadDrawableFromAssets(viewModel.getServant().getArtworkPath(4), getActivity().getAssets()));
    }

}
