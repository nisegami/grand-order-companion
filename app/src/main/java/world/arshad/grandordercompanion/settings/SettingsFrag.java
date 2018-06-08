package world.arshad.grandordercompanion.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import world.arshad.grandordercompanion.R;

public class SettingsFrag extends PreferenceFragmentCompat {

    public void onCreatePreferences(Bundle savedInstanceState, String str) {
        addPreferencesFromResource(R.xml.prefs);
    }


    public SettingsFrag() {
    }

    public static SettingsFrag newInstance() {
        SettingsFrag fragment = new SettingsFrag();
        return fragment;
    }


}
