package IPAA;

import java.awt.Desktop;
import java.net.URI;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPDetails ipDetails = new IPDetails();

        System.out.print("Enter an IP address: ");
        String ipAddress = scanner.nextLine();

        // Get and display IP details
        String details = ipDetails.getIPDetails(ipAddress);
        System.out.println(details);

        // Extract latitude and longitude from the details
        String[] lines = details.split("\n");
        String latitude = null;
        String longitude = null;

        for (String line : lines) {
            if (line.startsWith("Latitude:")) {
                latitude = line.split(": ")[1];
            } else if (line.startsWith("Longitude:")) {
                longitude = line.split(": ")[1];
            }
        }

        // If latitude and longitude were found, open them in Google Maps
        if (latitude != null && longitude != null) {
            String googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
            try {
                Desktop.getDesktop().browse(new URI(googleMapsUrl));
                System.out.println("Opening Google Maps with the coordinates...");
            } catch (Exception e) {
                System.out.println("Error opening Google Maps: " + e.getMessage());
            }
        } else {
            System.out.println("Could not extract latitude and longitude.");
        }
    }
}
