package world.arshad.grandordercompanion.view_servant;

import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;

/**
 *
 */
public class ServantActivity extends FragmentActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.servant_pager)
    ViewPager pager;

    @BindView(R.id.servant_pager_title_strip)
    PagerSlidingTabStrip titleStrip;

    private ServantViewModel viewModel;
    private ServantPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant);
        ButterKnife.bind(this);
        setActionBar(toolbar);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ServantViewModel.class);
        viewModel.setID(getIntent().getIntExtra("servant_id", 1));

        setTitle(viewModel.getServant().toString());

        adapter = new ServantPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        titleStrip.setTextSize(45);
        titleStrip.setViewPager(pager);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // The following code is useful, but I found it frustrating to use.
//        if (pager.getCurrentItem() == 0) {
//            super.onBackPressed();
//        } else {
//            pager.setCurrentItem(pager.getCurrentItem() - 1);
//        }
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

}
