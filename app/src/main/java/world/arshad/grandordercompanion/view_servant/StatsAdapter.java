package world.arshad.grandordercompanion.view_servant;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.model.Servant;

import java.util.ArrayList;
import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder>  {

    private final int NUM_STATS = 15;

    private final String[] labels = {"Class:", "Rarity:", "Cost:", "Base Attack:", "Max Attack:", "Grail Attack:", "Base HP:", "Max HP:", "Grail HP:", "Deck:", "Growth Curve:", "Attribute:", "Alignment:", "Gender:", "Seiyuu:"};

    private final int color1 = Color.WHITE;
    private final int color2 = Color.LTGRAY;

    private Servant servant;
    private final List<String> values = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.entry_stat_title)
        TextView title;
        @BindView(R.id.entry_stat_value)
        TextView value;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public StatsAdapter(Servant servant) {
        this.servant = servant;
        values.add(servant.getServantClass().toString());
        values.add(String.valueOf(servant.getRarity()));
        values.add(String.valueOf(servant.getCost()));
        values.add(String.valueOf(servant.getBaseAtk()));
        values.add(String.valueOf(servant.getMaxAtk()));
        values.add(String.valueOf(servant.getGrailAtk()));
        values.add(String.valueOf(servant.getBaseHp()));
        values.add(String.valueOf(servant.getMaxHp()));
        values.add(String.valueOf(servant.getGrailHp()));
        values.add(servant.getDeck());
        values.add(servant.getGrowthCurve().toString());
        values.add(servant.getAttribute());
        values.add(servant.getAlignment());
        values.add(servant.getGender());
        values.add(servant.getSeiyuu());
    }

    @Override
    public StatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View servantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_stat, parent, false);
        return new StatsAdapter.ViewHolder(servantView);
    }

    @Override
    public void onBindViewHolder(StatsAdapter.ViewHolder holder, int position) {

        holder.title.setText(labels[position]);
        holder.value.setText(values.get(position));

        if (0 == (position % 2)) {
            holder.title.setBackgroundColor(color1);
            holder.value.setBackgroundColor(color1);
        } else {
            holder.title.setBackgroundColor(color2);
            holder.value.setBackgroundColor(color2);
        }
    }

    @Override
    public int getItemCount() {
        return NUM_STATS - 1; // Hide seiyuu
    }

}
