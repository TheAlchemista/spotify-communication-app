package requests;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import com.wrapper.spotify.model_objects.specification.User;

// Exceptions
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;

// Local imports
import data.DataWork;


public class Requests {

    private final DataWork dataWork = new DataWork();

    public SpotifyApi createSpotifyApi() {
        SpotifyApi spotifyApi = new SpotifyApi
        .Builder()
        .setClientId(this.dataWork.getClienId())
        .setClientSecret(this.dataWork.getClientSecret())
        .setRedirectUri(this.dataWork.getRedirectURL())
        .build();

        return spotifyApi;
    }

    public User getCurrentUserProfile(SpotifyApi spotifyApi) {
        User user = null;
        try {
            GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
            user = getCurrentUsersProfileRequest.execute();
            return user;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return user;
        }
    }
}

