package AppPckg;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryWindow {
    public static void display() {

        final JFrame f = new JFrame();

        List<String[]> histroyResult = SQL.gHistory();
        String data[][] = new String[histroyResult.size()][3];
        data = histroyResult.toArray(data);



        String column[] = {"Id","Lokacija", "Datum"};
        final JTable historyTable = new JTable(data,column);
        historyTable.setRowSelectionAllowed(true);
        historyTable.setBounds(0, 50,540,200);

        JButton sHistoryButton = new JButton("Prikaži podatke iz zgodovine");
        sHistoryButton.setBounds(10, 10, 500, 30);
        final String[][] finalData = data;
        sHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = historyTable.getSelectedRow();
                if (index == -1) {
                    Helpers.showMessageDialog(f, "Prosimo izberite vrstico iz zgodovine");
                } else {
                    try {
                        WeatherWindow.display(Integer.valueOf(finalData[index][0]));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        f.add(historyTable);
        f.add(sHistoryButton);
        f.setSize(540,200);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Zgodovina iskanja");
        f.setLocationRelativeTo(null);
    }
}
