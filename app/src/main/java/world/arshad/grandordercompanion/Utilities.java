package world.arshad.grandordercompanion;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.util.Log;

import java.io.IOException;

import world.arshad.grandordercompanion.model.Servant;

/**
 * Created by arsha on 20/03/2018.
 */

public final class Utilities {

    private Utilities() {
    }

    public static Drawable loadDrawableFromAssets(String filename, AssetManager assetManager) {
        try {
            return Drawable.createFromStream(assetManager.open(filename), null);
        } catch (IOException e) {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename, e);
        }
        return null;
    }

    public static Bitmap loadBitmapFromAssets(String filename, AssetManager assetManager) {
        try {
            return BitmapFactory.decodeStream(assetManager.open(filename));
        } catch (IOException e) {
            Log.e("IMAGE LOADING", "Failed to load image: " + filename, e);
        }
        return null;
    }

    public static int getServantColor(Servant servant, Context context) {
        Integer color;
        Palette palette = (new Palette.Builder(Utilities.loadBitmapFromAssets(servant.getArtworkPath(1), context.getAssets()))).maximumColorCount(40).generate();

        try {
            color = palette.getLightMutedSwatch().getRgb();
        } catch (NullPointerException e) {
            color = palette.getLightMutedColor(context.getColor(R.color.colorBackgroundGray));
        }

        return color;
    }
}
