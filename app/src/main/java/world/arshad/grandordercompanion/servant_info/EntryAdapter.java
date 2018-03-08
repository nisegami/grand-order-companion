package world.arshad.grandordercompanion.servant_info;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
 * Created by arshad on 02/01/2018.
 */

public class EntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int GROUP = 0, CHILD = 1;

    private final Context context;

    private List<Object> items = new ArrayList<>();

    public static class EntryParentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.skill_or_ascension_parent_label) TextView entryNumberLabel;
        @BindView(R.id.skill_or_ascension_parent_button) ImageButton entryButton;

        public EntryParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class EntryChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.skill_or_ascension_child_name) TextView name;
        @BindView(R.id.skill_or_ascension_child_count) TextView count;
        @BindView(R.id.skill_or_ascension_child_thumbnail) ImageView thumbnail;

        public EntryChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /*
    Arshad, at some point in the future, you will come across this and wonder what on earth you
    could possibly have been thinking.

    I hope on that day, you have learnt enough to figure out how to do this in a reasonable way.

    Until then, this will remain untouched for generations.
     */
    public interface EntryTrackInterface {
        public void track(Context context);
    }

    public static class EntryParent {
        String title;
        EntryTrackInterface entryTrackInterface;
        public EntryParent(String title, EntryTrackInterface entryTrackInterface) {
            this.title = title;
            this.entryTrackInterface = entryTrackInterface;
        }
    }

    public EntryAdapter(Context context, List<Object> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case GROUP: {
                EntryParentViewHolder holder = (EntryParentViewHolder) viewHolder;
                EntryParent group = ((EntryParent) items.get(position));
                holder.entryNumberLabel.setText(group.title);
                holder.entryButton.setOnClickListener(view -> new AlertDialog.Builder(context)
                        .setTitle("Track Entry?")
                        .setIcon(R.drawable.ic_warning_black_24dp)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                group.entryTrackInterface.track(context);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show());

                holder.entryButton.setBackgroundResource(R.drawable.ic_eye_black_24dp);
                break;
            }
            case CHILD: {
                EntryChildViewHolder holder = (EntryChildViewHolder) viewHolder;
                Entry entry = ((Entry) items.get(position));
                holder.name.setText(entry.getMaterial().getName());
                holder.count.setText(String.valueOf(entry.getCount()));
                holder.thumbnail.setImageDrawable(Utilities.loadImageFromStorage(entry.getMaterial().getIconURL(), context.getAssets()));
                break;
            }
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case GROUP: {
                View view = inflater.inflate(R.layout.entry_skill_or_ascension_parent, viewGroup, false);
                RecyclerView.ViewHolder viewHolder = new EntryParentViewHolder(view);
                return viewHolder;
            }
            case CHILD: {
                View view = inflater.inflate(R.layout.entry_skill_or_ascension_child, viewGroup, false);
                RecyclerView.ViewHolder viewHolder = new EntryChildViewHolder(view);
                return viewHolder;
            }
        }
        return null; // LOL
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof EntryParent) {
            return GROUP;
        } else if (items.get(position) instanceof Entry) {
            return CHILD;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
