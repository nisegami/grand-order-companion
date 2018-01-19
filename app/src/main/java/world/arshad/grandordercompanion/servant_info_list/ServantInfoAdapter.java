package world.arshad.grandordercompanion.servant_info_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;
import world.arshad.grandordercompanion.servant_info.ServantInfoActivity;
import world.arshad.grandordercompanion.utils.Utilities;

/**
 * Created by arsha on 25/12/2017.
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
        View servantInfoView = inflater.inflate(R.layout.servant_info_entry, parent, false);
        servantInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ServantInfoActivity.class);
                intent.putExtra("servant", servants.get(((ServantInfoListActivity) context).servantInfoList.getChildAdapterPosition(view)));
                context.startActivity(intent);
            }
        });
        return new ViewHolder(servantInfoView);
    }

    @Override
    public void onBindViewHolder(ServantInfoAdapter.ViewHolder holder, int position) {
        final ServantInfo servant = servants.get(position);

        holder.name.setText(servant.getName());

        String thumbnailPath = context.getFilesDir() + servant.getThumbnailURL();

        File thumbnail = new File(thumbnailPath);
        if (thumbnail.exists()) {
            holder.thumbnail.setImageBitmap(Utilities.loadImageFromStorage(thumbnailPath));
        } else {
            holder.thumbnail.setImageBitmap(Utilities.loadImageFromStorage(context.getFilesDir() + servant.getDefaultThumbnail()));
        }
    }

    @Override
    public int getItemCount() {
        return servants.size();
    }
}
