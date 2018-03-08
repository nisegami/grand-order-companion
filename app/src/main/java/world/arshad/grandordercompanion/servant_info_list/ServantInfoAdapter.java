package world.arshad.grandordercompanion.servant_info_list;

import android.content.Context;
import android.content.Intent;
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
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;
import world.arshad.grandordercompanion.servant_info.ServantInfoActivity;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arshad on 25/12/2017.
 */

public class ServantInfoAdapter extends RecyclerView.Adapter<ServantInfoAdapter.ViewHolder> {

    private final List<ServantInfo> servants = new ArrayList<>();
    private final Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.servant_info_entry_name) TextView name;
        @BindView(R.id.servant_info_entry_thumbnail) ImageView thumbnail;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ServantInfoAdapter(List<ServantInfo> servants, Context context) {
        this.servants.addAll(servants);
        this.context = context;
    }

    @Override
    public ServantInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View servantInfoView = inflater.inflate(R.layout.entry_servant, parent, false);
        servantInfoView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ServantInfoActivity.class);
            intent.putExtra("servant", servants.get(((ServantInfoListActivity) context).servantInfoList.getChildAdapterPosition(view)));
            context.startActivity(intent);
        });
        return new ViewHolder(servantInfoView);
    }

    @Override
    public void onBindViewHolder(ServantInfoAdapter.ViewHolder holder, int position) {
        final ServantInfo servant = servants.get(position);

        holder.name.setText(servant.getName());
        holder.thumbnail.setImageDrawable(Utilities.loadImageFromStorage(servant.getThumbnailURL(1), context.getAssets()));
    }

    @Override
    public int getItemCount() {
        return servants.size();
    }
}
