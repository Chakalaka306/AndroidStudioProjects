package fh_muenster.de.liveticker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FavoritActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);


        Spinner liga = (Spinner)findViewById(R.id.Liga_spinner);
        ArrayAdapter<CharSequence> adapter2 =
                ArrayAdapter.createFromResource(
                        this, R.array.uebersicht_ligen,
                        android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        liga.setAdapter(adapter2);

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
