package world.arshad.grandordercompanion.data.user_data.sources;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import world.arshad.grandordercompanion.data.user_data.TrackedAscension;
import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;

/**
 * Created by arshad on 09/02/2018.
 */

@Dao
public interface TrackedSkillUpDao {
    @Query("SELECT * FROM tracked_skill_up")
    List<TrackedSkillUp> getAll();

    @Query("SELECT * FROM tracked_skill_up WHERE servant_id = :servantId")
    List<TrackedSkillUp> getAllForServant(int servantId);

    @Insert
    void insert(TrackedSkillUp trackedSkillUp);

    @Delete
    void delete(TrackedSkillUp trackedSkillUp);
}
