import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.IModelObject;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import java.util.Scanner;
import java.io.IOException;

public class Main
{
    private static final String clientId = "94733260eeea48598a43f8a49780a7b8";
    private static final String clientSecret = "33b6015ce56448fab6e5a8675b1054fe";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();


    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    public static void authenticate() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void main(String[] args) {

        authenticate();

        Scanner sc = new Scanner(System.in);
        System.out.println("Type the name of an artist");
        String artistName = sc.nextLine();

        final SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(artistName)
                .market(CountryCode.DE)
//          .limit(10)
//          .offset(0)
//          .includeExternal("audio")
                .build();

        String artistId = null;
        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();
            Artist[] listOfArtists = artistPaging.getItems();
            artistId = listOfArtists[0].getId();
            System.out.println("ID of your artist: " + artistId);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: There is not Artist named like" + e.getMessage());
        }

        // get artist 10 top tracks
        final GetArtistsTopTracksRequest request = spotifyApi.getArtistsTopTracks(
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

    }
