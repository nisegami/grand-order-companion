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

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder>  {

    private final int NUM_STATS = 15;

    private final int SERVANT_CLASS = 0;
    private final int SERVANT_RARITY = 1;
    private final int SERVANT_COST = 2;
    private final int SERVANT_BASE_ATK = 3;
    private final int SERVANT_MAX_ATK = 4;
    private final int SERVANT_GRAIL_ATK = 5;
    private final int SERVANT_BASE_HP = 6;
    private final int SERVANT_MAX_HP = 7;
    private final int SERVANT_GRAIL_HP = 8;
    private final int SERVANT_DECK = 9;
    private final int SERVANT_GROWTH_CURVE = 10;
    private final int SERVANT_ATTRIBUTE = 11;
    private final int SERVANT_ALIGNMENT = 12;
    private final int SERVANT_GENDER = 13;
    private final int SERVANT_SEIYUU = 14;

    private final String SERVANT_CLASS_TITLE = "Class:";
    private final String SERVANT_RARITY_TITLE = "Rarity:";
    private final String SERVANT_COST_TITLE = "Cost:";
    private final String SERVANT_BASE_ATK_TITLE = "Base Attack:";
    private final String SERVANT_MAX_ATK_TITLE = "Max Attack:";
    private final String SERVANT_GRAIL_ATK_TITLE = "Grail Attack:";
    private final String SERVANT_BASE_HP_TITLE = "Base HP:";
    private final String SERVANT_MAX_HP_TITLE = "Max HP:";
    private final String SERVANT_GRAIL_HP_TITLE = "Grail HP:";
    private final String SERVANT_DECK_TITLE = "Deck:";
    private final String SERVANT_GROWTH_CURVE_TITLE = "Growth Curve:";
    private final String SERVANT_ATTRIBUTE_TITLE = "Attribute:";
    private final String SERVANT_ALIGNMENT_TITLE = "Alignment:";
    private final String SERVANT_GENDER_TITLE = "Gender:";
    private final String SERVANT_SEIYUU_TITLE = "Seiyuu:";

    private final int color1 = Color.WHITE;
    private final int color2 = Color.LTGRAY;


    private Servant servant;

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
        notifyDataSetChanged();
    }

    @Override
    public StatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View servantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_stat, parent, false);
        return new StatsAdapter.ViewHolder(servantView);
    }

    @Override
    public void onBindViewHolder(StatsAdapter.ViewHolder holder, int position) {
        switch (position) {
            case SERVANT_CLASS:
                holder.title.setText(SERVANT_CLASS_TITLE);
                holder.value.setText(servant.getServantClass().toString());
                break;
            case SERVANT_RARITY:
                holder.title.setText(SERVANT_RARITY_TITLE);
                holder.value.setText(String.valueOf(servant.getRarity()));
                break;
            case SERVANT_COST:
                holder.title.setText(SERVANT_COST_TITLE);
                holder.value.setText(String.valueOf(servant.getCost()));
                break;
            case SERVANT_BASE_ATK:
                holder.title.setText(SERVANT_BASE_ATK_TITLE);
                holder.value.setText(String.valueOf(servant.getBaseAtk()));
                break;
            case SERVANT_MAX_ATK:
                holder.title.setText(SERVANT_MAX_ATK_TITLE);
                holder.value.setText(String.valueOf(servant.getMaxAtk()));
                break;
            case SERVANT_GRAIL_ATK:
                holder.title.setText(SERVANT_GRAIL_ATK_TITLE);
                holder.value.setText(String.valueOf(servant.getGrailAtk()));
                break;
            case SERVANT_BASE_HP:
                holder.title.setText(SERVANT_BASE_HP_TITLE);
                holder.value.setText(String.valueOf(servant.getBaseHp()));
                break;
            case SERVANT_MAX_HP:
                holder.title.setText(SERVANT_MAX_HP_TITLE);
                holder.value.setText(String.valueOf(servant.getMaxHp()));
                break;
            case SERVANT_GRAIL_HP:
                holder.title.setText(SERVANT_GRAIL_HP_TITLE);
                holder.value.setText(String.valueOf(servant.getGrailHp()));
                break;
            case SERVANT_DECK:
                holder.title.setText(SERVANT_DECK_TITLE);
                holder.value.setText(servant.getDeck());
                break;
            case SERVANT_GROWTH_CURVE:
                holder.title.setText(SERVANT_GROWTH_CURVE_TITLE);
                holder.value.setText(servant.getGrowthCurve().toString());
                break;
            case SERVANT_ATTRIBUTE:
                holder.title.setText(SERVANT_ATTRIBUTE_TITLE);
                holder.value.setText(servant.getAttribute());
                break;
            case SERVANT_ALIGNMENT:
                holder.title.setText(SERVANT_ALIGNMENT_TITLE);
                holder.value.setText(servant.getAlignment());
                break;
            case SERVANT_GENDER:
                holder.title.setText(SERVANT_GENDER_TITLE);
                holder.value.setText(servant.getGender());
                break;
            case SERVANT_SEIYUU:
                holder.title.setText(SERVANT_SEIYUU_TITLE);
                holder.value.setText(servant.getSeiyuu());
                break;
        }

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
