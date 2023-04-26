package veikko.vanninen.lutemonht;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    TextView lutemonName,lutemonColor, lutemonAttack, lutemonDefence, lutemonHealth, lutemonExperience;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonName = itemView.findViewById(R.id.txtLutemonName);
        lutemonColor = itemView.findViewById(R.id.txtLutemonColor);
        lutemonAttack = itemView.findViewById(R.id.txtAttack);
        lutemonDefence = itemView.findViewById(R.id.txtDefence);
        lutemonHealth = itemView.findViewById(R.id.txtHealth);
        lutemonExperience = itemView.findViewById(R.id.txtExperience);
    }
}
