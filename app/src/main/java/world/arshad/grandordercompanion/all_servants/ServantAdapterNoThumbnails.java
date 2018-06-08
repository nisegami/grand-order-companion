package world.arshad.grandordercompanion.all_servants;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.Utilities;
import world.arshad.grandordercompanion.model.Servant;
import world.arshad.grandordercompanion.view_servant.ServantActivity;

import java.util.ArrayList;
import java.util.List;

public class ServantAdapterNoThumbnails extends ServantAdapter<ServantAdapterNoThumbnails.ViewHolder> {

    private List<Servant> servants = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.servant_background)
        RelativeLayout servantBackground;

        @BindView(R.id.servant_info_entry_name)
        TextView name;

        int servantId;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ServantActivity.class);
            intent.putExtra("servant_id", servantId);
            v.getContext().startActivity(intent);
        }
    }

    public ServantAdapterNoThumbnails(Context context) {
        this.context = context;
        setData(new ArrayList<>());
    }

    public void setData(List<Servant> servants) {
        this.servants = servants;
        notifyDataSetChanged();
    }

    @Override
    public ServantAdapterNoThumbnails.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View servantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_servant_no_thumbnail, parent, false);
        return new ViewHolder(servantView);
    }

    @Override
    public void onBindViewHolder(ServantAdapterNoThumbnails.ViewHolder holder, int position) {
        final Servant servant = servants.get(position);

        holder.name.setText(servant.getName());
        holder.servantBackground.setBackgroundColor(servant.getColor());
        holder.servantId = servant.getId();
    }

    @Override
    public int getItemCount() {
        return servants.size();
    }
}