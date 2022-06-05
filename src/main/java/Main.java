import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import utils.Authentication;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String clientId, clientSecret;

        Scanner scan = new Scanner(System.in);
        System.out.println("=================================================================");
        System.out.print("Please enter your ClientId : ");
        clientId = scan.nextLine();
        System.out.print("Please enter your ClientSecret : ");
        clientSecret= scan.nextLine();
        System.out.println("=================================================================");
        Authentication authentication = new Authentication();
        // Create a request object with the optional parameter "market"
        GetArtistsTopTracksRequest request = authentication.authenticate(clientId, clientSecret);

        try {
            // Execute the request synchronous
            final Track[] tracks = request.execute();

            for (Track track : tracks) {
                System.out.println(track.getName());
            }

        } catch (Exception e) {
            System.out.println("Something went wrong!\n" + e.getMessage());
        }

        // get number of Albums by artist
        final GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums("2DaxqgrOhkeH0fpeiQq2f4")
//          .album_type("album")
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
                .build();

            try {
                final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();

                System.out.println("Total: " + albumSimplifiedPaging.getTotal());
            } catch (IOException | SpotifyWebApiException | ParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
