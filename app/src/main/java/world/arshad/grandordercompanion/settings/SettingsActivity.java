package world.arshad.grandordercompanion.settings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;

public class SettingsActivity extends SidebarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.setupSidebar(R.id.toolbar);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.setting_fragment, SettingsFrag.newInstance()).commit();
    }
}
