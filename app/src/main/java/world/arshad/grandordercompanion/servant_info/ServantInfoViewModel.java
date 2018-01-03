package world.arshad.grandordercompanion.servant_info;

import android.arch.lifecycle.ViewModel;

import world.arshad.grandordercompanion.data.domain_data.ServantInfo;

/**
 * Created by arsha on 26/12/2017.
 */

public class ServantInfoViewModel extends ViewModel {
    private ServantInfo servant;

    public void setServant(ServantInfo servant) {
        this.servant = servant;
    }

    public ServantInfo getServant() {
        return servant;
    }
}
