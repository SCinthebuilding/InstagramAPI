package utils;

import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Authentication {
    SpotifyApi spotifyApi = null;

    public void buildSpotify(String clientId, String clientSecret) {
        try {
            spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .build();
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                    .build();
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());


        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String returnArtistId(String clientId, String clientSecret, String artistName) throws IOException, ParseException, SpotifyWebApiException {
        buildSpotify(clientId,clientSecret);
        SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(artistName)
                .market(CountryCode.SE)
                .limit(10)
                .build();

        Paging<Artist> artistPaging = searchArtistsRequest.execute();
        List<Artist> artists = Arrays.asList(artistPaging.getItems());
        return !artists.isEmpty() ? artists.get(0).getId() : "Not found";
    }
}