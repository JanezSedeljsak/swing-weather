package AppPckg;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQL {

    public static void createDB() throws ClassNotFoundException, IOException {
        //get db file
        try{
            File database = new File("database.db");
            database.createNewFile();
            System.out.println("create db");
        }catch (Exception err){
            Helpers.showMessageDialog(null, "Napaka pri kreiranju baze");
            //custom error!!!
        }

        String[] tables = {""};
        tables[0] = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS `history`")
                .append("( `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,")
                .append("`data` TEXT NOT NULL,")
                .append("`stamp` TEXT NOT NULL)")
                .toString();
        //generate tables if they dont exsist
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = conn.createStatement()) {
            for(String sql: tables) stmt.execute(sql);
            System.out.println(tables.toString());
        } catch (SQLException e) {
            System.out.println(e.toString());
            //error on base connection
        }
    }

    public static Object gSearchById(int id) {

        String sql = new StringBuffer()
                .append("SELECT * FROM history")
                .append("WHERE id = '")
                .append(id)
                .append("';")
                .toString();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt  = conn.createStatement();
             ResultSet result    = stmt.executeQuery(sql)){
            if(result.next()) {
                return "vreme";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Object gHistory() {
        return null;
    }

    public static boolean writeToDB() {
        return true;
    }
}
