package fh_muenster.de.liveticker;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;

import java.util.List;


/**
 * The type Settings activity.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {


    /**
     * @author Andreas Blass , Kevin Gorter
     * A preference value change listener that updates the preference's summary
     * to reflect its new value
    /**

     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            /**
              Überprüfung ob die Einträge in die preference korrekt sind
             */
            if (preference instanceof ListPreference) {

                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                /**
                 * Setzen einer "Zusammenfassung" um die Variable zu reflektieren
                 */

                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);


                /**
                * Ansonsten wenn die ringtone preference vorhanden ist , überprüfe ob es sich um die richtige Variable handelt
                 */
            } else if (preference instanceof RingtonePreference) {


                  /**
                   *Wenn es keine Variable gibt , führe keinen Rington durch
                   */
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));


                    /**
                    * Setze die Summary auf Null wenn es einen Error gab
                     */
                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    }

                    /**
                    * Setze der neuen Zusammenfassung für den  ringtone display
                     */
                    else {



                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            }
            /**
            * Für alle anderen preferences , setze eine Zusammenfassung der Variablen
            * in einfacher String Ausgabe
             */
            else {

                preference.setSummary(stringValue);
            }
            return true;
        }
    };



       /**
       * Methode für die Überprüfung , ob es sich um einen extra langen Screen handelt

        */

    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }


    private static void bindPreferenceSummaryToValue(Preference preference) {

        /**
        Setzen der Liste um Änderungen an der Variable zu sehen
         */
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);


        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    /**
    * Methode die eine Instanz erzeugt
    * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }



    /**
    *  Methode die eine ActionBar setzt
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();

        /**
        *Überprüfung ob, eine ActionBar erzeugt wurde
        * Wenn ja , dann zeig den Button in der action bar
         */
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }




    /**
    * Laden der Headers aus den Ressourcen
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }



    /**
    * Diese Methode stoppt Fragmentierung injection in böswilligen application
    * Sicherstellung , dass keine unbekannten fragments in die Methode eingeschleußt wird
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * author : Andreas Blass , Kevin Gorter
     * Dieses Fragment zeigt die generellen preferences only
     * Diese wird genutzt wenn die activity eine two-pane settings UI zeigt
     */


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);


            /**
            * Bindung der Texten , Listen,Dialogen, Ringtone preferences zu den jeweiligen Variablen
            *
             */
            bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference("example_list"));
        }


        /**
        * Methode die dem ItemId eine ID zuordnet
         */
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * author : Andreas Blass , Kevin Gorter
     * Dieses Fragment zeigt die generellen preferences only
     * Diese wird genutzt wenn die activity eine two-pane settings UI zeigt
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);


            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Dieses Fragment zeigt die generellen preferences only
     * Diese wird genutzt wenn die activity eine two-pane settings UI zeigt
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);


            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        /**
        *@author Andreas Blass , Kevin Gorter
        *@param item
        *
         */
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
