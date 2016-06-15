package fh_muenster.de.liveticker.async;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import fh_muenster.de.liveticker.NavigationActivity;

/**
 * Created by Kevin on 10.06.2016.
 */
public class async_task_master extends AsyncTask<String, Void, String> {

    String username = "";
    Context used_activity;

    public void init(Context ctx)
    {
        used_activity = ctx;
    }

    @Override
    protected String doInBackground(String[] params) {

        String response = "";

        try
        {
            username = params[0];
            //TODO methode des servers einfügen
            String urlParameters  = "username="+params[0]+"&passwort="+params[1]+"&methode=login";
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            //TODO hier webservice url einfügen yo
            String request        = "http://example.com/index.php";
            URL    url            = new URL( request );
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write( postData );


            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {

        /*
        String bernd = response.split(",");
        */

        //TODO Response vom Server prüfen
        //if(response==true){

            Intent i = new Intent(used_activity,NavigationActivity.class);
            i.putExtra("User",username);

        /*
            SharedPreferences shared = used_activity.getSharedPreferences("TEST",Context.MODE_PRIVATE);
            SharedPreferences.Editor shared_edit = shared.edit();

        shared_edit.putString("User",username);
        shared_edit.commit();*/

            used_activity.startActivity(i);

        //}else{
            //Toast.makeText(used_activity,"Error Login Failed. Check your Username and Password.",Toast.LENGTH_LONG).show();
        //}
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}
