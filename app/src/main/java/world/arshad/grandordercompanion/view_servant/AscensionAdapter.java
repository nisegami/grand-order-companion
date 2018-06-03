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
import world.arshad.grandordercompanion.database.ServantRepository;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.Utilities;
import world.arshad.grandordercompanion.model.Ascension;
import world.arshad.grandordercompanion.model.AscensionEntry;

/**
 * Created by arsha on 21/03/2018.
 */

public class AscensionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PARENT = 0, CHILD = 1;
    private final Context context;
    private List<Object> items;
    private List<Ascension> ascensions;
    
    public static class AscensionParentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ascension_parent_label)
        TextView ascensionLabel;

        @BindView(R.id.ascension_parent_button_1)
        ImageButton trackButton1;

        @BindView(R.id.ascension_parent_button_2)
        ImageButton trackButton2;

        public AscensionParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class AscensionChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ascension_child_name)
        TextView name;

        @BindView(R.id.ascension_child_count)
        TextView count;

        @BindView(R.id.ascension_child_thumbnail)
        ImageView thumbnail;

        public AscensionChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public AscensionAdapter(Context context, List<Ascension> ascensions) {
        this.context = context;
        setData(ascensions);
    }

    public void setData(List<Ascension> ascensions) {
        this.items = new ArrayList<>();
        this.ascensions = ascensions;

        for (Ascension ascension : ascensions) {
            this.items.add(ascension);
            this.items.addAll(ascension.getAscensionEntries());
        }

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case AscensionAdapter.PARENT: {
                AscensionParentViewHolder holder = (AscensionParentViewHolder) viewHolder;
                Ascension ascension = ((Ascension) items.get(position));
                holder.ascensionLabel.setText(ascension.toString());

                switch (ascension.getStatus()) {
                    case Ascension.DONTCARE: {
                        holder.trackButton1.setBackgroundResource(R.drawable.ic_eye_black_24dp);
                        holder.trackButton1.setOnClickListener(view -> new AlertDialog.Builder(context)
                                .setTitle("Mark as tracked?")
                                .setIcon(R.drawable.ic_warning_black_24dp)
                                .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                                    for (int j = 0; j < items.size(); j++) {
                                        if ((items.get(j) instanceof Ascension) && (j <= position)) {
                                            Ascension otherAscension = (Ascension) items.get(j);
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
            case AscensionAdapter.CHILD: {
                AscensionChildViewHolder holder = (AscensionChildViewHolder) viewHolder;
                AscensionEntry ascensionEntry = ((AscensionEntry) items.get(position));
                holder.name.setText(ascensionEntry.getMaterial().toString());
                holder.count.setText(String.valueOf(ascensionEntry.getCount()));
                holder.thumbnail.setImageBitmap(Utilities.loadBitmapFromAssets(ascensionEntry.getMaterial().getIconPath(), context.getAssets()));
                break;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case AscensionAdapter.PARENT: {
                View view = inflater.inflate(R.layout.entry_ascension_parent, viewGroup, false);
                return new AscensionParentViewHolder(view);
            }
            case AscensionAdapter.CHILD: {
                View view = inflater.inflate(R.layout.entry_ascension_child, viewGroup, false);
                return new AscensionChildViewHolder(view);
            }
        }
        return null; // LOL
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Ascension) {
            return AscensionAdapter.PARENT;
        } else if (items.get(position) instanceof AscensionEntry) {
            return AscensionAdapter.CHILD;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
