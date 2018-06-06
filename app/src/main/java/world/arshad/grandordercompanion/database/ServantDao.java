package world.arshad.grandordercompanion.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

/**
 * Data access object for Servants
 * Created by arshad on 19/03/2018.
 */
@Dao
public abstract class ServantDao implements ServantDatabaseInterface {

    /*
     * Room does not handle nested relations at all, so the public methods here are inserts,
     * updates, or methods that pull the raw data and construct the necessary objects with their
     * nested relations intact.
     */

    // Servants

    public List<Servant> getAllServants() {
        List<Servant> servants = _getAllServants();

        for (Servant servant : servants) {
            servant.setAscensions(getAscensionsForServant(servant.getId()));
            servant.setSkillUps1(getSkillUpsForServantAndSkillNumber(servant.getId(), 1));
            servant.setSkillUps2(getSkillUpsForServantAndSkillNumber(servant.getId(), 2));
            servant.setSkillUps3(getSkillUpsForServantAndSkillNumber(servant.getId(), 3));
        }

        return servants;
    }

    public List<Servant> getSomeServants(int finalID) {
        List<Servant> servants = _getSomeServants(finalID);

        for (Servant servant : servants) {
            servant.setAscensions(getAscensionsForServant(servant.getId()));
            servant.setSkillUps1(getSkillUpsForServantAndSkillNumber(servant.getId(), 1));
            servant.setSkillUps2(getSkillUpsForServantAndSkillNumber(servant.getId(), 2));
            servant.setSkillUps3(getSkillUpsForServantAndSkillNumber(servant.getId(), 3));
        }

        return servants;
    }

    public Servant getServant(int servantId) {
        Servant servant = _getServant(servantId);
        servant.setAscensions(getAscensionsForServant(servant.getId()));
        servant.setSkillUps1(getSkillUpsForServantAndSkillNumber(servant.getId(), 1));
        servant.setSkillUps2(getSkillUpsForServantAndSkillNumber(servant.getId(), 2));
        servant.setSkillUps3(getSkillUpsForServantAndSkillNumber(servant.getId(), 3));
        return servant;
    }

    public  String getServantNameFromId(int id) {
        return _getServantNameFromId(id);
    }

    public void updateServant(Servant servant) {
        _updateServant(servant);
    }

    // Ascensions

    public List<Ascension> getAscensions() {
        List<Ascension> ascensions = _getAllAscensions();

        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public List<Ascension> getAscensionsOfType(int type) {
        List<Ascension> ascensions = _getAllAscensionsForStatus(type);

        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public List<Ascension> getAscensionsForServant(int servant_id) {
        List<Ascension> ascensions = _getAllAscensionsForServant(servant_id);
        for (Ascension ascension : ascensions) {
            ascension.setAscensionEntries(getSpecificAscensionEntriesForServant(ascension.getServantId(), ascension.getAscensionNumber()));
        }

        return ascensions;
    }

    public void updateAscension(Ascension ascension) {
        _updateAscension(ascension);
    }

    public List<AscensionEntry> getSpecificAscensionEntriesForServant(int servant_id, int ascension_number) {
        return _getSpecificAscensionEntriesForServant(servant_id, ascension_number);
    }

    // Skill Ups

    public List<SkillUp> getSkillUps() {
        List<SkillUp> skillUps = _getAllSkillUps();

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public List<SkillUp> getSkillUpsOfType(int type) {
        List<SkillUp> skillUps = _getAllSkillUpsForStatus(type);

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public List<SkillUp> getSkillUpsForServantAndSkillNumber(int servantId, int skillNumber) {
        List<SkillUp> skillUps = _getAllSkillUpsForServant(servantId, skillNumber);

        for (SkillUp skillUp : skillUps) {
            skillUp.setSkillUpEntries(getSpecificSkillUpEntriesForServant(skillUp.getServantId(), skillUp.getDestSkillLevel()));
        }

        return skillUps;
    }

    public void updateSkillUp(SkillUp skillUp) {
        _updateSkillUp(skillUp);
    }

    public List<SkillUpEntry> getSpecificSkillUpEntriesForServant(int servant_id, int dest_skill_level) {
        return _getSpecificSkillUpEntriesForServant(servant_id, dest_skill_level);
    }

    // Insert-All methods

    public void insertAllServants(List<Servant> servants) {
        _insertAllServants(servants);
    }

    public void insertAllAscensions(List<Ascension> ascensions) {
        _insertAllAscensions(ascensions);
    }

    public void insertAllAscensionEntries(List<AscensionEntry> entries) {
        _insertAllAscensionEntries(entries);
    }

    public void insertAllSkillUps(List<SkillUp> skillUps) {
        _insertAllSkillUps(skillUps);
    }

    public void insertAllSkillUpEntries(List<SkillUpEntry> entries) {
        _insertAllSkillUpEntries(entries);
    }

    // Private Methods

    @Query("SELECT * FROM servant")
    abstract List<Servant> _getAllServants();

    @Query("SELECT * FROM servant WHERE id <= :finalId")
    abstract List<Servant> _getSomeServants(int finalId);

    @Query("SELECT * FROM servant WHERE id = :id")
    abstract Servant _getServant(int id);

    @Query("SELECT * FROM ascension")
    abstract List<Ascension> _getAllAscensions();

    @Query("SELECT * FROM ascension WHERE status = :status")
    abstract List<Ascension> _getAllAscensionsForStatus(int status);

    @Query("SELECT * FROM ascension WHERE servant_id = :servant_id")
    abstract List<Ascension> _getAllAscensionsForServant(int servant_id);

    @Query("SELECT * FROM skill_up")
    abstract List<SkillUp> _getAllSkillUps();

    @Query("SELECT * FROM skill_up WHERE status = :status")
    abstract List<SkillUp> _getAllSkillUpsForStatus(int status);

    @Query("SELECT * FROM skill_up WHERE servant_id = :servant_id AND skill_number = :skill_number")
    abstract List<SkillUp> _getAllSkillUpsForServant(int servant_id, int skill_number);



    @Query("SELECT name FROM servant WHERE id = :id")
    abstract String _getServantNameFromId(int id);

    @Update
    abstract void _updateServant(Servant servant);

    @Update
    abstract void _updateAscension(Ascension ascension);

    @Query("SELECT * FROM ascension_entry WHERE servant_id = :servant_id AND ascension_number = :ascension_number")
    abstract List<AscensionEntry> _getSpecificAscensionEntriesForServant(int servant_id, int ascension_number);

    @Update
    abstract void _updateSkillUp(SkillUp skillUp);

    @Query("SELECT * FROM skill_up_entry WHERE servant_id = :servant_id AND dest_skill_level = :dest_skill_level")
    abstract List<SkillUpEntry> _getSpecificSkillUpEntriesForServant(int servant_id, int dest_skill_level);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllServants(List<Servant> servants);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllAscensions(List<Ascension> ascensions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllAscensionEntries(List<AscensionEntry> entries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllSkillUps(List<SkillUp> skillUps);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void _insertAllSkillUpEntries(List<SkillUpEntry> entries);
}
