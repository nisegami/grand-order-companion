package world.arshad.grandordercompanion.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by arshad on 29/12/2017.
 */

public class Utilities {

    public static Drawable loadImageFromStorage(String filename, Context context) {
        try {
            return Drawable.createFromStream(context.getAssets().open(filename), null);
        } catch (IOException e) {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename, e);
        }

        return null;
    }

}
