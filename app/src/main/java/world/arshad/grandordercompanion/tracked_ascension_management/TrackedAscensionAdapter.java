package world.arshad.grandordercompanion.tracked_ascension_management;

import android.app.AlertDialog;
import android.content.Context;
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
import world.arshad.grandordercompanion.data.domain_data.AscensionEntry;
import world.arshad.grandordercompanion.data.domain_data.Entry;
import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;
import world.arshad.grandordercompanion.data.user_data.TrackedAscension;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arshad on 09/02/2018.
 */

public class TrackedAscensionAdapter extends ExpandableRecyclerViewAdapter<TrackedAscensionAdapter.TrackedAscensionParentViewHolder, TrackedAscensionAdapter.TrackedAscensionChildViewHolder> {

    private final Context context;
    private final LayoutInflater mInflater;
    private View.OnClickListener buttonListener;

    public static class TrackedAscensionParentViewHolder extends GroupViewHolder {
        @BindView(R.id.skill_or_ascension_parent_label)
        TextView entryNumberLabel;

        @BindView(R.id.skill_or_ascension_parent_button)
        ImageButton entryButton;

        public TrackedAscensionParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class TrackedAscensionChildViewHolder extends ChildViewHolder {
        @BindView(R.id.skill_or_ascension_child_name)
        TextView name;
        @BindView(R.id.skill_or_ascension_child_count)
        TextView count;
        @BindView(R.id.skill_or_ascension_child_thumbnail)
        ImageView thumbnail;

        public TrackedAscensionChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class TrackedAscensionParent extends ExpandableGroup<AscensionEntry> {
        private final TrackedAscension trackedAscension;

        TrackedAscensionParent(TrackedAscension trackedAscension) {
            super(String.format("%s :\nAscension # %d", DomainDataSingleton.getInstance().getServantInfo(trackedAscension.getServantId()).toString(), trackedAscension.getAscensionNumber()), DomainDataSingleton.getInstance().getServantInfo(trackedAscension.getServantId()).getAscensionEntries(trackedAscension.getAscensionNumber()));
            this.trackedAscension = trackedAscension;
            this.trackedAscension.setAscensionNumber(this.trackedAscension.getAscensionNumber());
        }
    }

    public TrackedAscensionAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TrackedAscensionAdapter.TrackedAscensionParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.entry_skill_or_ascension_parent, parent, false);
        return new TrackedAscensionAdapter.TrackedAscensionParentViewHolder(view);
    }

    @Override
    public TrackedAscensionAdapter.TrackedAscensionChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.entry_skill_or_ascension_child, parent, false);
        return new TrackedAscensionAdapter.TrackedAscensionChildViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(TrackedAscensionAdapter.TrackedAscensionParentViewHolder holder, int position, ExpandableGroup group) {
        holder.entryNumberLabel.setText(group.getTitle());

        holder.entryButton.setOnClickListener(view -> new AlertDialog.Builder(context)
            .setTitle("Delete Entry?")
            .setIcon(R.drawable.ic_warning_black_24dp)
            .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                UserDataSingleton.getInstance().getRoomDB().trackedAscensionDao().delete((((TrackedAscensionParent) group).trackedAscension));
                ((TrackedAscensionsActivity) context).refreshData();
            })
            .setNegativeButton(android.R.string.no, null).show());

        holder.entryButton.setBackgroundResource(R.drawable.ic_delete_black_24dp);
    }

    public void onBindChildViewHolder(TrackedAscensionAdapter.TrackedAscensionChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Entry entry = ((TrackedAscensionAdapter.TrackedAscensionParent) group).getItems().get(childIndex);
        holder.name.setText(entry.getMaterial().getName());
        holder.count.setText(String.valueOf(entry.getCount()));
        holder.thumbnail.setImageDrawable(Utilities.loadImageFromStorage(entry.getMaterial().getIconURL(), context.getAssets()));
    }

}