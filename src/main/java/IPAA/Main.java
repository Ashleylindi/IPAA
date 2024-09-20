package IPAA;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class Main {

    public static void main(String[] args) {
        // Initialize Javalin server
        Javalin app = Javalin.create().start(7000);

        // Serve the form page at the root
        app.get("/", ctx -> {
            ctx.html("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>IP Details Form</title>
                </head>
                <body>
                    <h1>Enter an IP Address</h1>
                    <form action="/get-details" method="post">
                        <label for="ip">IP Address:</label>
                        <input type="text" id="ip" name="ipAddress" required>
                        <input type="submit" value="Get IP Details">
                    </form>
                </body>
                </html>
            """);
        });

        // Handle POST requests from the form
        app.post("/get-details", handleIPDetails);
    }

    // Define handler to process IP address and return details
    public static Handler handleIPDetails = ctx -> {
        String ipAddress = ctx.formParam("ipAddress");

        // Process IP address using IPDetails class
        IPDetails ipDetails = new IPDetails();
        String details = ipDetails.getIPDetails(ipAddress);

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

        // Generate HTML response
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<html><body>");
        htmlResponse.append("<h1>IP Details for: ").append(ipAddress).append("</h1>");
        htmlResponse.append("<pre>").append(details).append("</pre>");

        // If latitude and longitude were found, show a link to Google Maps
        if (latitude != null && longitude != null) {
            String googleMapsUrl = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
            htmlResponse.append("<p><a href=\"").append(googleMapsUrl).append("\" target=\"_blank\">Open in Google Maps</a></p>");
        } else {
            htmlResponse.append("<p>Could not extract latitude and longitude.</p>");
        }

        htmlResponse.append("</body></html>");

        // Send the HTML response
        ctx.html(htmlResponse.toString());
    };
}
