package AppPckg;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class WeatherWindow {
    public static void display(Object... params) throws Exception {
        JFrame f = new JFrame();
        String weatherResponse = "";
        if (params.length == 0) {
            Helpers.showMessageDialog(f, "Nepravilen klic funkcije");
            return;
        } else if (params[0] instanceof String) {
            weatherResponse = (String) params[0];
        } else if (params[0] instanceof Integer) {
            int historyIndex = (Integer) params[0];
            System.out.println(SQL.gSearchById(historyIndex));
        }

        JSONParser parser = new JSONParser();
        JSONObject data;
        data = (JSONObject) parser.parse(weatherResponse);

        JLabel locationLabel = new JLabel();
        locationLabel.setText("Vreme za: " + (String)data.get("name"));
        locationLabel.setBounds(10,10,500, 20);

        JLabel currentDateLabel = new JLabel();
        currentDateLabel.setText("Datum: " + java.time.LocalDate.now().toString());
        currentDateLabel.setBounds(10,40,500, 20);

        JLabel weatherData = new JLabel();
        weatherData.setText(
                "Temperatura: " + String.valueOf(((JSONObject)data.get("main")).get("temp")) + "\n"
                + "Veter: " + String.valueOf(((JSONObject)data.get("wind")).get("speed")) + "km/h \n"
        );
        weatherData.setBounds(10, 70, 500, 50);

        JSONArray forcast = (JSONArray) data.get("weather");





        String week[]= { "Monday","Tuesday","Wednesday",
                "Thursday","Friday","Saturday","Sunday"};

        JList forecast = new JList(week);
        forecast.setBounds(10,150, 520, 300);



        f.add(locationLabel);
        f.add(currentDateLabel);
        f.add(weatherData);

        f.setSize(540,800);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Vreme");
        f.setLocationRelativeTo(null);
    }
}
