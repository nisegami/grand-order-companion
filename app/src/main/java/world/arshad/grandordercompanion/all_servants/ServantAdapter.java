package world.arshad.grandordercompanion.all_servants;

import android.content.Context;
import android.content.Intent;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
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
import world.arshad.grandordercompanion.Utilities;
import world.arshad.grandordercompanion.data.Servant;
import world.arshad.grandordercompanion.view_servant.ServantActivity;

/**
 * Created by arsha on 20/03/2018.
 */

public class ServantAdapter extends RecyclerView.Adapter<ServantAdapter.ViewHolder> {

    private List<Servant> servants = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.servant_info_card)
        CardView servantCard;
        @BindView(R.id.servant_info_entry_name)
        TextView name;
        @BindView(R.id.servant_info_entry_thumbnail)
        ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ServantAdapter(List<Servant> servants, Context context) {
        this.context = context;
        setData(servants);
    }

    public void setData(List<Servant> servants) {
        this.servants = servants;
        notifyDataSetChanged();
    }


    @Override
    public ServantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View servantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_servant, parent, false);
        return new ViewHolder(servantView);
    }

    @Override
    public void onBindViewHolder(ServantAdapter.ViewHolder holder, int position) {
        final Servant servant = servants.get(position);

        holder.name.setText(servant.getName());
        holder.thumbnail.setImageDrawable(Utilities.loadDrawableFromAssets(servant.getThumbnailPath(1), context.getAssets()));

        if (context.getSharedPreferences("goc", Context.MODE_PRIVATE).getBoolean("use_colors", false)) {
            Palette.from(Utilities.loadBitmapFromAssets(servant.getThumbnailPath(1), context.getAssets())).generate(palette -> {
                try {
                    holder.servantCard.setCardBackgroundColor(palette.getLightMutedSwatch().getRgb());
                } catch (NullPointerException e) {
                    holder.servantCard.setCardBackgroundColor(palette.getLightMutedColor(context.getColor(R.color.colorBackgroundGray)));
                }
            });
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ServantActivity.class);
            intent.putExtra("servant_id", servant.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return servants.size();
    }

}
