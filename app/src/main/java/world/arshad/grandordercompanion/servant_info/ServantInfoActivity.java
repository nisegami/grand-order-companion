package world.arshad.grandordercompanion.servant_info;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.AscensionEntry;
import world.arshad.grandordercompanion.data.domain_data.Entry;
import world.arshad.grandordercompanion.data.domain_data.SkillUpEntry;
import world.arshad.grandordercompanion.utils.Utilities;

public class ServantInfoActivity extends AppCompatActivity {

    @BindView(R.id.servant_info_name)
    Toolbar servantName;

    @BindView(R.id.servant_info_attack_value)
    TextView attackValue;

    @BindView(R.id.servant_info_hp_value)
    TextView hpValue;

    @BindView(R.id.servant_info_image)
    ImageView servantImage;

    @BindView(R.id.servant_info_class_image)
    ImageView classImage;

    @BindView(R.id.servant_info_ascension_entries)
    RecyclerView ascensionEntryList;

    @BindView(R.id.servant_info_skill_up_entries)
    RecyclerView skillUpEntryList;

    private EntryAdapter ascensionAdapter;
    private EntryAdapter skillUpAdapter;

    private ServantInfoViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant_info);

        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(ServantInfoViewModel.class);

        mViewModel.setServant((getIntent().getExtras().getParcelable("servant")));

        servantImage.setImageDrawable(Utilities.loadImageFromStorage(mViewModel.getServant().getFullImageURL(4), this.getAssets()));
        classImage.setImageDrawable(Utilities.loadImageFromStorage(mViewModel.getServant().getServantClass().getIconURL(), this.getAssets()));

        servantName.setTitle(mViewModel.getServant().getName());
        attackValue.setText(String.format("%d / %d", mViewModel.getServant().getBaseAttack(), mViewModel.getServant().getMaxAttack()));
        hpValue.setText(String.format("%d / %d", mViewModel.getServant().getBaseHp(), mViewModel.getServant().getMaxHp()));

        List<Object> ascensionItems = new ArrayList<>();

        List<List<AscensionEntry>> ascensionEntries = mViewModel.getServant().getAscensionEntries();

        if (ascensionEntries.isEmpty()) {
            ascensionEntryList.setVisibility(View.INVISIBLE);
        } else {
            for (int i = 0; i < 4; i++) {
                final int x = i;
                ascensionItems.add(new EntryAdapter.EntryParent(String.format("%s : %d", "Ascension", i + 1), new EntryAdapter.EntryTrackInterface() {
                    @Override
                    public void track(Context context) {
                        ascensionEntries.get(x).get(0).trackThisEntry(context);
                    }
                }));
                ascensionItems.addAll(ascensionEntries.get(i));
            }

            ascensionAdapter = new EntryAdapter(this, ascensionItems);
            ascensionEntryList.setAdapter(ascensionAdapter);
            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
            ascensionEntryList.setLayoutManager(layoutManager1);
        }


        List<Object> skillUpItems = new ArrayList<>();

        List<List<SkillUpEntry>> skillEntries = mViewModel.getServant().getSkillUpEntries();

        for (int i = 0; i < 9; i++) {
            final int x = i;
            skillUpItems.add(new EntryAdapter.EntryParent(String.format("%s : %d", "Skill Up", i + 2, i + 1), new EntryAdapter.EntryTrackInterface() {
                @Override
                public void track(Context context) {
                    skillEntries.get(x).get(0).trackThisEntry(context);
                }
            }));
            skillUpItems.addAll(skillEntries.get(i));
        }

        skillUpAdapter = new EntryAdapter(this, skillUpItems);
        skillUpEntryList.setAdapter(skillUpAdapter);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        skillUpEntryList.setLayoutManager(layoutManager2);

        setSupportActionBar(servantName);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
