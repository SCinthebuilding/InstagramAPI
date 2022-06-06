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

    public static void main(String[] args){
        menu();
    }

    public static void menu(){
        String clientId, clientSecret,artistName;

        Scanner scan = new Scanner(System.in);
        System.out.println("=================================================================");
        System.out.print("Please enter your ClientId : ");
        clientId = scan.nextLine();
        System.out.print("Please enter your ClientSecret : ");
        clientSecret = scan.nextLine();
        System.out.print("Please enter your Artist's name : ");
        artistName = scan.nextLine();
        System.out.println("=================================================================");
        Authentication authentication = new Authentication();
        try{
            System.out.printf("Artist's Id : %s ",authentication.returnArtistId(clientId,clientSecret,artistName));
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
