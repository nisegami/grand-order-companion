package world.arshad.grandordercompanion.all_servants;

import android.support.v7.widget.RecyclerView;
import world.arshad.grandordercompanion.model.Servant;

import java.util.List;

abstract class ServantAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    public abstract void setData(List<Servant> servants);
}
