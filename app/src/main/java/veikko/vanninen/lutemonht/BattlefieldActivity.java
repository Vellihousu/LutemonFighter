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
import java.util.Collections;

public class BattlefieldActivity extends AppCompatActivity {
    private LinearLayout listOfLutemons;
    private Button fightButton;
    private TextView battleLog;
    private ArrayList<Lutemon> lutemonsOnBattlefield = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private ArrayList<CheckBox> checkedCheckboxes = new ArrayList<>();
    private ArrayList<Lutemon> fightingLutemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battlefield);

        // Set name for header.
        getSupportActionBar().setTitle(R.string.select_arena_lutemons);

        listOfLutemons = findViewById(R.id.llLutemons);
        fightButton = findViewById(R.id.btnFight);
        battleLog = findViewById(R.id.tvBattleLog);

        createCheckBoxesForLutemons();

        fightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Check that two lutemons are selected to fight
                int checkboxChecked = 0;
                for (CheckBox cb : checkBoxes) {
                    if (cb.isChecked()) {
                        checkboxChecked++;
                        checkedCheckboxes.add(cb);
                    }
                }
                if (checkboxChecked != 2) {
                    Toast.makeText(getApplicationContext(), "Select two Lutemons to fight.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    for (CheckBox cb : checkedCheckboxes) {
                        if (cb.isChecked()) {
                            fightingLutemon.add(Storage.getInstance().getLutemon(cb.getId()));
                        }
                    }
                    fight(fightingLutemon);
                    fightingLutemon.clear();
                    createCheckBoxesForLutemons();
                    checkedCheckboxes.clear();
                }
            }
        });
    }
    //Method for creating checkboxes to Lutemons
    private void createCheckBoxesForLutemons() {
        CheckBox checkBox;
        lutemonsOnBattlefield = Storage.getInstance().getLutemonsToBattlefield();
        checkBoxes.clear();
        listOfLutemons.removeAllViews();
        int i = 0;

        for (Lutemon lutemon : lutemonsOnBattlefield) {
            checkBox = new CheckBox(this);
            checkBox.setText(lutemon.getName() + " (" + lutemon.getColor() + ")" + " Att: "
                    + lutemon.getAttack() + " Def: " + lutemon.getDefence() + " HP: "
                    + lutemon.getHealth() + "/" + lutemon.getMaxHealth());
            checkBox.setId(i++);
            listOfLutemons.addView(checkBox);
            checkBoxes.add(checkBox);
        }
    }

    //Method for two Lutemons to fight with each other
    private void fight (ArrayList<Lutemon> fightingLutemon) {
        //Draw random starter Lutemon
        Collections.shuffle(fightingLutemon);
        Lutemon firstLutemon = fightingLutemon.get(0);
        Lutemon secondLutemon = fightingLutemon.get(1);

        // Set how many experience points winner gets
        int winnerExp = 2;

        // Set winner and loser lutemon.
        int win = 1;
        int loss = 1;

        StringBuilder stringBuilder = new StringBuilder();
        battleLog.setText("");
        for  (Lutemon lutemon : fightingLutemon) {
            stringBuilder.append(lutemon.getName() + " (" + lutemon.getColor() + ") " + "att: "
                    + lutemon.getAttack() + "; def: " + lutemon.getDefence() + "; exp: "
                    + lutemon.getExperience() + "; health: " + lutemon.getHealth() + "/"
                    + lutemon.getMaxHealth() + "\n");
        }
        // while loop for Lutemon battle.
        while ((firstLutemon.getHealth() > 0) || (secondLutemon.getHealth() > 0)) {
            //first Lutemon attacks.
            stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor() + ")"
                    + " attacks " + secondLutemon.getName() + " ("
                    + secondLutemon.getColor() + ")." + "\n");
            secondLutemon.defence(firstLutemon);
            if (secondLutemon.getHealth() > 0) {
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                        + ")" + " manages to escape death." + "\n");
                for  (Lutemon lutemon : fightingLutemon) {
                    stringBuilder.append(lutemon.getName() + " (" + lutemon.getColor() + ") "
                            + "health: " + lutemon.getHealth() + "/"
                            + lutemon.getMaxHealth() + "\n");
                }
            } else {
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                        + ")" + " gets killed." + "\n");
                stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor() +
                        ") wins the battle." + "\n");
                //Lutemon deleted if dies.
                //Storage.getInstance().deleteLutemon(secondLutemon.getId());
                firstLutemon.setExperience(winnerExp);
                firstLutemon.setStats(winnerExp);
                firstLutemon.setWin(win);
                secondLutemon.setLoss(loss);
                stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor()
                        + ") has " + firstLutemon.getWins() + " wins total." + "\n");
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                        + ") has " + secondLutemon.getLoses() + " loses total." + "\n");
                stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor()
                        + ") gains " + winnerExp + " experience points." + "\n");
                stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor()
                        + ") " + "att: " + firstLutemon.getAttack() + "; def: "
                        + firstLutemon.getDefence() + "; exp: " + firstLutemon.getExperience()
                        + "; health: " + firstLutemon.getHealth() + "/"
                        + firstLutemon.getMaxHealth() + "\n");
                break;
            }
            //Second Lutemon attacks
            stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                    + ") " + "attacks " + firstLutemon.getName() + " ("
                    + firstLutemon.getColor() + ") " + "\n");
            firstLutemon.defence(secondLutemon);
            if (firstLutemon.getHealth() > 0) {
                stringBuilder.append(firstLutemon.getName() + "(" + firstLutemon.getColor()
                        + ")" + " manages to escape death." + "\n");
                for  (Lutemon lutemon : fightingLutemon) {
                    stringBuilder.append(lutemon.getName() + " (" + lutemon.getColor() + ") "
                            + "health: " + lutemon.getHealth()
                            + "/" + lutemon.getMaxHealth() + "\n");
                }
            } else {
                stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor()
                        + ")" + " gets killed." + "\n");
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor() +
                        ") wins the battle." + "\n");

                //Lutemon deleted id dies.
                //Storage.getInstance().deleteLutemon(firstLutemon.getId());
                secondLutemon.setExperience(winnerExp);
                secondLutemon.setStats(winnerExp);
                secondLutemon.setWin(win);
                firstLutemon.setLoss(loss);
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                        + ") has " + secondLutemon.getWins() + " wins total." + "\n");
                stringBuilder.append(firstLutemon.getName() + " (" + firstLutemon.getColor()
                        + ") has " + firstLutemon.getLoses() + " loses total." + "\n");
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                        + ") gains " + winnerExp + " experience points." + "\n");
                stringBuilder.append(secondLutemon.getName() + " (" + secondLutemon.getColor()
                        + ") " + "att: " + secondLutemon.getAttack() + "; def: "
                        + secondLutemon.getDefence() + "; exp: " + secondLutemon.getExperience()
                        + "; health: " + secondLutemon.getHealth() + "/"
                        + secondLutemon.getMaxHealth() + "\n");
                break;
            }
        }
        stringBuilder.append("The battle is over."+ "\n");
        //Heal survived Lutemons to maxHealth.
        for (Lutemon lutemon : fightingLutemon) {
            lutemon.healHealth();
        }
        //Print battlelog to screen.
        battleLog.setText(stringBuilder);


    }
}