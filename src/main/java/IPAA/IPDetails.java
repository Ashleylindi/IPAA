package IPAA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class IPDetails {

    public String getIPDetails(String ipAddress) {
        String apiUrl = "http://ipinfo.io/" + ipAddress + "/json";
        StringBuilder response = new StringBuilder();

        try {
            // Send HTTP GET request to the API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON response
            JSONObject jsonObj = new JSONObject(response.toString());

            // Extract details
            String city = jsonObj.getString("city");
            String region = jsonObj.getString("region");
            String country = jsonObj.getString("country");
            String postal = jsonObj.optString("postal", "N/A");
            String loc = jsonObj.getString("loc");  // Get the 'loc' field for lat/lon

            // Split 'loc' into latitude and longitude
            String[] coordinates = loc.split(",");
            String latitude = coordinates[0];
            String longitude = coordinates[1];

            // Build the result string
            return String.format("IP Address: %s\nCity: %s\nRegion: %s\nCountry: %s\nPostal Code: %s\nLatitude: %s\nLongitude: %s",
                    ipAddress, city, region, country, postal, latitude, longitude);

        } catch (Exception e) {
            return "Error retrieving IP details: " + e.getMessage();
        }
    }
}
