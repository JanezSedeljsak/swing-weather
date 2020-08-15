package AppPckg;
import javax.swing.*;
import java.awt.*;

public class WeatherWindow {
    public static void display() {
        JFrame f = new JFrame();


        f.setSize(540,200);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Vreme");
        f.setLocationRelativeTo(null);
    }
}
