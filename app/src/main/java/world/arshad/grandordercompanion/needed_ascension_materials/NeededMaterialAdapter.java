package world.arshad.grandordercompanion.needed_ascension_materials;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arshad on 04/01/2018.
 */

public class NeededMaterialAdapter extends RecyclerView.Adapter<NeededMaterialAdapter.ViewHolder> {

    private final List<NeededMaterialEntry> entries = new ArrayList<>();
    private final Context context;

    private final View.OnClickListener servantOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //What is the Law of Demeter?
            String message = Joiner.on("\n").join(entries.get(((NeededAscensionMaterialsActivity) context).materialList.getChildAdapterPosition(view)).getServantNames());
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Materials needed for...");
            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Dismiss",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.needed_material_child_name)
        TextView name;
        @BindView(R.id.needed_material_child_count)
        TextView count;
        @BindView(R.id.needed_material_child_thumbnail)
        ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public NeededMaterialAdapter(List<NeededMaterialEntry> entries, Context context) {
        this.entries.addAll(entries);
        this.context = context;
    }

    @Override
    public NeededMaterialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View servantInfoView = inflater.inflate(R.layout.needed_material_entry, parent, false);
        servantInfoView.setOnClickListener(servantOnClickListener);
        return new ViewHolder(servantInfoView);
    }

    @Override
    public void onBindViewHolder(NeededMaterialAdapter.ViewHolder holder, int position) {
        final NeededMaterialEntry entry = entries.get(position);

        holder.name.setText(entry.getMaterial().getName());
        holder.count.setText(String.valueOf(entry.getCount()));
        holder.thumbnail.setImageBitmap(Utilities.loadImageFromStorage(context.getFilesDir() + entry.getMaterial().getIconURL()));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }


}
