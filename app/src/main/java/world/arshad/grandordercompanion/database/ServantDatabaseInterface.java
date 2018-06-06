package world.arshad.grandordercompanion.database;

import java.util.List;

import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

public interface ServantDatabaseInterface {

    List<Servant> getAllServants();
    List<Servant> getSomeServants(int finalID);
    Servant getServant(int servantId);
    String getServantNameFromId(int id);
    void updateServant(Servant servant);

    List<Ascension> getAscensions();
    List<Ascension> getAscensionsOfType(int type);
    List<Ascension> getAscensionsForServant(int servant_id);
    void updateAscension(Ascension ascension);
    List<AscensionEntry> getSpecificAscensionEntriesForServant(int servant_id, int ascension_number);

    List<SkillUp> getSkillUps();
    List<SkillUp> getSkillUpsOfType(int type);
    List<SkillUp> getSkillUpsForServantAndSkillNumber(int servant_id, int skill_number);
    void updateSkillUp(SkillUp skillUp);
    List<SkillUpEntry> getSpecificSkillUpEntriesForServant(int servant_id, int dest_skill_level);

    void insertAllServants(List<Servant> servants);
    void insertAllAscensions(List<Ascension> ascensions);
    void insertAllAscensionEntries(List<AscensionEntry> entries);
    void insertAllSkillUps(List<SkillUp> skillUps);
    void insertAllSkillUpEntries(List<SkillUpEntry> entries);
}
