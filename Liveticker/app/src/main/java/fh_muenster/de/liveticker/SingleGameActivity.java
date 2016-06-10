package fh_muenster.de.liveticker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SingleGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);

        Intent user_infos = getIntent();
        String spiel = user_infos.getStringExtra("Spiel");

        if(spiel!=null && !spiel.equals(""))
        {
            TextView text = (TextView)findViewById(R.id.TEXT);
            text.setText("Willkommen bei Spiel "+spiel);
        }


    }
}
