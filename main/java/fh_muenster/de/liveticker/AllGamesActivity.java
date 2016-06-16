package fh_muenster.de.liveticker;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class AllGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_games);

        SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        final LinearLayout spiel_eins = (LinearLayout)findViewById(R.id.spiel_eins);
        final LinearLayout spiel_zwei = (LinearLayout)findViewById(R.id.spiel_zwei);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        System.out.println("PULL TO REFRESH USED");

                        progressBar.setVisibility(View.VISIBLE);

                        //TODO ASYNCTASK zum abrufen neuer Daten vom Server: PostDelayed später entfernen
                        progressBar.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                spiel_eins.setVisibility(View.VISIBLE);
                                spiel_zwei.setVisibility(View.VISIBLE);
                            }
                        },2000);
                    }
                }
        );


        spiel_eins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllGamesActivity.this,SingleGameActivity.class);
                i.putExtra("Spiel","1");
                startActivity(i);
            }
        });

        spiel_zwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllGamesActivity.this,SingleGameActivity.class);
                i.putExtra("Spiel","2");
                startActivity(i);
            }
        });
    }
}
