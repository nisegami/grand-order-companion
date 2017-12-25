package world.arshad.grandordercompanion.servant_info_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.data.domain_data.ServantInfo;

/**
 * Created by arsha on 25/12/2017.
 */

public class ServantInfoAdapter extends RecyclerView.Adapter<ServantInfoAdapter.ViewHolder> {

    private final List<ServantInfo> servants = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name) TextView name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ServantInfoAdapter(List<ServantInfo> servants) {
        this.servants.addAll(servants);
    }

    @Override
    public ServantInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.servant_info_entry, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ServantInfoAdapter.ViewHolder holder, int position) {
        final String name = servants.get(position).getName();

        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return servants.size();
    }
}
