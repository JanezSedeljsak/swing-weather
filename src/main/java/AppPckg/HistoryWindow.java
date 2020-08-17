package AppPckg;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class HistoryWindow {
    public static void display() {

        JFrame f = new JFrame();

        List<String[]> histroyResult = SQL.gHistory();
        String data[][] = new String[histroyResult.size()][3];
        data = histroyResult.toArray(data);

        String column[] = {"Id","Lokacija", "Datum"};
        final JTable historyTable = new JTable(data,column);
        historyTable.setRowSelectionAllowed(true);
        historyTable.setBounds(0,0,540,200);

        JScrollPane panel = new JScrollPane(historyTable);
        f.add(historyTable);
        f.setSize(540,200);
        f.setLayout(null);
        f.setVisible(true);
        f.setTitle("Zgodovina iskanja");
        f.setLocationRelativeTo(null);
    }

    public static void example() {
        JFrame f = new JFrame("Table Example");
        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};
        final JTable jt=new JTable(data,column);
        jt.setCellSelectionEnabled(true);
        ListSelectionModel select= jt.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int[] row = jt.getSelectedRows();
                int[] columns = jt.getSelectedColumns();
                for (int i = 0; i < row.length; i++) {
                    for (int j = 0; j < columns.length; j++) {
                        Data = (String) jt.getValueAt(row[i], columns[j]);
                    } }
                System.out.println("Table element selected is: " + Data);
            }
        });
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        f.setSize(300, 200);
        f.setVisible(true);
    }
}
