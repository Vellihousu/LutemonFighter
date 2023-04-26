package veikko.vanninen.lutemonht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    private LinearLayout listOfLutemons;
    private Button trainButton;
    private TextView trainlog;
    private ArrayList<Lutemon> lutemonsOnTraining = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<CheckBox> checkedCheckboxes = new ArrayList<>();
    private ArrayList<Lutemon> trainingLutemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        // Set name for header.
        getSupportActionBar().setTitle(R.string.training);

        listOfLutemons = findViewById(R.id.llLutemons);
        trainButton = findViewById(R.id.btnTrain);
        trainlog = findViewById(R.id.tvTrainLog);

        createCheckBoxesForLutemons();

        trainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check that lutemons are selected to train
                int checkboxChecked = 0;
                for (CheckBox cb : checkBoxes) {
                    if (cb.isChecked()) {
                        checkboxChecked++;
                        checkedCheckboxes.add(cb);
                    }
                }
                if (checkboxChecked == 0) {
                    Toast.makeText(getApplicationContext(), "Select Lutemon(s) to train.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    for (CheckBox cb : checkedCheckboxes) {
                        if (cb.isChecked()) {
                            trainingLutemons.add(Storage.getInstance().getLutemon(cb.getId()));
                        }
                    }
                    train(trainingLutemons);
                    trainingLutemons.clear();
                    createCheckBoxesForLutemons();
                    checkedCheckboxes.clear();
                }
            }
        });
    }

    private void train(ArrayList<Lutemon> trainingLutemons) {
        int trainExp = 1;
        StringBuilder stringBuilder = new StringBuilder();
        trainlog.setText("");
        for  (Lutemon lutemon : trainingLutemons) {
            stringBuilder.append(lutemon.getName() + " (" + lutemon.getColor() + ") " + "att: "
                    + lutemon.getAttack() + "; def: " + lutemon.getDefence() + "; exp: "
                    + lutemon.getExperience() + "; health: " + lutemon.getHealth() + "/"
                    + lutemon.getMaxHealth() + "\n");
            lutemon.setExperience(trainExp);
            lutemon.setStats(trainExp);
            stringBuilder.append(lutemon.getName() + " (" + lutemon.getColor()
                    + ") trains and gains " + trainExp + " experience points." + "\n");
            stringBuilder.append(lutemon.getName() + " (" + lutemon.getColor()
                    + ") " + "att: " + lutemon.getAttack() + "; def: "
                    + lutemon.getDefence() + "; exp: " + lutemon.getExperience()
                    + "; health: " + lutemon.getHealth() + "/"
                    + lutemon.getMaxHealth() + "\n");
        }
        //Print trainlog to screen.
        trainlog.setText(stringBuilder);
    }

    private void createCheckBoxesForLutemons() {
        CheckBox checkBox;
        lutemonsOnTraining = Storage.getInstance().getLutemonsToBattlefield();
        checkBoxes.clear();
        listOfLutemons.removeAllViews();
        int i = 0;

        for (Lutemon lutemon : lutemonsOnTraining) {
            checkBox = new CheckBox(this);
            checkBox.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
            checkBox.setId(i++);
            listOfLutemons.addView(checkBox);
            checkBoxes.add(checkBox);
        }
    }

}