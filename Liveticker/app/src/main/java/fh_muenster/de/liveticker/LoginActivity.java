package fh_muenster.de.liveticker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import fh_muenster.de.liveticker.async.async_task_master;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;


/**
 * @author Andreas Blass , Kevin Gorter
 * @version 1.0
 */

/**
 * Eine Loginactivity die dem User eine Anmeldung per Benutzername und Passwort anbietet
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    /**
    Eine Id wird angelegt um die Identität der READ_CONTACTS erlaubnis anzufragen
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /*
     * TODO: remove after connecting to a real authentication system.
     */

    /**
    Ein Dummy wird authentifiziert  und hat enthält in seinem Bereich das Wissen über Passwort und Benutzer
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    /*
    Verfolgt die Login-Aufgabe , um sicherzustellen das storniert werden kann, falls es gewünscht wird
     */

    private UserLoginTask mAuthTask = null;

    /**
    UI Referenzen
     */
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        /**
        * Auto- Implementierung der App Index API
         */
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    /**
     * On button click.
     *
     * @param v Entgegennahme der Eingabe in dem EditTextfeld "uname" und "pw" Eingaben werden überprüft Uname und pw werden in einen String [] gesteckt Aufruf einer Intent i
     */


    public void onButtonClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            EditText uname = (EditText) findViewById(R.id.TVusername);
            EditText pw = (EditText)findViewById(R.id.TVpassword);

            String str_uname = uname.getText().toString();
            String str_pw = pw.getText().toString();

            String[] params = new String[]{str_uname,str_pw};

            async_task_master async_inst = new async_task_master();
            async_inst.init(LoginActivity.this);
            async_inst.execute(params);
        }
        if (v.getId() == R.id.btnRegister) {
            Intent i = new Intent(LoginActivity.this,RegistryActivity.class);
            startActivity(i);
        }
        if(v.getId() == R.id.btnGuest){
            Intent i = new Intent(LoginActivity.this,NavigationActivity.class);
            startActivity(i);
        }
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }
    /**
    Überprüfung der SDK Version
     */
    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        /**
        Überprüfung der READ_CONTACTS Erlaubnis
         */
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }


    /**
     Man soll einen Rückruf erhalten , wenn eine Anforderung abgeschlossen wurde
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
    *Versuche dich einzuloggen oder zu registrieren in der spezifizierten login form
    *Wenn Fehler bei der Eingabe vorhanden sind dann gebe Fehlermeldungen aus
    * Zum Beispiel gebe aus "Das Passwort ist zu kurz" oder "Die E-Mail Adresse ist ungültig"
    * Die Fehlermeldungen werden angezeigt und der Login ist fehlgeschlagen
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        /**
        Zurücksetzen der Fehler
         */
        mEmailView.setError(null);
        mPasswordView.setError(null);


        /**
        Speicherung der Werte zum Zeitpunkt der Anmeldung
         */

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }


        /**
        Überprüfe ob eine  gültige E-Mail eingetragen wurde
         */

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        /**
        Überprüfe ob es sich um eine gültige E-Mail Adrese handelt
         */
        else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            /**
            * Es ist ein fehler aufgetreten und führe den Login nicht durch
             */
            focusView.requestFocus();
        } else {


            /**
            Zeige den Prozess Fortschritt an  und füge im Hintergrund einen Task aus und
            führe den user login versuch aus
             */
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    /**
    *@param email
    *Überprüfe ob ein "@" in der Eingabe ist
     */

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    /**
   *@param password
   *Überprüfe ob die Passwort länge >4 ist
    */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    /**
     * Honeycomb MR2 erlaubt Animation
     * Wenn es möglich ist ,dann nutze API's to fade-in den Progress spinner
     * @param show
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            /*
            *Die ViewPropertyAnimator APIs sind nicht erlaubt , also zeige diese an
            * und verstecke die relevanten UI Komponenten
             */
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,

                /**
                 * Abruf von Datenzeilen für das "Profil" Kontakt des Gerätebenutzer
                 */
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,



                /**
                 * wähle nur E-Mail adressen aus
                 */
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                /**
                 * Zeige vorzüglich email adressen an. Merke dass es nicht primär e-mail adressen sein müssen
                 * wenn keine angegeben worden sind
                 */
                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    /**
     * Erzeuge ein Array für E-Mails
     * Solange es E-Mail adressen gibt die noch nicht in dem Array stehen füge sie hinzu
     * @param cursorLoader
     * @param cursor
     */
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    /**
     * Erstellt einen Adapter der angibt was AutoCompleteTextView in seiner Liste enthält
     * @param emailAddressCollection
     */
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        /**
         * Auto-generated das implementiert die App Index API
         */
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,


                /**
                 * Gehe sicher das die WEB page URL is correct
                 * Ansonsten setze die URL auf null
                 */
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://fh_muenster.de.liveticker/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        /**
         * Auto-Generated um die APP Index des APIs zu imolementiert
         */

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,

                /**
                 * Gehe sicher das die WEB page URL is correct
                 * Ansonsten setze die URL auf null
                 */

                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://fh_muenster.de.liveticker/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private interface ProfileQuery {
        /**
         * The Projection.
         */
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        /**
         * The constant ADDRESS.
         */
        int ADDRESS = 0;
        /**
         * The constant IS_PRIMARY.
         */
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    /**
     * Präsentiert ein asynchrones login/registrierung task um die authentifizierung des Users zu gewähren
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        /**
         * Instantiates a new User login task.
         *
         * @param email    the email
         * @param password the password
         */
        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                /**
                 * Simuliert die Netzwerk Verbindung
                 */

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {

                    /**
                     * Return "true" wenn ein Account existiert und das passwort übereinstimmt
                     */

                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

