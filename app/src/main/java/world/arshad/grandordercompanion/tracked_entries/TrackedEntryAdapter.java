package world.arshad.grandordercompanion.tracked_entries;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.model.SkillUp;
import world.arshad.grandordercompanion.view_servant.ServantActivity;

public class TrackedEntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SERVANT = 0, ASCENSION = 1, SKILL = 3;
    private final Context context;
    private List<Object> items;

    public static class ServantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.servant_info_background)
        ConstraintLayout background;
        @BindView(R.id.servant_info_entry_name)
        TextView name;
        @BindView(R.id.servant_info_entry_thumbnail)
        ImageView thumbnail;

        public ServantViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class AscensionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ascension_parent_label)
        TextView ascensionLabel;

        @BindView(R.id.ascension_parent_button_1)
        ImageButton trackButton1;

        @BindView(R.id.ascension_parent_button_2)
        ImageButton trackButton2;

        public AscensionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class SkillUpViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.skill_up_parent_label)
        TextView skillUpLabel;

        @BindView(R.id.skill_up_parent_button_1)
        ImageButton trackButton1;

        @BindView(R.id.skill_up_parent_button_2)
        ImageButton trackButton2;

        public SkillUpViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public TrackedEntryAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setData(List<Object> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case TrackedEntryAdapter.SERVANT: {
                View view = inflater.inflate(R.layout.entry_servant_for_tracked_entries, viewGroup, false);
                return new ServantViewHolder(view);
            }
            case TrackedEntryAdapter.ASCENSION: {
                View view = inflater.inflate(R.layout.entry_ascension_parent, viewGroup, false);
                return new AscensionViewHolder(view);
            }
            case TrackedEntryAdapter.SKILL: {
                View view = inflater.inflate(R.layout.entry_skill_up_parent, viewGroup, false);
                return new SkillUpViewHolder(view);
            }
        }
        return null; // LOL
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case TrackedEntryAdapter.SERVANT: {
                ServantViewHolder holder = (ServantViewHolder) viewHolder;
                Servant servant = ((Servant) items.get(position));
                holder.name.setText(servant.getName());
                holder.thumbnail.setImageBitmap(Utilities.loadBitmapFromAssets(servant.getThumbnailPath(1), context.getAssets()));
                holder.background.setBackgroundColor(servant.getColor());

                holder.itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(context, ServantActivity.class);
                    intent.putExtra("servant_id", servant.getId());
                    context.startActivity(intent);
                });
            }
            break;
            case TrackedEntryAdapter.ASCENSION: {
                AscensionViewHolder holder = (AscensionViewHolder) viewHolder;
                Ascension ascension = ((Ascension) items.get(position));
                holder.ascensionLabel.setText(ascension.toString());
                holder.ascensionLabel.setOnClickListener(view -> {
                    Intent intent = new Intent(context, ServantActivity.class);
                    intent.putExtra("servant_id", ascension.getServantId());
                    intent.putExtra("screen_number", ServantActivity.ASCENSION);
                    context.startActivity(intent);
                });
                switch (ascension.getStatus()) {
                    case Ascension.DONTCARE: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_eye_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof Ascension) && (j < position)) {
                                            Ascension otherAscension = (Ascension) items.get(j);
                                            if (otherAscension.getServantId() != ascension.getServantId()) {
                                                continue;
                                            }
                                            if (Ascension.DONTCARE == otherAscension.getStatus()) {
                                                otherAscension.setStatus(Ascension.TRACKED);
                                                ServantRepository.getInstance().updateAscension(otherAscension);
                                                notifyItemChanged(j);
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show());
                        holder.trackButton2.setVisibility(View.GONE);
                    }
                    break;
                    case Ascension.TRACKED: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_eye_off_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as un-tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof Ascension) && (j >= position)) {
                                            Ascension otherAscension = (Ascension) items.get(j);
                                            if (otherAscension.getServantId() != ascension.getServantId()) {
                                                continue;
                                            }
                                            if (Ascension.TRACKED == otherAscension.getStatus()) {
                                                otherAscension.setStatus(Ascension.DONTCARE);
                                                ServantRepository.getInstance().updateAscension(otherAscension);
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
                                        if ((items.get(j) instanceof Ascension) && (j <= position)) {
                                            Ascension otherAscension = (Ascension) items.get(j);
                                            if (otherAscension.getServantId() != ascension.getServantId()) {
                                                continue;
                                            }
                                            if (Ascension.TRACKED == otherAscension.getStatus()) {
                                                otherAscension.setStatus(Ascension.COMPLETED);
                                                ServantRepository.getInstance().updateAscension(otherAscension);
                                                notifyItemChanged(j);
                                            }
                                        }
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null).show());
                        holder.trackButton2.setVisibility(View.VISIBLE);
                    }
                    break;
                    case Ascension.COMPLETED: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_delete_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as un-tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof Ascension) && (j >= position)) {
                                            Ascension otherAscension = (Ascension) items.get(j);
                                            if (otherAscension.getServantId() != ascension.getServantId()) {
                                                continue;
                                            }
                                            otherAscension.setStatus(Ascension.DONTCARE);
                                            ServantRepository.getInstance().updateAscension(otherAscension);
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
            case TrackedEntryAdapter.SKILL: {
                SkillUpViewHolder holder = (SkillUpViewHolder) viewHolder;
                SkillUp skillUp = ((SkillUp) items.get(position));
                holder.skillUpLabel.setText(skillUp.getTitle());
                holder.skillUpLabel.setOnClickListener(view -> {
                    Intent intent = new Intent(context, ServantActivity.class);
                    intent.putExtra("servant_id", skillUp.getServantId());
                    intent.putExtra("screen_number", ServantActivity.SKILL_UP);
                    context.startActivity(intent);
                });
                switch (skillUp.getStatus()) {
                    case SkillUp.DONTCARE: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_eye_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof SkillUp) && (j <= position) && ((SkillUp) items.get(j)).getSkillNumber() == skillUp.getSkillNumber()) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (otherSkillUp.getServantId() != skillUp.getServantId()) {
                                                continue;
                                            }
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
                                        if ((items.get(j) instanceof SkillUp) && (j >= position) && ((SkillUp) items.get(j)).getSkillNumber() == skillUp.getSkillNumber()) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (otherSkillUp.getServantId() != skillUp.getServantId()) {
                                                continue;
                                            }
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
                                        if ((items.get(j) instanceof SkillUp) && (j <= position) && ((SkillUp) items.get(j)).getSkillNumber() == skillUp.getSkillNumber()) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (otherSkillUp.getServantId() != skillUp.getServantId()) {
                                                continue;
                                            }
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
                                        if ((items.get(j) instanceof SkillUp) && (j >= position) && ((SkillUp) items.get(j)).getSkillNumber() == skillUp.getSkillNumber()) {
                                            SkillUp otherSkillUp = (SkillUp) items.get(j);
                                            if (otherSkillUp.getServantId() != skillUp.getServantId()) {
                                                continue;
                                            }
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
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Ascension) {
            return TrackedEntryAdapter.ASCENSION;
        } else if (items.get(position) instanceof SkillUp) {
            return TrackedEntryAdapter.SKILL;
        } else if (items.get(position) instanceof Servant) {
            return TrackedEntryAdapter.SERVANT;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
