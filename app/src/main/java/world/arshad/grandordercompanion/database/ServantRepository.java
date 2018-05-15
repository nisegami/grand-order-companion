package world.arshad.grandordercompanion.database;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return ServantRepository.ourInstance;
    }

    private ServantRepository() {
    }

    /*.....................................................*/

    private ServantDatabase database;

    public void setDatabase(ServantDatabase database) {
        this.database = database;
    }

    @SuppressLint("UseSparseArrays")
    public void populate() {
        servantCache = new HashMap<>();
        for (Servant servant : getDao().getAllServants()) {
            servantCache.put(servant.getId(), servant);
        }
    }

    private void invalidateEntriesCache() {
        trackedAscensions = null;
        trackedSkillUps = null;
    }

    public ServantDatabase getDatabase() {
        return database;
    }

    public ServantDatabaseInterface getDao() {
        return database.servantDao();
    }

    /*.....................................................*/

    private Map<Integer, Servant> servantCache;
    private List<Ascension> trackedAscensions = null;
    private List<SkillUp> trackedSkillUps = null;


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
        if ((Ascension.TRACKED == type) && (null != trackedAscensions)) {
            return trackedAscensions;
        } else {
            return getDao().getAscensionsOfType(type);
        }
    }

    @Override
    public List<Ascension> getAscensionsForServant(int servant_id) {
        return getDao().getAscensionsForServant(servant_id);
    }

    @Override
    public void updateAscension(Ascension ascension) {
        invalidateEntriesCache();
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
        if ((SkillUp.TRACKED == type) && (null != trackedSkillUps)) {
            return trackedSkillUps;
        } else {
            return getDao().getSkillUpsOfType(type);
        }
    }

    @Override
    public List<SkillUp> getSkillUpsForServantAndSkillNumber(int servant_id, int skill_number) {
        return getDao().getSkillUpsForServantAndSkillNumber(servant_id, skill_number);
    }

    @Override
    public void updateSkillUp(SkillUp skillUp) {
        invalidateEntriesCache();
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
