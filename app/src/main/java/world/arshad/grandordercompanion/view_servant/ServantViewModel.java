package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModel;

import world.arshad.grandordercompanion.data.Model;
import world.arshad.grandordercompanion.data.Servant;

/**
 * Created by arsha on 20/03/2018.
 */

public class ServantViewModel extends ViewModel {
    private Servant servant;
    private int servantId = -1;
    private String[] skillNames = new String[3];

    public void setID(int servantId) {
        this.servantId = servantId;
    }

    /**
     * Call this at the start of any method that is working with the data.
     */
    private void fetchData(){
        if (servantId < 1) {
            throw new RuntimeException("Servant ID not defined!");
        }
        if (servant == null) {
            servant = Model.getInstance().getDatabase().servantDao().getServant(servantId);
            skillNames[0] = servant.getSkill1();
            skillNames[1] = servant.getSkill2();
            skillNames[2] = servant.getSkill3();
        }
    }

    public void refreshServant() {
        servant = null;
    }

    public Servant getServant() {
        fetchData();
        return servant;
    }

    public String[] getSkillNames() {
        return skillNames;
    }
}
