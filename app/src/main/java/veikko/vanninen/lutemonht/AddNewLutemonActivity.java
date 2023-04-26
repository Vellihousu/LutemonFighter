package veikko.vanninen.lutemonht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddNewLutemonActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private RadioGroup radioGroup;
    private EditText ptName;
    private Context context;
    private Button addNewLutemon;
    private RadioButton radioButton;
    private Storage storage = Storage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lutemon);
        // Set name for header.
        getSupportActionBar().setTitle(R.string.add_new_lutemon);

        ptName = findViewById(R.id.ptLutemonName);
        radioGroup = findViewById(R.id.rgLutemonType);
        addNewLutemon = findViewById(R.id.btnAddNewLutemon);
        context = this;
    }

    public void addNewLutemon (View view) {

        String lutemonName = ptName.getText().toString();

        if (lutemonName.equals("") || getLutemonColor(view).equals("")) {
            Toast toast = Toast.makeText(this, "Give Lutemon color and name!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Lutemon lutemon = null;
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rbWhiteType:
                    lutemon = new White(lutemonName);
                    break;
                case R.id.rbGreenType:
                    lutemon = new Green(lutemonName);
                    break;
                case R.id.rbPinkType:
                    lutemon = new Pink(lutemonName);
                    break;
                case R.id.rbOrangeType:
                    lutemon = new Orange(lutemonName);
                    break;
                case R.id.rbBlackType:
                    lutemon = new Black(lutemonName);
                    break;
            }
            storage.addLutemon(lutemon);
            //storage.saveLutemons(this);

            Toast toast = Toast.makeText(this, "Lutemon added.", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    public String getLutemonColor (View view) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioButtonId);
        String color = radioButton.getText().toString();

        return color;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}