package AppPckg;

public class Api {
    public static String API_KEY = "70ef7d1ecc959f4ef1a91a8a4ab7a914";

    public static String gWeatherByLocation(String location) throws Exception {
        String uri = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + Api.API_KEY;
        String result = Helpers.API_GET(uri);
        SQL.createNewRecord(result);
        return result;
    }

    public static String gWeatherByGeoLocation(Double latitude, Double longitude)  throws Exception {
        String uri = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&units=metric&appid=" + Api.API_KEY;
        return Helpers.API_GET(uri);
    }
}