package world.arshad.grandordercompanion.tracked_skill_up_management;

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
import world.arshad.grandordercompanion.data.domain_data.Entry;
import world.arshad.grandordercompanion.data.domain_data.SkillUpEntry;
import world.arshad.grandordercompanion.data.domain_data.sources.DomainDataSingleton;
import world.arshad.grandordercompanion.data.user_data.TrackedSkillUp;
import world.arshad.grandordercompanion.data.user_data.sources.UserDataSingleton;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arshad on 09/02/2018.
 */

public class TrackedSkillUpAdapter extends ExpandableRecyclerViewAdapter<TrackedSkillUpAdapter.TrackedSkillUpParentViewHolder, TrackedSkillUpAdapter.TrackedSkillUpChildViewHolder> {

    private final Context context;
    private final LayoutInflater mInflater;
    private View.OnClickListener buttonListener;

    public static class TrackedSkillUpParentViewHolder extends GroupViewHolder {
        @BindView(R.id.skill_or_ascension_parent_label)
        TextView entryNumberLabel;

        @BindView(R.id.skill_or_ascension_parent_button)
        ImageButton entryButton;

        public TrackedSkillUpParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class TrackedSkillUpChildViewHolder extends ChildViewHolder {
        @BindView(R.id.skill_or_ascension_child_name)
        TextView name;
        @BindView(R.id.skill_or_ascension_child_count)
        TextView count;
        @BindView(R.id.skill_or_ascension_child_thumbnail)
        ImageView thumbnail;

        public TrackedSkillUpChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class TrackedSkillUpParent extends ExpandableGroup<SkillUpEntry> {
        private final TrackedSkillUp trackedSkillUp;

        TrackedSkillUpParent(TrackedSkillUp trackedSkillUp) {
            super(String.format("%s :\nSkill %d Level # %d", DomainDataSingleton.getInstance().getServantInfo(trackedSkillUp.getServantId()).toString(),  trackedSkillUp.getSkillNumber(), trackedSkillUp.getSkillDestLevel()), DomainDataSingleton.getInstance().getServantInfo(trackedSkillUp.getServantId()).getSkillUpEntries(trackedSkillUp.getSkillDestLevel()));
            this.trackedSkillUp = trackedSkillUp;
            this.trackedSkillUp.setSkillDestLevel(this.trackedSkillUp.getSkillDestLevel());
        }
    }

    public TrackedSkillUpAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public TrackedSkillUpAdapter.TrackedSkillUpParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.skill_or_ascension_parent, parent, false);
        return new TrackedSkillUpAdapter.TrackedSkillUpParentViewHolder(view);
    }

    @Override
    public TrackedSkillUpAdapter.TrackedSkillUpChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.skill_or_ascension_child, parent, false);
        return new TrackedSkillUpAdapter.TrackedSkillUpChildViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(TrackedSkillUpAdapter.TrackedSkillUpParentViewHolder holder, int position, ExpandableGroup group) {
        holder.entryNumberLabel.setText(group.getTitle());

        holder.entryButton.setOnClickListener(view -> new AlertDialog.Builder(context)
            .setTitle("Delete Entry?")
            .setIcon(R.drawable.ic_warning_black_24dp)
            .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                UserDataSingleton.getInstance().getRoomDB().trackedSkillUpDao().delete((((TrackedSkillUpParent) group).trackedSkillUp));
                ((TrackedSkillUpsActivity) context).refreshData();
            })
            .setNegativeButton(android.R.string.no, null).show());

        holder.entryButton.setBackgroundResource(R.drawable.ic_delete_black_24dp);
    }

    public void onBindChildViewHolder(TrackedSkillUpAdapter.TrackedSkillUpChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Entry entry = ((TrackedSkillUpAdapter.TrackedSkillUpParent) group).getItems().get(childIndex);
        holder.name.setText(entry.getMaterial().getName());
        holder.count.setText(String.valueOf(entry.getCount()));
        holder.thumbnail.setImageDrawable(Utilities.loadImageFromStorage(entry.getMaterial().getIconURL(), context));
    }

}