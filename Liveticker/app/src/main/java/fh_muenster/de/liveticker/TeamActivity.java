package fh_muenster.de.liveticker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * The type Team activity.
 */
public class TeamActivity extends AppCompatActivity {

    /**
     * @author Andreas Blass , Kevin Gorter
     * @version 1.0
     */




    /**
     *
     * @author Andreas Blass, Kevin Gorter
     * @param savedInstanceState
     *  Aufruf der activity_team
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        /**
        *
         *@author Andreas Blass
         * Spinner anlegen um eine Liga auszuwählen
         * Name des Spinners: ligen
         * aus dem Ordner arrays.xml werden die Arrays angezeigt
         * Name des Arrays : uebersicht_Ligen
         * Auswählen einer Liga möglich
         */

        Spinner ligen = (Spinner)findViewById(R.id.uebersicht_ligen);
        ArrayAdapter<CharSequence> adapter4 =
                ArrayAdapter.createFromResource(
                        this, R.array.uebersicht_ligen,
                        android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        ligen.setAdapter(adapter4);

        /**
         *
         *@author Andreas Blass
         * Spinner anlegen um eine Liga auszuwählen
         * Name des Spinners: teams
         * aus dem Ordner arrays.xml werden die Arrays angezeigt
         * Name des Arrays : uebersicht_teams
         * Auswählen einer Liga möglich
         */


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
