package fh_muenster.de.liveticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * The type Single game activity.
 */
public class SingleGameActivity extends AppCompatActivity {

    /**
     * @author Andreas Blass , Kevin Gorter
     * @version 1.0
     */


    /**
     * @author Andreas Blass , Kevin Gorter
     * @param savedInstanceState
     * Aufruf der Activity Single_game
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);

        /**
         * @author Andreas Blass , Kevin Gorter
         * Anlegung eines Spiels
         */
        Intent user_infos = getIntent();
        String spiel = user_infos.getStringExtra("Spiel");

        /**
         * @author Andreas Blass, Kevin Gorter
         * Überprüfung ob ein Spiel vorhanden ist
         * 1. Ist ein Spiel vorhanden , erfolgt die Textausgabe "Willkommen bei Spiel ..."
         * 2. Ist kein Spiel vorhanden , passiert nichts
         */

        if(spiel!=null && !spiel.equals(""))
        {
            TextView text = (TextView)findViewById(R.id.TEXT);
            text.setText("Willkommen bei Spiel "+spiel);
        }


    }
}
