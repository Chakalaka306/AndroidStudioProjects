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

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        if(user_name!=null && !user_name.equals(""))
        {
            TextView txt = (TextView)findViewById(R.id.wilkommens_seite);
            txt.setText("Willkommen bei der Liveticker app, "+user_name+". Das Menü finden Sie auf der oberen linken Seite. Happy Ticking!");
        }



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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(NavigationActivity.this,AllGamesActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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
