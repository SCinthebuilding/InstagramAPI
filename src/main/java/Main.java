
import utils.Authentication;

import java.util.Scanner;

public class Main {

    public static Authentication authentication;

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

        authentication = new Authentication(clientId, clientSecret);
        try {
            String artistId = authentication.returnArtistId(artistName);
            subMenu(artistId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("=================================================================");
    }

    public static void subMenu(String artistId) {
        int choice;
        String albumName;
        Scanner scan = new Scanner(System.in);
        menuItems();
        while (scan.hasNextInt()) {
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Top Tracks of your Artist");
                    authentication.getArtistsTopTracks(artistId);
                    break;
                case 2:
                    System.out.println("List of the Albums of your Artist");
                    authentication.getArtistsAlbums(artistId);
                    break;
                case 3:
                    System.out.print("please enter your specific album : ");
                    // albumName = scan.nextLine();
                    //TODO : call function
                    break;
                case 4:
                    return;
                default:
                    System.out.print("Your input is wrong.Please enter a number between 1-4");
                    break;
            }
            menuItems();
        }
    }

    public static void menuItems() {
        System.out.println("\nPlease choose one of the items : ");
        System.out.println(". enter 1 to get 10 top tracks of your artist ");
        System.out.println(". enter 2 to get albums of your artist ");
        System.out.println(". enter 3 to get tracks of specific album of your artist ");
        System.out.println(". enter 4 to exit ");
    }
}
