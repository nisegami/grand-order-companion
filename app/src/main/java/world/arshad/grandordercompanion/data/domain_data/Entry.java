package world.arshad.grandordercompanion.data.domain_data;

import android.os.Parcelable;

/**
 * Created by arsha on 02/01/2018.
 */

public abstract class Entry implements Parcelable {
    public abstract int getCount();

    public abstract void setCount(int count);

    public abstract Material getMaterial();

    public abstract void setMaterial(Material materialName);

    public abstract int getServantId();

    public abstract void setServantId(int servantId);
}
