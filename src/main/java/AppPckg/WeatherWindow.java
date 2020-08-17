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
        locationLabel.setText("<html><h1>Vreme za: " + (String)data.get("name") + "</h1></html>");
        locationLabel.setBounds(10,10,500, 30);

        JLabel currentDateLabel = new JLabel();
        currentDateLabel.setText("<html><h3 style='color: #6f6f6f'>Datum: " + java.time.LocalDate.now().toString() + "</h3></html>");
        currentDateLabel.setBounds(10,40,500, 20);

        JLabel weatherData = new JLabel();
        weatherData.setText(
                "<html><ul>"
                + "<li>Vreme: " + String.valueOf(((JSONObject)((JSONArray)data.get("weather")).get(0)).get("main")) + "</li>\n"
                + "<li>Temperatura: " + String.valueOf(((JSONObject)data.get("main")).get("temp")) + "</li>\n"
                + "<li>Veter: " + String.valueOf(((JSONObject)data.get("wind")).get("speed")) + "km/h </li>\n"
                + "<li>Vlaga: " + String.valueOf(((JSONObject)data.get("main")).get("humidity")) + "% </li>\n"
                + "</ul></html>"
        );
        weatherData.setBounds(10, 70, 500, 80);


        f.add(locationLabel);
        f.add(currentDateLabel);
        f.add(weatherData);

        f.setSize(540, 200);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Vreme");
        f.setLocationRelativeTo(null);
    }
}
