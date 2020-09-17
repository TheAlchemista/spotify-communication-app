package app;

// Api imports
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import authorization.client_credentials.GettingAccessToken;

// Java exceptions
import java.io.IOException;

// Internal imports
import requests.Requests;
import serialization.Serialization;
import key_listener.DetectKeys;
import key_listener.DetectKeysRunMethods;

public final class App {

    public static void main(String[] args) {

        Run run = new Run();

        run.tokensAcquisition();
        run.setTokens();
        run.run();
    }
}

class Run {
    private Requests requests;
    private SpotifyApi spotifyApi;
    private DetectKeysRunMethods detectKeysRunMethods;
    private DetectKeys detectKeys;
    private Serialization serialization;

    public Run() {
        this.requests = new Requests();
        this.spotifyApi = requests.createSpotifyApi();
        this.detectKeysRunMethods = new DetectKeysRunMethods(spotifyApi);
        this.detectKeys = new DetectKeys(detectKeysRunMethods);
        this.serialization = new Serialization();
    }
    
    public void run() {
        detectKeys.run(detectKeysRunMethods);
    }

    public void tokensAcquisition() {
        if (!serialization.checkIfFileIsEmpty()) {
            serialization.readFromFile();
        } else {
            GettingAccessToken gettingAccessToken = new GettingAccessToken();

            AuthorizationCodeCredentials authorizationCodeCredentials = gettingAccessToken.getAuthorizationCodeCredentialsWithUser(spotifyApi);

            try {
                serialization.addElement("accessToken", authorizationCodeCredentials.getAccessToken());
                serialization.addElement("refreshToken", authorizationCodeCredentials.getRefreshToken());
            } catch (IOException e) { System.out.println(e.getMessage()); }

            serialization.saveToFile();
        }
    }

    public void setTokens() {
        spotifyApi.setAccessToken(serialization.getAccessToken());
        spotifyApi.setRefreshToken(serialization.getRefreshToken());
    }
}
