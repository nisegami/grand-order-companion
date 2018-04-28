package world.arshad.grandordercompanion.all_servants;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import world.arshad.grandordercompanion.data.Model;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.Servant;

/**
 * ViewModel for the list of servants.
 * Created by arshad on 20/03/2018.
 */

public class AllServantsViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak") // AppContext use forced
    private final Context appContext;
    private SharedPreferences prefs;

    private List<Servant> servants;
    private List<Servant> allServants;
    private boolean reverse = false;
    private Servant.Comps comparator = Servant.Comps.ID;

    public AllServantsViewModel(Application appContext) {
        super(appContext);
        this.appContext = appContext;

        prefs = appContext.getSharedPreferences(appContext.getString(R.string.app_name), Context.MODE_PRIVATE);
        comparator = Servant.Comps.valueOf(prefs.getString("servant_info_list_sort", "ID"));
        reverse = prefs.getBoolean("servant_info_list_reverse", false);
    }

    /**
     * Call this at the start of any method that is working with the data.
     */
    private void fetchData(){
        if (servants == null) {
            allServants = Model.getInstance().getDatabase().servantDao().getAllServants();
            servants = new ArrayList<>();
            servants.addAll(allServants);
        }
    }

    public void refreshServants() {
        servants = null;
    }

    public List<Servant> getServants() {
        fetchData();
        sortItems();
        return servants;
    }

    public void sortItems(Servant.Comps comparator) {
        this.comparator = comparator;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("servant_info_list_sort", this.comparator.name());
        editor.apply();
        sortItems();
    }

    public void reverseItems() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("servant_info_list_reverse", !reverse);
        editor.apply();
        reverse = !reverse;
        sortItems();
    }

    public void filterItems(CharSequence rawTerm) {
        fetchData();
        servants = new ArrayList<>();
        String term = rawTerm.toString();

        if (term.equals("")) {
            servants.addAll(allServants);
            sortItems();
            return;
        }

        for (Servant servant : allServants) {
            if (servant.getName().toLowerCase().contains(term.toLowerCase())) {
                servants.add(servant);
            }
        }
        sortItems();
    }

    public void sortItems() {
        fetchData();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            servants.sort(reverse ? comparator.getComp().reversed() : comparator.getComp());
        } else{
            Collections.sort(servants, comparator.getComp());
            if (reverse) {
                Collections.reverse(servants);
            }
        }
    }

    public Drawable getReverseButtonDrawable() {
        return reverse ? appContext.getDrawable(R.drawable.ic_sort_ascending_black_24dp) : appContext.getDrawable(R.drawable.ic_sort_descending_black_24dp);
    }

}
