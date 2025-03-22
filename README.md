# IPAA - IP Address Analyzer

## Overview

IPAA (IP Address Analyzer) is a Java-based application that retrieves and displays detailed information about a given IP address. It uses an external API (ipinfo.io) to fetch details like city, region, country, postal code, latitude, and longitude. The application provides a user-friendly web interface powered by Javalin to interact with the analyzer.

## Features

- **Accepts an IP address through a web-based form.
Fetches detailed information about the IP address, including:**
  - City
  - Region
  - Country
  - Postal code
  - Latitude and longitude

- **Provides a link to view the IP's location on Google Maps.**
- **Uses a clean and responsive web interface for ease of use.**

## Project Structure

```bash
    IPAA/
    ├── src/
    │   ├── IPDetails.java        # Handles fetching and parsing IP details
    │   ├── Main.java             # Entry point and web server logic
    └── resources/                # Static resources (if needed)
```

## Class Descriptions

1.**IPDetails.java**

- Fetches IP details from ipinfo.io.
- Parses the JSON response and extracts fields such as city, region, country, postal code, latitude, and longitude.
- Handles errors gracefully and provides meaningful feedback.

2.**Main.java**

- Implements a web server using Javalin.
Serves a form-based web page for entering an IP address.
- Processes the submitted IP address and displays the details in a user-friendly HTML response.
- Provides a Google Maps link for geographical visualization of the IP location.

## Prerequisites

To run the project, ensure you have the following installed:

- Java 17 or later
- Maven for dependency management
- Internet connection (required for API requests to ipinfo.io)

## Setup and Installation

1.**Clone the repository:**

```bash
git clone https://github.com/Ashleylindi/IPAA.git
cd IPAA
```

2.**Build the project:**

```bash
mvn clean install
```

3.**Run the application:**

```bash
mvn exec:java -Dexec.mainClass="IPAA.Main"
```

4.**Access the web interface: Open your browser and navigate to <http://localhost:7000>.**

## Usage

- Enter an IP address in the form provided on the main page.
- Click the Get IP Details button.
- View the detailed IP information and optionally open the location on Google Maps.

## Dependencies

The project uses the following libraries:

- Javalin: Web server framework for serving and handling HTTP requests.
- JSON: For parsing API responses.
- Java HTTP: To send and handle HTTP requests.
- Dependencies are managed via Maven. Refer to the pom.xml file for details.

## Limitations

- The application depends on the ipinfo.io API, which may impose rate limits.
- Requires an active internet connection to fetch IP details.
- API keys may be required for high-volume usage (not included in this project).

## Future Improvements

- Add support for API keys for better scalability.
- Cache results to reduce API calls.
- Enhance error handling for more robust user feedback.

## Contact Information

Feel free to reach out if you'd like to collaborate or discuss a project:

1.**Ashley Xaba**

- Email: <lindix332@gmail.com>

- GitHub: <https://github.com/Ashleylindi>

- LinkedIn: <https://www.linkedin.com/in/ashleylindi/>

2.**Tebogo Phiri**

- Email: <tphiri.firi@gmail.com>

- GitHub: <https://github.com/TebogoP>

- LinkedIn: <https://www.linkedin.com/in/tebogo-phiri-b5a96796/>
