package AppPckg;
import javax.swing.*;

public class MainWindow {
    public static void main(String[] args) throws Exception {
        JFrame f=new JFrame();

        JTextArea searchBar = new JTextArea("Type Here!");
        searchBar.setBounds(10,10,500,20);

        JButton b=new JButton("click");
        b.setBounds(20,100,500, 500);

        f.add(b);
        f.add(searchBar);

        f.setSize(540,360);
        f.setLayout(null);
        f.setVisible(true);
        //System.out.println(Api.gWeatherByLocation("velenje"));

    }
}