
package veikko.vanninen.lutemonht;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Class for holderadapter.
public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }


    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.lutemonName.setText(lutemons.get(position).name);
        holder.lutemonColor.setText("Color:" + lutemons.get(position).color);
        holder.lutemonAttack.setText("Attack:" + String.valueOf(lutemons.get(position).attack));
        holder.lutemonDefence.setText("Defence:" + String.valueOf(lutemons.get(position).defence));
        holder.lutemonHealth.setText("Health:" + String.valueOf(lutemons.get(position).health) + "/"
                + String.valueOf(lutemons.get(position).maxHealth));
        holder.lutemonExperience.setText("Experience points:"
                + String.valueOf(lutemons.get(position).experience));
        holder.lutemonImage.setImageResource(lutemons.get(position).getImage());
        holder.lutemonWins.setText("Total wins: " + String.valueOf(lutemons.get(position).wins));
        holder.lutemonLoses.setText("Total loses: "+ String.valueOf(lutemons.get(position).loses));
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
