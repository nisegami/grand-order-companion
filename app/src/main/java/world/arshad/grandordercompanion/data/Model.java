package world.arshad.grandordercompanion.data;

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.Map;

import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.Utilities;

/**
 * Created by arsha on 19/03/2018.
 */

public class Model {
    private static final Model ourInstance = new Model();

    public static Model getInstance() {
        return ourInstance;
    }

    private Model() {
    }

    // Database
    private ServantDatabase database;
    public ServantDatabase getDatabase() {
        return database;
    }
    public void setDatabase(ServantDatabase database) {
        this.database = database;
    }

    // Colors
    private Map<Integer, Integer> colors = new HashMap<>();
    public Integer getServantColor(Servant servant, Context context) {
        if (!context.getSharedPreferences("goc", Context.MODE_PRIVATE).getBoolean("use_colors", false)) {
            return context.getColor(R.color.colorBackgroundGray);
        }

        if (colors.containsKey(servant.getId())) {
            return colors.get(servant.getId());
        }

        Integer color;
        Palette palette = (new Palette.Builder(Utilities.loadBitmapFromAssets(servant.getArtworkPath(1), context.getAssets()))).maximumColorCount(40).generate();

        try {
            color = palette.getLightMutedSwatch().getRgb();
        } catch (NullPointerException e) {
            color = palette.getLightMutedColor(context.getColor(R.color.colorBackgroundGray));
        }

        colors.put(servant.getId(), color);

        return color;
    }
}
