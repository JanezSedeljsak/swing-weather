package AppPckg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class APIHelper {
    public static String get_(String uri) throws  Exception {
        URL obj = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

public class Api {
    public static String API_KEY = "70ef7d1ecc959f4ef1a91a8a4ab7a914";

    public static String gWeatherByLocation(String location) throws Exception {
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + Api.API_KEY;
        return APIHelper.get_(uri);
    }

    public static String gWeatherByGeoLocation(String latitude, String longitude)  throws Exception {
        String uri = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + Api.API_KEY;
        return APIHelper.get_(uri);
    }
}