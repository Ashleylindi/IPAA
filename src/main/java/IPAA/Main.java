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
                <style>
                    /* Basic Reset */
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                        font-family: Arial, sans-serif;
                    }
            
                    /* Center the container */
                    body {
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        background-color: #f4f4f4;
                    }
            
                    /* Styling the form container */
                    .form-container {
                        background-color: grey;
                        padding: 100px 500px; /* Wider padding for larger box */
                        border-radius: 0px;
                        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
                        width: 100%;
                        text-align: center;
                    }
            
                    /* Inner form alignment */
                    .form-content {
                        max-width: 400px;
                        margin: 0 auto;
                        text-align: left;
                    }
            
                    /* Heading style */
                    .form-content h1 {
                        text-align: center;
                        margin-bottom: 20px;
                        font-size: 24px;
                        color: #333;
                    }
            
                    /* Label and input styling */
                    label {
                        display: block;
                        margin-bottom: 8px;
                        font-weight: bold;
                        color: #555;
                        font-size: 14px;
                    }
            
                    input[type="text"] {
                        width: 100%;
                        padding: 10px;
                        margin-bottom: 20px;
                        border: 1px solid #ddd;
                        border-radius: 4px;
                        font-size: 16px;
                    }
            
                    /* Submit button styling */
                    input[type="submit"] {
                        background-color: #007bff;
                        color: white;
                        border: none;
                        padding: 10px 15px;
                        font-size: 16px;
                        border-radius: 4px;
                        cursor: pointer;
                        transition: background-color 0.3s ease;
                        width: 100%;
                    }
            
                    input[type="submit"]:hover {
                        background-color: #0056b3;
                    }
                </style>
            </head>
            <body>
                <div class="form-container">
                    <div class="form-content">
                        <h1>Enter an IP Address</h1>
                        <form action="/get-details" method="post">
                            <label for="ip">IP Address:</label>
                            <input type="text" id="ip" name="ipAddress" required>
                            <input type="submit" value="Get IP Details">
                        </form>
                    </div>
                </div>
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