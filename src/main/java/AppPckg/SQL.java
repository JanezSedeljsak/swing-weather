package AppPckg;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
                .append("`title` TEXT NOT NULL,")
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

    public static Dictionary gHsitoryById(int id) {
        Dictionary data = new Hashtable();
        String sql = new StringBuffer()
                .append("SELECT * FROM history WHERE id = ")
                .append(id)
                .toString();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt  = conn.createStatement();
             ResultSet result    = stmt.executeQuery(sql)){
            if(result.next()) {
                data.put("data", result.getString("data"));
                data.put("stamp", result.getString("stamp"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public static List<String[]> gHistory() {
        String sql = "SELECT id, title, stamp FROM history ORDER BY id DESC";

        List<String[]> rows = new ArrayList<String[]>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement stmt  = conn.createStatement();
             ResultSet result    = stmt.executeQuery(sql)){
            while (result.next()) {
                String row[] = {String.valueOf(result.getInt("id")), result.getString("title"), result.getString("stamp")};
                rows.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rows;
    }

    public static boolean createNewRecord(String weatherData) throws SQLException, ClassNotFoundException, ParseException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(weatherData);
        String title = (String) data.get("name");
        String sql = "INSERT INTO history (data, title, stamp) VALUES ('"+weatherData+"', '" + title + "', '"+java.time.LocalDate.now().toString()+"')";
        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            conn.close();
            return true;
        } catch (SQLException e) {
            conn.close();
            return false;
        }
    }
}
