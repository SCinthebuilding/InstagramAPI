import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    static final String CLIENTID = "916f9184b17b47ce8144a62b71c38d32";
    static final String REDIRECTURL = "http://localhost:8888/callback";

    public static void main(String[] args) {
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
