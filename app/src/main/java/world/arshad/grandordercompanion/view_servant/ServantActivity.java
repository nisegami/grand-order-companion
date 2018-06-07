package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.all_servants.ModelActivity;
import world.arshad.grandordercompanion.model.Servant;

/**
 *
 */
public class ServantActivity extends ModelActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.servant_pager)
    ViewPager pager;

    @BindView(R.id.servant_pager_title_strip)
    PagerSlidingTabStrip titleStrip;

    public static final int ASCENSION = 1;
    public static final int SKILL_UP = 2;

    private Context c = this;
    private ServantViewModel viewModel;
    private ServantPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ServantViewModel.class);
        viewModel.setID(getIntent().getIntExtra("servant_id", 1));

        Servant servant = viewModel.getServant();

        setTitle(servant.toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                adapter = new ServantPagerAdapter(getSupportFragmentManager());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pager.setAdapter(adapter);

                        titleStrip.setTextSize(45);
                        titleStrip.setViewPager(pager);

                        pager.setBackgroundColor(servant.getColor());
                        pager.setCurrentItem(getIntent().getIntExtra("screen_number", 0));

                        pager.setPageTransformer(true, new DepthPageTransformer());
                        pager.setOffscreenPageLimit(3);
                    }
                });
            }
        }).start();
    }

    private class ServantPagerAdapter extends FragmentPagerAdapter {
        public ServantPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new StatsFragment();
                case 1: return new AscensionsFragment();
                case 2: return new SkillUpsFragment();
                case 3: return new ArtworkFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Stats";
                case 1: return "Ascensions";
                case 2: return "Skill Ups";
                case 3: return "Artwork";
            }
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
