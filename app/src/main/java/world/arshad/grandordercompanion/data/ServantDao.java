package world.arshad.grandordercompanion.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Data access object for Servants
 * Created by arshad on 19/03/2018.
 */
@Dao
public abstract class ServantDao {


    /*
     * Room does not handle nested relations at all, so the public methods here are inserts,
     * updates, or methods that pull the raw data and construct the necessary objects with their
     * nested relations intact.
     */

    public List<Servant> getAllServants() {
        List<Servant> servants = _getAllServants();

        for (Servant servant : servants) {
            servant.setAscensions(getAllAscensionsForServant(servant.getId()));
            servant.setSkillUps1(getAllSkillUpsForServant(servant.getId(), 1));
            servant.setSkillUps2(getAllSkillUpsForServant(servant.getId(), 2));
            servant.setSkillUps3(getAllSkillUpsForServant(servant.getId(), 3));
        }

        return servants;
    }

    public Servant getServant(int servantId) {
        Servant servant = _getServant(servantId);
        servant.setAscensions(getAllAscensionsForServant(servant.getId()));
        servant.setSkillUps1(getAllSkillUpsForServant(servant.getId(), 1));
        servant.setSkillUps2(getAllSkillUpsForServant(servant.getId(), 2));
        servant.setSkillUps3(getAllSkillUpsForServant(servant.getId(), 3));
        return servant;
    }

    public List<Ascension> getAllAscensions() {
        List<Ascension> ascensions = _getAllAscensions();

        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public List<Ascension> getTrackedAscensions() {
        List<Ascension> ascensions = _getAllAscensionsForStatus(Ascension.TRACKED);

        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public List<Ascension> getCompletedAscensions() {
        List<Ascension> ascensions = _getAllAscensionsForStatus(Ascension.COMPLETED);

        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public List<Ascension> getAllAscensionsForServant(int servant_id) {
        List<Ascension> ascensions = _getAllAscensionsForServant(servant_id);
        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public Ascension getSingleAscensionForServant(int servant_id, int ascension_number) {
        Ascension ascension = _getSingleAscensionForServant(servant_id, ascension_number);
        ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(servant_id, ascension_number));
        return ascension;
    }

    public List<SkillUp> getAllSkillUps() {
        List<SkillUp> skillUps = _getAllSkillUps();

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public List<SkillUp> getTrackedSkillUps() {
        List<SkillUp> skillUps = _getAllSkillUpsForStatus(SkillUp.TRACKED);

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public List<SkillUp> getCompletedSkillUps() {
        List<SkillUp> skillUps = _getAllSkillUpsForStatus(SkillUp.COMPLETED);

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public List<SkillUp> getAllSkillUpsForServant(int servantId, int skillNumber) {
        List<SkillUp> skillUps = _getAllSkillUpsForServant(servantId, skillNumber);

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public SkillUp getSingleSkillUpForServant(int servantId, int destSkillLevel, int skillNumber) {
        SkillUp skillUp = _getSingleSkillUpForServant(servantId, destSkillLevel, skillNumber);
        skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(servantId, destSkillLevel));
        return skillUp;
    }

    // Servant

    @Query("SELECT * FROM servant")
    abstract List<Servant> _getAllServants();

    @Query("SELECT * FROM servant WHERE id = :id")
    abstract Servant _getServant(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertServant(Servant servant);

    @Query("SELECT name FROM servant WHERE id = :id")
    public abstract String getServantNameFromId(int id);

    // Ascension

    @Query("SELECT * FROM ascension")
    abstract List<Ascension> _getAllAscensions();

    @Query("SELECT * FROM ascension WHERE status = :status")
    abstract List<Ascension> _getAllAscensionsForStatus(int status);

    @Query("SELECT * FROM ascension WHERE servant_id = :servant_id")
    abstract List<Ascension> _getAllAscensionsForServant(int servant_id);

    @Query("SELECT * FROM ascension WHERE servant_id = :servant_id AND ascension_number = :ascension_number")
    abstract Ascension _getSingleAscensionForServant(int servant_id, int ascension_number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAscension(Ascension ascension);

    @Query("SELECT * FROM ascension_entry")
    public abstract List<AscensionEntry> getAllAscensionEntriesForServant();

    @Query("SELECT * FROM ascension_entry WHERE servant_id = :servant_id AND ascension_number = :ascension_number")
    abstract List<AscensionEntry> getSpecificAscensionEntriesForServant(int servant_id, int ascension_number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAscensionEntry(AscensionEntry entry);

    // SkillUp

    @Query("SELECT * FROM skill_up")
    abstract List<SkillUp> _getAllSkillUps();

    @Query("SELECT * FROM skill_up WHERE status = :status")
    abstract List<SkillUp> _getAllSkillUpsForStatus(int status);

    @Query("SELECT * FROM skill_up WHERE servant_id = :servant_id AND skill_number = :skill_number")
    abstract List<SkillUp> _getAllSkillUpsForServant(int servant_id, int skill_number);

    @Query("SELECT * FROM skill_up WHERE servant_id = :servant_id AND dest_skill_level = :dest_skill_level AND skill_number = :skill_number")
    abstract SkillUp _getSingleSkillUpForServant(int servant_id, int dest_skill_level, int skill_number);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertSkillUp(SkillUp skillUp);

    @Query("SELECT * FROM skill_up_entry WHERE servant_id = :servant_id")
    abstract List<SkillUpEntry> getAllSkillUpEntriesForServant(int servant_id);

    @Query("SELECT * FROM skill_up_entry WHERE servant_id = :servant_id AND dest_skill_level = :dest_skill_level")
    abstract List<SkillUpEntry> getSpecificSkillUpEntriesForServant(int servant_id, int dest_skill_level);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertSkillUpEntry(SkillUpEntry entry);

    // Update methods
    @Update
    public abstract void updateServant(Servant servant);

    @Update
    public abstract void updateAscension(Ascension ascension);

    @Update
    public abstract void updateAscensionEntry(AscensionEntry entry);

    @Update
    public abstract void updateSkillUp(SkillUp skillUp);

    @Update
    public abstract void updateSkillUpEntry(SkillUpEntry entry);

    // Insert-All methods

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllServants(List<Servant> servants);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllAscensions(List<Ascension> ascensions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllAscensionEntrys(List<AscensionEntry> entries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllSkillUps(List<SkillUp> skillUps);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllSkillUpEntrys(List<SkillUpEntry> entries);
}
