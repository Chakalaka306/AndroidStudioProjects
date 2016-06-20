package fh_muenster.de.liveticker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * The type Favorit activity.
 */
public class FavoritActivity extends AppCompatActivity {

    /**
     * @author Andreas Blass , Kevin Gorter
     * @version 1.0
     */

    /**
     * Erstellen einer Instanz
     * @param savedInstanceState
     * Aufruf der Activity activtiy_favorit
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        /**
         *
         *@author Andreas Blass
         * Spinner anlegen um eine Liga auszuwählen
         * Name des Spinners: liga
         * aus dem Ordner arrays.xml werden die Arrays angezeigt
         * Name des Arrays : Liga_spinner
         * Auswählen einer Liga möglich
         */

        Spinner liga = (Spinner)findViewById(R.id.Liga_spinner);
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(
                        this, R.array.uebersicht_ligen,
                        android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        liga.setAdapter(adapter2);

        /**
         *
         *@author Andreas Blass
         * Spinner anlegen um eine Liga auszuwählen
         * Name des Spinners: Mannschaft
         * aus dem Ordner arrays.xml werden die Arrays angezeigt
         * Name des Arrays : Mannschaften_spinner
         * Auswählen einer Liga möglich
         */
        Spinner Mannschaft = (Spinner)findViewById(R.id.Mannschaften_spinner);
        ArrayAdapter<CharSequence> adapter3 =
                ArrayAdapter.createFromResource(
                        this, R.array.Mannschaften_spinner,
                        android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        Mannschaft.setAdapter(adapter3);

    }
}
