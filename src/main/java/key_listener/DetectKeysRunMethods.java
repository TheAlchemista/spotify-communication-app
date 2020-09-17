package key_listener;


// SpotifyApi imports
import com.wrapper.spotify.SpotifyApi;

// Java imports
import java.util.Map;
import java.util.HashMap;

// KeyLogger imports
import org.jnativehook.keyboard.NativeKeyEvent;

// Local imports
import spotify_api_interaction.SpotifyApiInteraction;


public class DetectKeysRunMethods {

    private Map<String, Integer> mapOfWantedKeys;
    private SpotifyApiInteraction spotifyApiInteraction;
    private SpotifyApi spotifyApi;

    public DetectKeysRunMethods(SpotifyApi spotifyApi) {
        mapOfWantedKeys = new HashMap<String, Integer>();
        mapOfWantedKeys.put("Numpad&5", 57420);
        mapOfWantedKeys.put("Numpad&6", 57421);
        mapOfWantedKeys.put("Numpad&4", 57419);
        mapOfWantedKeys.put("exit", 3658);
        spotifyApiInteraction = new SpotifyApiInteraction();
        this.spotifyApi = spotifyApi;
    }

    public boolean checkWantedKeysList(NativeKeyEvent event) {
        return mapOfWantedKeys.containsValue(event.getKeyCode());
    }

    public void chooseAndRun(NativeKeyEvent event) {
        // Checking what key was pressed
        System.out.println("Key pressed: " + event.getKeyCode() + " '" + event.getKeyText(event.getKeyCode()) + "'");

        if (checkWantedKeysList(event)) {
            if (event.getKeyCode() == mapOfWantedKeys.get("Numpad&5"))
                {
                    Boolean playing = spotifyApiInteraction.getPlaybackState();
                    if (playing != null && playing)
                    {
                        System.out.println("Pause");
                        spotifyApiInteraction.pauseUsersPlayback(spotifyApi);
                    }
                    else if (playing != null && !playing)
                    {
                        System.out.println("Resume");
                        spotifyApiInteraction.startResumeUsersPlayback(spotifyApi);
                    }
                    spotifyApiInteraction.changePlayingState();
                }
            else if ( event.getKeyCode() == mapOfWantedKeys.get("exit"))
                spotifyApiInteraction.exit();
            else if (event.getKeyCode() == mapOfWantedKeys.get("Numpad&6"))
                spotifyApiInteraction.skipUsersPlaybackToNextTrack(spotifyApi);
            else if (event.getKeyCode() == mapOfWantedKeys.get("Numpad&4"))
                spotifyApiInteraction.skipUsersPlaybackToPreviousTrack(spotifyApi);
        }
    }
    
}
