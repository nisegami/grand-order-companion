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

    private final static int CURRENT_NA_CUTOFF = 113;

    private boolean NAOnly = true;

    private final Lock lock = (new ReentrantReadWriteLock()).writeLock();

    /*.....................................................*/

    private static final ServantRepository ourInstance = new ServantRepository();

    public static ServantRepository getInstance() {
        return ourInstance;
    }

    private ServantRepository() {
    }

    /*.....................................................*/

    private ServantDatabaseInterface backingDatabase;

    @SuppressLint("UseSparseArrays")
    public void setBackingDatabase(ServantDatabaseInterface backingDatabase, boolean NAOnly) {
        lock.lock();
        try {
            this.NAOnly = NAOnly;
            this.backingDatabase = backingDatabase;

            servantCache = new HashMap<>();

            for (Servant servant : NAOnly ? backingDatabase.getSomeServants(CURRENT_NA_CUTOFF) : backingDatabase.getAllServants()) {
                servantCache.put(servant.getId(), servant);
            }

        } finally {
            lock.unlock();
        }
    }

    public ServantDatabaseInterface getBackingStructure() {
        return backingDatabase;
    }

    /*.....................................................*/

    private Map<Integer, Servant> servantCache;

    @Override
    public List<Servant> getAllServants() {
        lock.lock();
        try {
            return new ArrayList<>(servantCache.values());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<Servant> getSomeServants(int finalId) {
        lock.lock();
        try {
            List<Servant> toReturn = new ArrayList<>();
            servantCache.forEach((id, servant) -> {
                if (id <= finalId) {
                    toReturn.add(servant);
                }
            });
            return toReturn;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Servant getServant(int servantId) {
        lock.lock();
        try {
            return servantCache.get(servantId);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getServantNameFromId(int servantId) {
        lock.lock();
        try {
            return servantCache.get(servantId).getName();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void updateServant(Servant servant) {
        lock.lock();
        try {
            servantCache.put(servant.getId(), servant);
        } finally {
            lock.unlock();
        }
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
        try {
            servantCache.put(ascension.getServantId(), getBackingStructure().getServant(ascension.getServantId()));
        } finally {
            lock.unlock();
        }
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
        try {
            servantCache.put(skillUp.getServantId(), getBackingStructure().getServant(skillUp.getServantId()));
        } finally {
            lock.unlock();
        }
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
