package spotify_api_interaction;


// SpotifyApi imports
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
    // Skipping
import com.wrapper.spotify.requests.data.player.SkipUsersPlaybackToNextTrackRequest;
import com.wrapper.spotify.requests.data.player.SkipUsersPlaybackToPreviousTrackRequest;
    // Pausing
import com.wrapper.spotify.requests.data.player.PauseUsersPlaybackRequest;

// Exceptions
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;


public class SpotifyApiInteraction {

    // Represents the playback state
    private Boolean playing;

    public SpotifyApiInteraction() {
        this.playing = false;
    }

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

    public void pauseUsersPlayback(SpotifyApi spotifyApi) {
        try {
            final PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback().build();
            final String string = pauseUsersPlaybackRequest.execute();

            System.out.println("Paused: " + string);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void changePlayingState() {
        if (playing) playing = false;
        else playing = true;
    }

    // Returns true if playback is started and false if playback is stopped.
    public Boolean getPlaybackState() {
        return playing;
    }

    public void exit() {
        System.exit(-1);
    }
}
