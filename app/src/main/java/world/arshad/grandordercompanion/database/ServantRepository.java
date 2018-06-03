package world.arshad.grandordercompanion.database;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

/**
 * Created by arsha on 19/03/2018.
 */

public final class ServantRepository implements ServantDatabaseInterface {

    private static final ServantRepository ourInstance = new ServantRepository();

    public static ServantRepository getInstance() {
        return ourInstance;
    }

    private ServantRepository() {
    }

    /*.....................................................*/

    private ServantDatabaseInterface backingDatabase;

    @SuppressLint("UseSparseArrays")
    public void setBackingDatabase(ServantDatabase database) {
        this.backingDatabase = database.servantDao();
        servantCache = new HashMap<>();
        for (Servant servant : getDao().getAllServants()) {
            servantCache.put(servant.getId(), servant);
        }
    }

    public ServantDatabaseInterface getDao() {
        return backingDatabase;
    }

    /*.....................................................*/

    private Map<Integer, Servant> servantCache;

    @Override
    public List<Servant> getAllServants() {
        return new ArrayList<>(servantCache.values());
    }

    @Override
    public Servant getServant(int servantId) {
        return servantCache.get(servantId);
    }

    @Override
    public String getServantNameFromId(int servantId) {
        return servantCache.get(servantId).getName();
    }

    @Override
    public void updateServant(Servant servant) {
        servantCache.put(servant.getId(), servant);
        getDao().updateServant(servant);
    }

    /*.....................................................*/

    @Override
    public List<Ascension> getAscensions() {
        return getDao().getAscensions();
    }

    @Override
    public List<Ascension> getAscensionsOfType(int type) {
        return getDao().getAscensionsOfType(type);
    }

    @Override
    public List<Ascension> getAscensionsForServant(int servant_id) {
        return getDao().getAscensionsForServant(servant_id);
    }

    @Override
    public void updateAscension(Ascension ascension) {
        getDao().updateAscension(ascension);
        servantCache.put(ascension.getServantId(), getDao().getServant(ascension.getServantId()));
    }

    @Override
    public List<AscensionEntry> getSpecificAscensionEntriesForServant(int servant_id, int ascension_number) {
        return getDao().getSpecificAscensionEntriesForServant(servant_id, ascension_number);
    }

    /*.....................................................*/

    @Override
    public List<SkillUp> getSkillUps() {
        return getDao().getSkillUps();
    }

    @Override
    public List<SkillUp> getSkillUpsOfType(int type) {
        return getDao().getSkillUpsOfType(type);
    }

    @Override
    public List<SkillUp> getSkillUpsForServantAndSkillNumber(int servant_id, int skill_number) {
        return getDao().getSkillUpsForServantAndSkillNumber(servant_id, skill_number);
    }

    @Override
    public void updateSkillUp(SkillUp skillUp) {
        getDao().updateSkillUp(skillUp);
        servantCache.put(skillUp.getServantId(), getDao().getServant(skillUp.getServantId()));
    }

    @Override
    public List<SkillUpEntry> getSpecificSkillUpEntriesForServant(int servant_id, int dest_skill_level) {
        return getDao().getSpecificSkillUpEntriesForServant(servant_id, dest_skill_level);
    }

    /*.....................................................*/

    @Override
    public void insertAllServants(List<Servant> servants) {
        getDao().insertAllServants(servants);
    }

    @Override
    public void insertAllAscensions(List<Ascension> ascensions) {
        getDao().insertAllAscensions(ascensions);
    }

    @Override
    public void insertAllAscensionEntries(List<AscensionEntry> entries) {
        getDao().insertAllAscensionEntries(entries);
    }

    @Override
    public void insertAllSkillUps(List<SkillUp> skillUps) {
        getDao().insertAllSkillUps(skillUps);
    }

    @Override
    public void insertAllSkillUpEntries(List<SkillUpEntry> entries) {
        getDao().insertAllSkillUpEntries(entries);
    }
}
