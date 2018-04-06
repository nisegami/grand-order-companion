package world.arshad.grandordercompanion.needed_materials;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.Utilities;
import world.arshad.grandordercompanion.data.Material;

import static android.content.DialogInterface.BUTTON_NEUTRAL;

/**
 * Created by arsha on 22/03/2018.
 */

public class NeededMaterialAdapter extends RecyclerView.Adapter<NeededMaterialAdapter.ViewHolder> {

    private Context context;

    private Map<Material, Integer> materialCounts;
    private Map<Material, String> materialStrings;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.needed_material_name)
        TextView name;

        @BindView(R.id.needed_material_count)
        TextView count;

        @BindView(R.id.needed_material_thumbnail)
        ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public NeededMaterialAdapter(Context context, Map<Material, Integer> materialCounts, Map<Material, String> materialStrings) {
        this.context = context;
        setData(materialCounts, materialStrings);
    }

    public void setData(Map<Material, Integer> materialCounts, Map<Material, String> materialStrings) {
        this.materialCounts = materialCounts;
        this.materialStrings = materialStrings;
        notifyDataSetChanged();
    }


    @Override
    public NeededMaterialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View materialView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_needed_material, parent, false);
        return new NeededMaterialAdapter.ViewHolder(materialView);
    }

    @Override
    public void onBindViewHolder(NeededMaterialAdapter.ViewHolder holder, int position) {
        Material material = (Material) materialCounts.keySet().toArray()[position];
        int count = materialCounts.get(material);
        String string = materialStrings.get(material);

        holder.name.setText(material.toString());
        holder.count.setText(String.valueOf(count));
        holder.thumbnail.setImageDrawable(Utilities.loadDrawableFromAssets(material.getIconPath(), context.getAssets()));

        holder.itemView.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Materials needed for...");
            alertDialog.setMessage(string);
            alertDialog.setButton(BUTTON_NEUTRAL, "Dismiss",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return materialCounts.keySet().size();
    }


}
