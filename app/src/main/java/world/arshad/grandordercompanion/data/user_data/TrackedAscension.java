package world.arshad.grandordercompanion.data.user_data;

/**
 * Created by arsha on 04/01/2018.
 */

public class TrackedAscension {
    private int servantId;
    private int ascensionNumber;

    public TrackedAscension(int servantId, int ascensionNumber) {
        this.servantId = servantId;
        this.ascensionNumber = ascensionNumber;
    }

    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
    }

    public int getAscensionNumber() {
        return ascensionNumber;
    }

    public void setAscensionNumber(int ascensionNumber) {
        this.ascensionNumber = ascensionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackedAscension)) return false;

        TrackedAscension that = (TrackedAscension) o;

        if (servantId != that.servantId) return false;
        return ascensionNumber == that.ascensionNumber;
    }

    @Override
    public int hashCode() {
        int result = servantId;
        result = 31 * result + ascensionNumber;
        return result;
    }
}
