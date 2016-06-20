package fh_muenster.de.liveticker;

/**
 * Created by Kevin on 19.06.2016.
 *
 * @author Kevin Gorter, Andreas Blass
 * @version 1.0
 */
public class LiveTickerOnlineIntegration implements OnlineIntegrationInterface {
/*
    private LivetickerOnlineIntegrationImplementer livetickerWebservice;
    private int webserviceSessionId;
*/

    /**
     * standard Konstruktor
     */
    /*
    public LivetickerOnlineIntegrationImplementer() {
        this.livetickerWebservice = new LivetickerOnlineIntegration();

    }
    */

    /**
     *
     *überschreiben der Login Methode des Servers für den Webservice.
     *Wirft InvalidLoginException bei falschen Login-Werten.
     * Gibt den Userlogin zurück.
     *
     **/

    /**
     * @param username Nutzername des einzuloggenden Nutzers.
     * @param password Passwort des einzuloggenden Nutzers.
     * @return gibt erfolgreichen Login zurück, wenn dies auch so war.
     * @throws InvalidLoginException
     */
    /*
    @Override
    public UserLoginResponse login(String username, String password) throws InvalidLoginException {

        userLoginResponse response = this.livetickerWebservice.login(username, password);
        if (response.returnCodeField != 0)
            throw new InvalidLoginException("Ihr Login ist leider fehlgeschlagen. Bitte versuchen Sie es erneut.");
        this.webserviceSessionId = response.webserviceSessionId;
        return new UserLoginResponse(response.UserLoginResponse.userName, response.UserLoginResponse.password);


    }
    */
    /**
     * @param sessionId id des momentan eingeloggten Nutzers.
     * @return gibt den erfolgreichen Logout zurück.
     */
/*
    @Override
    public ReturncodeResponse logout(int sessionId) {

        dao.closeSession(sessionId);
        ReturncodeResponse response = new ReturncodeResponse();
        return response;
    }
*/
    /**
     * @param userName Benutzername des neu anzulegenden Nutzers
     * @param email    Email des zu registrierenden Nutzers.
     * @param password Passwort des zu registrierenden Nutzers.
     * @return Gibt zurück ob die Registrierung erfolgreich verlief.
     * @throws LiveTickerException
     */
/*
    @Override
    public User registerNewUser(String userName, String email, String password) throws LiveTickerException {
        userLoginResponse response = this.livetickerWebservice.registerNewUser(username, email, password);
        this.webserviceSessionId = response.webserviceSessionId;
        return new User(response.User.userName, response.User.email, response.User.password);
    }
*/
    /**
     * @param sessionId Die ID der Session des derzeitigen Nutzers.
     * @param team1     Mannschaft Nummer 1 des Spiels.
     * @param team2     Mannschaft Nummer 1 des Spiels.
     * @param aDate     Datum des Spielbeginns.
     * @return einen repsonse code ob das spiel angelegt wurde.
     * @throws LiveTickerException
     */
/*
    @Override
    public Game createNewGame(int sessionId, Team team1, Team team2, Date aDate) throws LiveTickerException {
        AddNewGameResponse response = this.livetickerWebservice.createNewGame(this.sessionId, team1, team2, aDate);
        if (response.returnCodeField != 0)
            throw new LiveTickerException("Spiel konnte nicht erstellt werden.");
        return new Game(response.Game.team1, response.Game.team2, response.Game.aDate);
    }
*/
    /**
     * @param sessionId id des users
     * @param gameId    id des Spiels
     * @param art       des event wie gelbe/rote karte oder spielerwechsel
     * @param team      welches team ein event ausgeführt hat
     * @param reason    evtl beschreibung zum event
     * @param min       in welcher spielminute ist dieses event eingetreten
     * @return gibt eine Response auf das Event mit den erstellten Eingaben
     * @throws LiveTickerException
     */
/*
    @Override
    public Event createNewEvent(int sessionId, int gameId, int art, int team, String reason, int min) throws LiveTickerException {
        AddNewEventResponse response = this.livetickerWebservice.createNewEvent(this.sessionId, gameId, art, team, reason, min);
        if (response.returnCodeField != 0)
            throw new LiveTickerException("Event konnte nicht erstellt werden.");
        return new Event(response.Event.gameId, response.Event.art, response.Event.team, response.Event.reason, response.Event.min);
    }
*/
    /**
     * @param sessionId id des users
     * @param newTeamId id der Mannschaft
     * @return gibt zurück, ob der Favorit erfolgreich angelegt wurde.
     * @throws LiveTickerException
     */
/*
    @Override
    public ReturncodeResponse addNewFavorite(int sessionId, int newTeamId) throws LiveTickerException {
        ReturnCodeResponse response = this.livetickerWebservice.addNewFavorite(this.sessionId, newTeamId);
        if (response.returnCodeField != 0)
            throw new LiveTickerException("Favorit konnte nicht hinzugefügt werden.");
        return new ReturnCodeResponse(response.Team.newTeamId);
    }
*/

    /**
     * @param sessionId id des users
     * @param newTeamId id der Mannschaft
     * @return gibt zurück, ob der Favorit erfolgreich entfernt wurde.
     * @throws LiveTickerException
     */
/*
    @Override
    public ReturncodeResponse deleteNewFavorite(int sessionId, int newTeamId) throws LiveTickerException {
        dao.deleteNewFavorite(this.sessionId, newTeamId);
        ReturncodeResponse response = new ReturncodeResponse();
        return response;
    }
*/
    /**
     * @param sessionId id des users
     * @return gibt zurück, ob ein Favorit angezeigt wird. Ausgabe des Favoriten erfolgt im Log.
     * @throws LiveTickerException
     */
/*
    @Override
    public AddNewFavoriteResponse displayMyFavorite(int sessionId) throws LiveTickerException {
        AddNewFavoriteResponse response = this.livetickerWebservice.displayMyFavorite(this.webserviceSessionId);
        return response;
    }
*/

    /**
     *
     * @param liga wird dargestellt.
     * @return Response code response mit der Liste der Teams
     * @throws LiveTickerException Teams konnten nicht gefunden werden.
     */
/*
    @Override
    public ReturncodeResponse displayLiga(String liga) throws LiveTickerException {
        ReturncodeResponse response = this.livetickerWebservice.displayLiga(this.liga);
        return response;
    }
    */
}

