package world.arshad.grandordercompanion.servant_info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.Entry;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arsha on 02/01/2018.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {

    private final List<Entry> entries = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public EntryAdapter(List<? extends Entry> entries, Context context) {
        this.entries.addAll(entries);
        this.context = context;
    }

    @Override
    public EntryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View entryView = inflater.inflate(R.layout.skill_or_acension_entry, parent, false);
        return new EntryAdapter.ViewHolder(entryView);
    }

    @Override
    public void onBindViewHolder(EntryAdapter.ViewHolder holder, int position) {
        final Entry entry = entries.get(position);

        holder.name.setText(entry.getMaterial().getName());
        holder.thumbnail.setImageBitmap(Utilities.loadImageFromStorage(context.getFilesDir() + entry.getMaterial().getIconURL()));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }
}
