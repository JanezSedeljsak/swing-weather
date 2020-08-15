package AppPckg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame();

        JLabel searchLabel = new JLabel();
        searchLabel.setText("Išči vreme po kraju");
        searchLabel.setBounds(10,10,500, 20);

        JTextArea searchBar = new JTextArea("");
        searchBar.setBounds(10,30,500,30);
        searchBar.setBorder(BorderFactory.createLineBorder(Color.black));

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