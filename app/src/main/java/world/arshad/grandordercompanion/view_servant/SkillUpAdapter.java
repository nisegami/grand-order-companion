package world.arshad.grandordercompanion.view_servant;

import android.content.Context;
import android.support.annotation.NonNull;
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
import world.arshad.grandordercompanion.Utilities;
import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.model.SkillUpEntry;

/**
 * Created by arsha on 21/03/2018.
 */

public class SkillUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PARENT = 0, CHILD = 1;
    private final Context context;
    private List<Object> items;
    private String skillName;

    public static class SkillUpParentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.skill_up_parent_label)
        TextView skillUpLabel;

        @BindView(R.id.skill_up_parent_button_1)
        ImageButton trackButton1;

        @BindView(R.id.skill_up_parent_button_2)
        ImageButton trackButton2;

        public SkillUpParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class SkillUpChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.skill_up_child_name)
        TextView name;

        @BindView(R.id.skill_up_child_count)
        TextView count;

        @BindView(R.id.skill_up_child_thumbnail)
        ImageView thumbnail;

        public SkillUpChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public SkillUpAdapter(Context context) {
        this.context = context;
    }

    public void setData(Servant servant, int skillNum) {

        List<SkillUp> list;

        switch (skillNum) {
            case 1: {
                skillName = servant.getSkill1();
                list = servant.getSkillUps1();
            }
                break;
            case 2: {
                skillName = servant.getSkill2();
                list = servant.getSkillUps2();
            }
                break;
            case 3: {
                skillName = servant.getSkill3();
                list = servant.getSkillUps3();
            }
                break;
            default: {
                skillName = servant.getSkill1();
                list = servant.getSkillUps1();
            }
        }

        this.items = new ArrayList<>();

        for (SkillUp skillUp : list) {
            this.items.add(skillUp);
            this.items.addAll(skillUp.getSkillUpEntries());
        }

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case SkillUpAdapter.PARENT: {
                SkillUpParentViewHolder holder = (SkillUpParentViewHolder) viewHolder;
                SkillUp skillUp = ((SkillUp) items.get(position));
                holder.skillUpLabel.setText(skillUp.getTitle());
                switch (skillUp.getStatus()) {
                    case SkillUp.DONTCARE: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_eye_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof SkillUp) && (j <= position)) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (SkillUp.DONTCARE == otherSkillUp.getStatus()) {
                                                otherSkillUp.setStatus(SkillUp.TRACKED);
                                                ServantRepository.getInstance().updateSkillUp(otherSkillUp);
                                                notifyItemChanged(j);
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show());
                        holder.trackButton2.setVisibility(View.GONE);
                    }
                    break;
                    case SkillUp.TRACKED: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_eye_off_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as un-tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof SkillUp) && (j >= position)) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (SkillUp.TRACKED == otherSkillUp.getStatus()) {
                                                otherSkillUp.setStatus(SkillUp.DONTCARE);
                                                ServantRepository.getInstance().updateSkillUp(otherSkillUp);
                                                notifyItemChanged(j);
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show());
                        holder.trackButton2.setBackgroundResource(R.drawable.ic_check_black_24dp);
                        holder.trackButton2.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as completed?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof SkillUp) && (j <= position)) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (SkillUp.TRACKED == otherSkillUp.getStatus()) {
                                                otherSkillUp.setStatus(SkillUp.COMPLETED);
                                                ServantRepository.getInstance().updateSkillUp(otherSkillUp);
                                                notifyItemChanged(j);
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show());
                        holder.trackButton2.setVisibility(View.VISIBLE);
                    }
                    break;
                    case SkillUp.COMPLETED: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_delete_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as un-tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof SkillUp) && (j >= position)) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            otherSkillUp.setStatus(SkillUp.DONTCARE);
                                            ServantRepository.getInstance().updateSkillUp(otherSkillUp);
                                            notifyItemChanged(j);
                                        }
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show());
                        holder.trackButton2.setVisibility(View.GONE);
                    }
                }
                break;
            }
            case SkillUpAdapter.CHILD: {
                SkillUpChildViewHolder holder = (SkillUpChildViewHolder) viewHolder;
                SkillUpEntry skillUpEntry = ((SkillUpEntry) items.get(position));
                holder.name.setText(skillUpEntry.getMaterial().toString());
                holder.count.setText(String.valueOf(skillUpEntry.getCount()));
                holder.thumbnail.setImageDrawable(Utilities.loadDrawableFromAssets(skillUpEntry.getMaterial().getIconPath(), context.getAssets()));
                break;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case SkillUpAdapter.PARENT: {
                View view = inflater.inflate(R.layout.entry_skill_up_parent, viewGroup, false);
                return new SkillUpParentViewHolder(view);
            }
            case SkillUpAdapter.CHILD: {
                View view = inflater.inflate(R.layout.entry_skill_up_child, viewGroup, false);
                return new SkillUpChildViewHolder(view);
            }
        }
        return null; // LOL
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof SkillUp) {
            return SkillUpAdapter.PARENT;
        } else if (items.get(position) instanceof SkillUpEntry) {
            return SkillUpAdapter.CHILD;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
