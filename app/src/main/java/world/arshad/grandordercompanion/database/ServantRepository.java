package world.arshad.grandordercompanion.database;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

/**
 * Created by arsha on 19/03/2018.
 */

public final class ServantRepository implements ServantDatabaseInterface {

    private final static int CURRENT_NA_CUTOFF = 117;
    private boolean NAOnly = true;
    private Map<Integer, Servant> servantCache;

    /*.....................................................*/

    private static final ServantRepository ourInstance = new ServantRepository();

    public static ServantRepository getInstance() {
        return ourInstance;
    }

    private ServantRepository() {
    }

    public boolean isValid() {
        return servantCache != null;
    }

    /*.....................................................*/

    private ServantDatabaseInterface backingDatabase;

    @SuppressLint("UseSparseArrays")
    public void setBackingDatabase(ServantDatabaseInterface backingDatabase, boolean NAOnly) {
        this.NAOnly = NAOnly;
        this.backingDatabase = backingDatabase;

        servantCache = new HashMap<>();

        for (Servant servant : NAOnly ? backingDatabase.getSomeServants(CURRENT_NA_CUTOFF) : backingDatabase.getAllServants()) {
            servantCache.put(servant.getId(), servant);
        }
    }

    public ServantDatabaseInterface getBackingStructure() {
        return backingDatabase;
    }

    /*.....................................................*/

    @Override
    public List<Servant> getAllServants() {
        return new ArrayList<>(servantCache.values());
    }

    @Override
    public List<Servant> getSomeServants(int finalId) {
        List<Servant> toReturn = new ArrayList<>();
        servantCache.forEach((id, servant) -> {
            if (id <= finalId) {
                toReturn.add(servant);
            }
        });
        return toReturn;
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
        getBackingStructure().updateServant(servant);
    }

    /*.....................................................*/

    @Override
    public List<Ascension> getAscensions() {
        return getBackingStructure().getAscensions();
    }

    @Override
    public List<Ascension> getAscensionsOfType(int type) {
        return getBackingStructure().getAscensionsOfType(type);
    }

    @Override
    public List<Ascension> getAscensionsForServant(int servant_id) {
        return getBackingStructure().getAscensionsForServant(servant_id);
    }

    @Override
    public void updateAscension(Ascension ascension) {
        getBackingStructure().updateAscension(ascension);
        servantCache.put(ascension.getServantId(), getBackingStructure().getServant(ascension.getServantId()));
    }

    @Override
    public List<AscensionEntry> getSpecificAscensionEntriesForServant(int servant_id, int ascension_number) {
        return getBackingStructure().getSpecificAscensionEntriesForServant(servant_id, ascension_number);
    }

    /*.....................................................*/

    @Override
    public List<SkillUp> getSkillUps() {
        return getBackingStructure().getSkillUps();
    }

    @Override
    public List<SkillUp> getSkillUpsOfType(int type) {
        return getBackingStructure().getSkillUpsOfType(type);
    }

    @Override
    public List<SkillUp> getSkillUpsForServantAndSkillNumber(int servant_id, int skill_number) {
        return getBackingStructure().getSkillUpsForServantAndSkillNumber(servant_id, skill_number);
    }

    @Override
    public void updateSkillUp(SkillUp skillUp) {
        getBackingStructure().updateSkillUp(skillUp);
        servantCache.put(skillUp.getServantId(), getBackingStructure().getServant(skillUp.getServantId()));
    }

    @Override
    public List<SkillUpEntry> getSpecificSkillUpEntriesForServant(int servant_id, int dest_skill_level) {
        return getBackingStructure().getSpecificSkillUpEntriesForServant(servant_id, dest_skill_level);
    }

    /*.....................................................*/

    @Override
    public void insertAllServants(List<Servant> servants) {
        getBackingStructure().insertAllServants(servants);
    }

    @Override
    public void insertAllAscensions(List<Ascension> ascensions) {
        getBackingStructure().insertAllAscensions(ascensions);
    }

    @Override
    public void insertAllAscensionEntries(List<AscensionEntry> entries) {
        getBackingStructure().insertAllAscensionEntries(entries);
    }

    @Override
    public void insertAllSkillUps(List<SkillUp> skillUps) {
        getBackingStructure().insertAllSkillUps(skillUps);
    }

    @Override
    public void insertAllSkillUpEntries(List<SkillUpEntry> entries) {
        getBackingStructure().insertAllSkillUpEntries(entries);
    }
}
