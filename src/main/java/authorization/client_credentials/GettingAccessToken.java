package authorization.client_credentials;

// First method necessities
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

// User authorization necessities 
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import org.apache.hc.core5.http.ParseException;

// Selenium
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Java imports
import java.io.IOException;
import java.net.URI;
import java.lang.Boolean;


public class GettingAccessToken {

    private WebDriver driver;
    private String authorizationCode;

    public GettingAccessToken() {
        this.driver = new ChromeDriver();
        this.authorizationCode = "";
    }

    public String getAccessTokenWithoutUser(SpotifyApi spotifyApi) {
        try {
            // Creating the client credentials request object
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
            // Executing the request
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            // Returning the access token
            return clientCredentials.getAccessToken();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return "";
        }
    }

    public AuthorizationCodeCredentials getAuthorizationCodeCredentialsWithUser(SpotifyApi spotifyApi) {
        AuthorizationCodeCredentials authorizationCodeCredentials = null;
        try {

            authorizationCode = getAuthorizationCodeWithUser(spotifyApi);

            AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(authorizationCode).build();
            authorizationCodeCredentials = authorizationCodeRequest.execute();

            // System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn()); 

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return authorizationCodeCredentials;
    }

    private URI getAuthorizationURI(SpotifyApi spotifyApi) {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
        .scope("user-modify-playback-state,user-read-email,user-read-playback-state")
        .build();
        return authorizationCodeUriRequest.execute();
    }

    public String getRedirectURL(WebDriver driver) {

        Boolean unproperTitle = true;
        Boolean timeoutNotExceeded = true;
        String returnString = "";
        // In miliseconds
        int waitTime = 2000, totalTime = 0, timeoutTime = 30000;

        while (unproperTitle && timeoutNotExceeded) {
            String title = driver.getTitle();

            if (title.equals("Google")) {
                unproperTitle = false;
                return driver.getCurrentUrl();
            } else {
                try {
                    Thread.sleep(waitTime);
                    totalTime = totalTime + waitTime;
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }  
            }

            if (totalTime >= timeoutTime) {
                timeoutNotExceeded = false;
            }
        }
        return returnString;
    }

    private String extractAuthorizationCodeFromRedirectURL(String redirectURL) {
        String[] splitedString = redirectURL.split("code=");
        return splitedString[1];
    }

    public String getAuthorizationCodeWithUser(SpotifyApi spotifyApi) {
            URI uri = getAuthorizationURI(spotifyApi);

            this.driver.get(uri.toString());

            String redirectURL = getRedirectURL(driver);

            return extractAuthorizationCodeFromRedirectURL(redirectURL);
    }
    
}
