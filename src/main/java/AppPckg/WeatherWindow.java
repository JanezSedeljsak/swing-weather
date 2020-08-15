package AppPckg;
import javax.swing.*;
import java.awt.*;

public class WeatherWindow {
    public static void display(Object... params) throws Exception {
        JFrame f = new JFrame();
        if (params.length == 0) {
            Helpers.showMessageDialog(f, "Nepravilen klic funkcije");
            return;
        } else if (params[0] instanceof String) {
            System.out.println(Api.gWeatherByLocation((String) params[0]));
        } else if (params[0] instanceof Integer) {
            int historyIndex = (Integer) params[0];
            System.out.println(SQL.gSearchById(historyIndex));
        }



        f.setSize(540,200);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Vreme");
        f.setLocationRelativeTo(null);
    }
}
