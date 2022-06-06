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

    public static Authentication authentication = new Authentication();
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        String clientId, clientSecret, artistName;

        Scanner scan = new Scanner(System.in);
        System.out.println("========== Welcome to search Artist's Songs and Albums ==========");
        System.out.print("Please enter your ClientId : ");
        clientId = scan.nextLine();
        System.out.print("Please enter your ClientSecret : ");
        clientSecret = scan.nextLine();
        System.out.print("Please enter your Artist's name : ");
        artistName = scan.nextLine();


        authentication.buildSpotify(clientId, clientSecret);
        try {
            String artistId = authentication.returnArtistId(artistName);
            System.out.printf("Artist's Id : %s \n", artistId);
            subMenu(artistId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("=================================================================");
    }

    public static void subMenu(String artistId) {
        int choice;
        Scanner scan = new Scanner(System.in);
        System.out.print("enter 1 to get 10 top tracks of your artist OR enter 2 to get albums of your artist : ");
        choice = scan.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.print("Please enter 1 or 2 : ");
            choice = scan.nextInt();
        }

        switch (choice) {
            case 1:
                System.out.println("Top Tracks of your Artist");
                authentication.getArtistsTopTracks(artistId);
            case 2:
                System.out.println("List of the Albums of your Artist");
               authentication.getArtistsAlbums(artistId);
        }
    }
}
