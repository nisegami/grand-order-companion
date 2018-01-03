package world.arshad.grandordercompanion.data.domain_data;

/**
 * Created by arsha on 02/01/2018.
 */

public abstract class Entry {
    public abstract int getCount();

    public abstract void setCount(int count);

    public abstract Material getMaterial();

    public abstract void setMaterial(Material materialName);

    public abstract int getServantId();

    public abstract void setServantId(int servantId);
}
