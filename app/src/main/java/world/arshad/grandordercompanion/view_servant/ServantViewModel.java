package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModel;

import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.model.Servant;

/**
 * Created by arsha on 20/03/2018.
 */

public class ServantViewModel extends ViewModel {
    private Servant servant;
    private int servantId = -1;
    private String[] skillNames = new String[3];

    public void setID(int servantId) {
        this.servantId = servantId;

        if (1 > servantId) {
            throw new RuntimeException("Servant ID not defined!");
        }

        servant = ServantRepository.getInstance().getServant(servantId);
        skillNames[0] = servant.getSkill1();
        skillNames[1] = servant.getSkill2();
        skillNames[2] = servant.getSkill3();
    }

    public Servant getServant() {
        return servant;
    }

    public String[] getSkillNames() {
        return skillNames;
    }
}
