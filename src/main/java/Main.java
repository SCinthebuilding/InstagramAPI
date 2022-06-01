import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    static final String CLIENTID = "916f9184b17b47ce8144a62b71c38d32";
    static final String REDIRECTURL = "http://localhost:8888/callback";

    public static void main(String[] args) {


        // For all requests an access token is needed
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
                .build();

        // Create a request object with the optional parameter "market"
        final GetArtistsTopTracksRequest getSomethingRequest = spotifyApi.getArtistsTopTracks("2DaxqgrOhkeH0fpeiQq2f4", CountryCode.DE)
                .build();

        void getSomething_Sync() {
            try {
                // Execute the request synchronous
                final Something something = getSomethingRequest.execute();

                // Print something's name
                System.out.println("Name: " + something.getName());
            } catch (Exception e) {
                System.out.println("Something went wrong!\n" + e.getMessage());
            }
        }

        try {



            String urlAuth = "https://accounts.spotify.com/authorize?"
                    + "client_id=" + CLIENTID + "&"
                    + "response_type=code&"
                    + "redirect_uri=" + REDIRECTURL + "&"
                    + "scope=user-read-private%20user-read-email&"
                    + "state=34fFs29kd09";
            System.out.println(urlAuth);

            URL url = new URL(urlAuth);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
            }
            System.out.println("Output from Server .... \n");

            con.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
