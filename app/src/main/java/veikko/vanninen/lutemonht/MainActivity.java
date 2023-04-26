package veikko.vanninen.lutemonht;

import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ImageButton saveLutemons, loadLutemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        saveLutemons = findViewById(R.id.imgbtnSave);
        loadLutemons = findViewById(R.id.imgbtnLoad);

        // Save lutemons by clicking save button.
        saveLutemons.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Storage.getInstance().saveLutemons(context)) {
                    Toast.makeText(getApplicationContext(), "Lutemons saved.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Could not save lutemons.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Load lutemons by clicking load button.
        loadLutemons.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Storage.getInstance().loadLutemons(context)) {
                    Toast.makeText(getApplicationContext(), "Lutemons loaded.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Could not load lutemons.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void switchToAddLutemonActivity (View view) {
        Intent intent = new Intent(this, AddNewLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemonsActivity (View view) {
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToBattlefieldActivity (View view) {
        Intent intent = new Intent(this, BattlefieldActivity.class);
        startActivity(intent);
    }

    public void switchToTrainingActivity (View view) {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }
}