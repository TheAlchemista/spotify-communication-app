package data;

import com.wrapper.spotify.SpotifyHttpManager;

import java.net.URI;

public class DataWork {
    public URI getRedirectURL() {
        return SpotifyHttpManager.makeUri("https://www.google.pl/");
    }

    public String getClienId() {
        return "ffd9b33a5db5469f82df1e9a4ac09ea3";
    }

    public String getClientSecret() {
        return "1f9a60efbb66486c983b0b56a597ab3f";
    }
}
