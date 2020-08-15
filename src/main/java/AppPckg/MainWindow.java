package AppPckg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MainWindow {

    public static void main(String[] args) throws Exception, ClassNotFoundException {
        SQL.createDB();
        JFrame f = new JFrame();

        JLabel searchLabel = new JLabel();
        searchLabel.setText("Išči vreme po kraju");
        searchLabel.setBounds(10,10,500, 20);

        final JTextArea searchBar = new JTextArea("");
        searchBar.setBounds(10,30,500,30);
        searchBar.setBorder(BorderFactory.createLineBorder(Color.black));
        searchBar.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) {
                // enter pressed
                if (e.getKeyCode() == 10) {
                    try {
                        System.out.println(Api.gWeatherByLocation(searchBar.getText()));
                        searchBar.setText("");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        JButton historyButton = new JButton("Zgodovina iskanja");
        historyButton.setBounds(10, 70, 500, 30);
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HistoryWindow.display();
            }
        });

        JButton b = new JButton("click");
        b.setBounds(10,60,500, 30);

        f.add(searchBar);
        f.add(searchLabel);
        f.add(historyButton);

        f.setSize(540,160);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("App za vreme");
        f.setLocationRelativeTo(null);

    }
}