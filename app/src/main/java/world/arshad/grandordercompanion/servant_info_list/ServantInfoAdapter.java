package world.arshad.grandordercompanion.servant_info_list;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;
import world.arshad.grandordercompanion.servant_info.ServantInfoActivity;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arshad on 25/12/2017.
 */

public class ServantInfoAdapter extends RecyclerView.Adapter<ServantInfoAdapter.ViewHolder> {

    private final List<ServantInfo> servants = new ArrayList<>();
    private final Context context;
    private SharedPreferences prefs;
    private Comparator<ServantInfo> comparator;
    private boolean reverse;
    private final Map<Integer, Comparator<ServantInfo>> comparators = new HashMap<>(4);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.servant_info_entry_name) TextView name;
        @BindView(R.id.servant_info_entry_thumbnail) ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ServantInfoAdapter(List<ServantInfo> servants, Context context) {
        this.servants.addAll(servants);
        this.context = context;

        comparators.put(0, ServantInfo.ID_COMP);
        comparators.put(1, ServantInfo.NAME_COMP);
        comparators.put(2, ServantInfo.CLASS_COMP);
        comparators.put(3, ServantInfo.RARITY_COMP);

        prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        comparator = comparators.get(prefs.getInt("servant_info_list_sort", 0));
        reverse = prefs.getBoolean("servant_info_list_reverse", false);
        sortItems();
    }

    public void sortItems(int selection) {
        this.comparator = comparators.get(selection);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("servant_info_list_sort", selection);
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

    public void sortItems() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            servants.sort(reverse ? comparator.reversed() : comparator);
        } else{
            Collections.sort(servants, comparator);
            if (reverse) {
                Collections.reverse(servants);
            }
        }
        notifyDataSetChanged();
    }

    public Drawable getReverseButtonDrawable() {
        return reverse ? context.getDrawable(R.drawable.ic_sort_ascending) : context.getDrawable(R.drawable.ic_sort_descending);
    }


    @Override
    public ServantInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View servantInfoView = inflater.inflate(R.layout.entry_servant, parent, false);
        servantInfoView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ServantInfoActivity.class);
            intent.putExtra("servant", servants.get(((ServantInfoListActivity) context).servantInfoList.getChildAdapterPosition(view)));
            context.startActivity(intent);
        });
        return new ViewHolder(servantInfoView);
    }

    @Override
    public void onBindViewHolder(ServantInfoAdapter.ViewHolder holder, int position) {
        final ServantInfo servant = servants.get(position);

        holder.name.setText(servant.getName());
        holder.thumbnail.setImageDrawable(Utilities.loadImageFromStorage(servant.getThumbnailURL(1), context.getAssets()));
    }

    @Override
    public int getItemCount() {
        return servants.size();
    }
}
