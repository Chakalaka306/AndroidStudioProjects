package fh_muenster.de.liveticker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Andreas Blass , Kevin Gorter
 * @version 1.0
 */

/**
 * Erstellen einer Methode NavigationActivity
 */
public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     *
     * @author Andreas Blass, Kevin Gorter
     * @param savedInstanceState
     *  Aufruf der activity_navigation
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Datenbankspeicher in der App ?!
        SharedPreferences shared = getSharedPreferences("TEST", Context.MODE_PRIVATE);
        shared.getString("User","");*/

        Intent user_infos = getIntent();
        String user_name = user_infos.getStringExtra("User");

        /**
        *Überprüfung ob ein User vorhanden ist
        * Ist ein User vorhanden dann wird er als User angezeigt und bekommt ein Willkommenstext
         */
        if(user_name!=null && !user_name.equals(""))
        {
            TextView txt = (TextView)findViewById(R.id.wilkommens_seite);
            txt.setText("Willkommen bei der Liveticker app, "+user_name+". Das Menü finden Sie auf der oberen linken Seite. Happy Ticking!");
        }


        /**
         * DrawerLayout drawer wird erstellt
         * Es ist möglich ihn zu öffnen und zu schließen
         */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * Einfügung in das Menü , Elemente werden dort eingefügt in die Aktionsleiste wenn diese vorhanden ist
         */
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * Action bar ist verfügbar
         * die Action bar aktuallisiert sich automatisch per Button
         * solange die spezifizíerten parent activitys in der AndroidManifest.xml vorhanden sind
         */
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(NavigationActivity.this,AllGamesActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
    * Methode die die Navigation händelt zwischen den Activitys
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_alle_spiele) {
            Intent i = new Intent(NavigationActivity.this,AllGamesActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_alle_mannschaften) {
            Intent i = new Intent(NavigationActivity.this,TeamActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_favoriten) {
            Intent i = new Intent(NavigationActivity.this,FavoritActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_impressum) {
            Intent i = new Intent(NavigationActivity.this,ImprintActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_donate) {
            Intent i = new Intent(NavigationActivity.this,DonationActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
// Hier die Methode für das Ändern der Hintergrundfarbe des MenuHeaders implementieren
     /* public void onStart(){
        super.onStart();
        Log.d("Test","Dies ist ein Test für das Ändern der Hintergrundfarbe");

        ImageView backgroundImage = (ImageView) findViewById(R.id.imageView);
        backgroundImage.setBackgroundColor(Color.rgb(135,206,235));


    }*/
}
