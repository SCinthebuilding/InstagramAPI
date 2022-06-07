package utils;

import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumsTracksRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Authentication {
   private SpotifyApi spotifyApi = null;

    public Authentication(String clientId, String clientSecret) {
        buildSpotify(clientId,clientSecret);
    }

    public void buildSpotify(String clientId, String clientSecret) {
        try {
            spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .build();
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                    .build();
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String returnArtistId(String artistName) throws IOException, ParseException, SpotifyWebApiException {
        SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(artistName)
                .market(CountryCode.DE)
                .limit(10)
                .build();

        Paging<Artist> artistPaging = searchArtistsRequest.execute();
        List<Artist> artists = Arrays.asList(artistPaging.getItems());
        return !artists.isEmpty() ? artists.get(0).getId() : "Not found";
    }

    public void getArtistsTopTracks( String artistId){
        GetArtistsTopTracksRequest request = spotifyApi.getArtistsTopTracks(
                artistId, CountryCode.DE).build();

        try {
            // Execute the request synchronous
            final Track[] tracks = request.execute();
            for (Track track : tracks) {
                System.out.println(track.getName());
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
    }

    public void getArtistsAlbums(String artistId){
        GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(artistId)
                .market(CountryCode.DE)
                .build();

        try {
            final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();
            AlbumSimplified[] albums = albumSimplifiedPaging.getItems();
            for( AlbumSimplified album : albums) {
                System.out.println(album.getName());
            }
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAlbumsTracks(String albumId)
    {
       GetAlbumsTracksRequest albumsTracksRequest =  spotifyApi.getAlbumsTracks(albumId).
               market(CountryCode.DE).build();

        try {
            final Paging<TrackSimplified> tracks = albumsTracksRequest.execute();
            for (TrackSimplified track : tracks.getItems()) {
                System.out.println(track.getName());
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }
    }
}