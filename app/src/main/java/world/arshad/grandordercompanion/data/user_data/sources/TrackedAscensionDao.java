package world.arshad.grandordercompanion.data.user_data.sources;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import world.arshad.grandordercompanion.data.user_data.TrackedAscension;

/**
 * Created by arshad on 09/02/2018.
 */

@Dao
public interface TrackedAscensionDao {
    @Query("SELECT * FROM tracked_ascension")
    List<TrackedAscension> getAll();

    @Query("SELECT * FROM tracked_ascension WHERE servant_id = :servantId")
    List<TrackedAscension> getAllForServant(int servantId);

    @Insert
    void insert(TrackedAscension trackedAscension);

    @Delete
    void delete(TrackedAscension trackedAscension);
}
