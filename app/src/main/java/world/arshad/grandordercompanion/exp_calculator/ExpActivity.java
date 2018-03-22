package world.arshad.grandordercompanion.exp_calculator;

import android.os.Bundle;

import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;

public class ExpActivity extends SidebarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);
    }
}
