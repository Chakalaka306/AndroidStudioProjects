package fh_muenster.de.liveticker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Spinner ligen = (Spinner)findViewById(R.id.uebersicht_ligen);
        ArrayAdapter<CharSequence> adapter4 =
                ArrayAdapter.createFromResource(
                        this, R.array.uebersicht_ligen,
                        android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        ligen.setAdapter(adapter4);

        Spinner teams = (Spinner)findViewById(R.id.uebersicht_teams);
        ArrayAdapter<CharSequence> adapter5 =
                ArrayAdapter.createFromResource(
                        this, R.array.uebersicht_teams,
                        android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        teams.setAdapter(adapter5);


    }
}
