package world.arshad.grandordercompanion.servant_info_list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;
import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;

public class ServantInfoListActivity extends SidebarActivity {

    @BindView(R.id.servant_info_list)
    RecyclerView servantInfoList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.servant_info_sort_button)
    ImageButton sortButton;

    @BindView(R.id.servant_info_reverse_button)
    ImageButton reverseButton;

    private ServantInfoListViewModel mViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private ServantInfoAdapter adapter;
    private final Context c = this;

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

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setItems(new CharSequence[]{"ID", "Name", "Class", "Rarity"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        adapter.sortItems(j);
                    }
                });
                builder.create().show();
            }
        });

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.reverseItems();
                reverseButton.setImageDrawable(adapter.getReverseButtonDrawable());
            }
        });

        reverseButton.setImageDrawable(adapter.getReverseButtonDrawable());
    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar.setTitle("All Servants");
    }
}
