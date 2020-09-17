package spotify_api_interaction;

// SpotifyApi imports
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.player.SkipUsersPlaybackToNextTrackRequest;
import com.wrapper.spotify.requests.data.player.SkipUsersPlaybackToPreviousTrackRequest;

// Exceptions
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;


public class SpotifyApiInteraction {

    public SpotifyApiInteraction() {}

    public void startResumeUsersPlayback(SpotifyApi spotifyApi) {
        try {
            final StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback().build();

            final String string = startResumeUsersPlaybackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void skipUsersPlaybackToNextTrack(SpotifyApi spotifyApi) {
        try {
            final SkipUsersPlaybackToNextTrackRequest skipUsersPlaybackToNextTrackRequest = spotifyApi.skipUsersPlaybackToNextTrack().build();
            final String string = skipUsersPlaybackToNextTrackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void skipUsersPlaybackToPreviousTrack(SpotifyApi spotifyApi) {
        try {
            final SkipUsersPlaybackToPreviousTrackRequest skipUsersPlaybackToPreviousTrackRequest = spotifyApi.skipUsersPlaybackToPreviousTrack().build();
            final String string = skipUsersPlaybackToPreviousTrackRequest.execute();

            System.out.println("Null: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void exit() {
        System.exit(-1);
    }
    
}
