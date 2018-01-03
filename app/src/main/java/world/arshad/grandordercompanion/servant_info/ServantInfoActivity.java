package world.arshad.grandordercompanion.servant_info;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;
import world.arshad.grandordercompanion.utils.Utilities;

public class ServantInfoActivity extends AppCompatActivity {

    @BindView(R.id.servant_name)
    TextView servantName;

    @BindView(R.id.attack_value)
    TextView attackValue;

    @BindView(R.id.hp_value)
    TextView hpValue;

    @BindView(R.id.servant_image)
    ImageView servantImage;

    @BindView(R.id.class_image)
    ImageView classImage;

    @BindView(R.id.ascension_entries)
    RecyclerView ascensionEntryList;

    @BindView(R.id.skill_up_entries)
    RecyclerView skillUpEntryList;


    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.LayoutManager layoutManager2;
    private EntryAdapter ascensionAdapter;
    private EntryAdapter skillUpAdapter;

    private ServantInfoViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant_info);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(ServantInfoViewModel.class);

        mViewModel.setServant((ServantInfo) (getIntent().getExtras().getParcelable("servant")));

        servantImage.setImageBitmap(Utilities.loadImageFromStorage(getFilesDir() + mViewModel.getServant().getFullImageURL()));
        classImage.setImageBitmap(Utilities.loadImageFromStorage(getFilesDir() + mViewModel.getServant().getServantClass().getIconURL()));

        servantName.setText(mViewModel.getServant().getName());
        attackValue.setText(String.format("%d / %d", mViewModel.getServant().getBaseAttack(), mViewModel.getServant().getMaxAttack()));
        hpValue.setText(String.format("%d / %d", mViewModel.getServant().getBaseHp(), mViewModel.getServant().getMaxHp()));

        ascensionAdapter = new EntryAdapter(mViewModel.getServant().getAscensionEntries(), this);
        ascensionEntryList.setAdapter(ascensionAdapter);
        ascensionEntryList.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        ascensionEntryList.setLayoutManager(layoutManager1);

        skillUpAdapter = new EntryAdapter(mViewModel.getServant().getSkillUpEntries(), this);
        skillUpEntryList.setAdapter(skillUpAdapter);
        skillUpEntryList.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(this);
        skillUpEntryList.setLayoutManager(layoutManager2);
    }
}
