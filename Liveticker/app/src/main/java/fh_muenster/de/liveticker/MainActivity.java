package fh_muenster.de.liveticker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * @author Andreas Blass , Kevin Gorter
     * @version 1.0
     */


    /**
     * The Txtheadline.
     *
     * @author Andreas BlassInitialisieren der Schriftblöcke , bzw der Schriftart
     */
    TextView txtheadline;
    /**
     * The Schriftart opensans.
     */
    Typeface schriftart_opensans;




    /**
     *
     * @author Andreas Blass
     * @param savedInstanceState
     *  Aufruf der activity_main
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * @author Andreas Blass
         * festlegen des Textes der in der Schrfitart angezeigt werden soll
         * hier : txtheadline
         * Schriftart: opensans
         * Datei: "schriftart_opensans " befindet sich in dem asset
         * unter dem Ordner Schriftarten
         */
        txtheadline = (TextView)  findViewById(R.id.txtheadline);
        schriftart_opensans=Typeface.createFromAsset(getAssets(), "schriftarten/opensans.ttf");
        txtheadline.setTypeface(schriftart_opensans);


        /**
         *
         *@author Kevin Gorter
         * Spinner anlegen um ein Event auszuwählen
         * Name des Spinners: s1
         * aus dem Ordner arrays.xml werden die Arrays angezeigt
         * Name des Arrays : event_spinner
         * Auswählen eines Events möglich
         */

        Spinner s1 = (Spinner)findViewById(R.id.event_spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this, R.array.event_spinner,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);


    }


    /**
     * author Andreas Blass , Kevin Gorter
     * @param menu
     * Erstellung des Menüs
     * zusätzlich werden die Items der action bar hinzugefügt sofern sie vorhanden ist
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * Aufruf der Methode onItemSelected
     * @param item
     * Die action bar kann mit Klicks umgehen solange  die jeweiligen parent activitys in der
     * AndroidManifest.xml definiert wurden
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();



        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
