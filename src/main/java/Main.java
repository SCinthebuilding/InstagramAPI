import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import utils.Authentication;

import java.io.IOException;

public class Main
{
    private static final String clientId = "94733260eeea48598a43f8a49780a7b8";
    private static final String clientSecret = "33b6015ce56448fab6e5a8675b1054fe";

    public static void main(String[] args) {


        Authentication authentication = new Authentication();

        // Create a request object with the optional parameter "market"
        final GetArtistsTopTracksRequest request =  authentication.authenticate(clientId,clientSecret);

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
