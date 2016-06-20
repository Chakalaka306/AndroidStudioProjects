package fh_muenster.de.liveticker;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * The type App compat preference activity.
 */
public abstract class AppCompatPreferenceActivity extends PreferenceActivity {

    /**
     * @author Andreas Blass , Kevin Gorter
     * @version 1.0
     */


    private AppCompatDelegate mDelegate;

    /**
    *Initialisierung aller Fragmente und loaders
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    /**
     * Wird aufgerufen wenn die Aktivität  und inbetriebnahme abgeschlossen ist
     * nach onStart () und onRestoreInstanceState (Bundle) genannt worden
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

    /**
     * Support-Bibliothek Version von getActionBar ()
     *
     * @return the support action bar
     */
    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    /**
     * Festlegung einer Toolbar als Aktionsleiste für die Aktivitätsfesnter
     *
     * @param toolbar the toolbar
     */
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

    /**
     *Gibt ein MenüInflator zurück
     */
    @Override
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

    /**
     * Stellt den Aktivitäts content aus einer Layout Ressource
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

    /**
     * Stellt den Aktivitäts content auf einer expliziten Ansicht
     * @param view
     */
    @Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

    /**
     * Stellt den Aktivitäts content auf einer expliziten Ansicht
     * @param view
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }

    /**
     * Fügt einen zusätzlichen Inhalt im Hinblick der Aktivität hinzu
     * @param view
     * @param params
     */
    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }

    /**
     * Sendet onResume() zu Fragmenten
     */
    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    /**
     * Änderungen am Titel möglich
     * @param title
     * @param color
     */
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }

    /**
     * Dispatcher Konfigurationsänderung an alle Fragmente
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getDelegate().onConfigurationChanged(newConfig);
    }

    /**
    * Stoppt den Vorgang
     */
    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    /**
     * Löscht das Delegete
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    /**
     * Support-Bibliothek Version von invalidateOptionsMenu ()
     */
    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    /**
     * Wenn mDelegate 0 ist dann erstelle es und setze es auf null
     * @return mDelegate
     */
    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }
}
