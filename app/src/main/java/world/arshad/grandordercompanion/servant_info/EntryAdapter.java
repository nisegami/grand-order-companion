package world.arshad.grandordercompanion.servant_info;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.Entry;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arshad on 02/01/2018.
 */

public class EntryAdapter extends ExpandableRecyclerViewAdapter<EntryAdapter.EntryParentViewHolder, EntryAdapter.EntryChildViewHolder> {

    private final Context context;
    private final LayoutInflater mInflater;

    public static class EntryParentViewHolder extends GroupViewHolder {
        @BindView(R.id.skill_or_ascension_parent_label) TextView entryNumberLabel;

        @BindView(R.id.skill_or_ascension_parent_button) ImageButton entryButton;

        public EntryParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class EntryChildViewHolder extends ChildViewHolder {
        @BindView(R.id.skill_or_ascension_child_name) TextView name;
        @BindView(R.id.skill_or_ascension_child_count) TextView count;
        @BindView(R.id.skill_or_ascension_child_thumbnail) ImageView thumbnail;

        public EntryChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class EntryParent extends ExpandableGroup<Entry> {
        EntryParent(String title, List<Entry> items) {
            super(title, items);
        }
    }

    public EntryAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public EntryAdapter.EntryParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.skill_or_ascension_parent, parent, false);
        return new EntryAdapter.EntryParentViewHolder(view);
    }

    @Override
    public EntryAdapter.EntryChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.skill_or_ascension_child, parent, false);
        return new EntryAdapter.EntryChildViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(EntryAdapter.EntryParentViewHolder holder, int position, ExpandableGroup group) {
        holder.entryNumberLabel.setText(group.getTitle());

        holder.entryButton.setOnClickListener(view -> new AlertDialog.Builder(context)
                .setTitle("Track Entry?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> ((Entry) group.getItems().get(0)).trackThisEntry(context))
                .setNegativeButton(android.R.string.no, null).show());

        holder.entryButton.setBackgroundResource(R.drawable.ic_eye_black_24dp);

    }

    public void onBindChildViewHolder(EntryAdapter.EntryChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Entry entry = ((EntryParent) group).getItems().get(childIndex);
        holder.name.setText(entry.getMaterial().getName());
        holder.count.setText(String.valueOf(entry.getCount()));
        holder.thumbnail.setImageDrawable(Utilities.loadImageFromStorage(entry.getMaterial().getIconURL(), context));
    }
}
