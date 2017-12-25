package world.arshad.grandordercompanion.servant_info_list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import java.util.List;

import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSource;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;

/**
 * Created by arsha on 22/12/2017.
 */

public class ServantInfoViewModel extends AndroidViewModel {
    private List<ServantInfo> servants;
    private final Context mContext;

    public ServantInfoViewModel(
            Application context) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
    }

    public List<ServantInfo> getServants() {
        if (servants == null) {
            servants = DomainDataSource.getServants(mContext.getResources());
        }

        return servants;
    }
}
