package network;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UniteDB {

    public static final String DB = "icc.db";

    public static final String[] USER_NAME = {
            "GS1", "GS2", "GS3", "GS4", "GS5", "GS6", "GS7",
            "GS8", "GS9", "GS10", "GS11", "GS12"
    };

//    public static final String[] DB_FILE = {
//            "LifeMap_GS1.db", "LifeMap_GS2.db", "LifeMap_GS3.db", "LifeMap_GS4.db", "LifeMap_GS7.db",
//            "LifeMap_GS8.db", "LifeMap_GS9.db", "LifeMap_GS10.db", "LifeMap_GS12.db"
//    };

    public static final String[] DB_FILE = {
            "LifeMap_GS1.db", "LifeMap_GS2.db", "LifeMap_GS3.db", "LifeMap_GS4.db", "LifeMap_GS5.db",
            "LifeMap_GS6.db", "LifeMap_GS7.db", "LifeMap_GS8.db", "LifeMap_GS9.db", "LifeMap_GS10.db",
            "LifeMap_GS11.db", "LifeMap_GS12.db"
    };

    Connection conn;
    HashMap<Integer, String[]> data;
    ArrayList<Integer> userpointer;

    public void run() {
        try {
            int index = 0;
            data = new HashMap<>();
            userpointer = new ArrayList<>();
            for (int k = 0; k < USER_NAME.length; k++) {
//            for (int k = 0; k < 2; k++) {
                if (conn != null && !conn.isClosed()) conn.close();
                conn = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE[k]);
                System.out.println("user name:\t" + USER_NAME[k]);
                System.out.println("file name:\t" + DB_FILE[k]);

                String query = "SELECT * FROM apTable";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    data.put(++index, new String[]{
                            String.valueOf(k + 1),
                            rs.getString("_bssid"),
                            rs.getString("_ssid"),
                            rs.getString("_time_ap")
                    });
                }

                userpointer.add(data.size());
                if (userpointer.size() == 1)
                    System.out.println("user " + String.valueOf(k + 1) + " has " + data.size() + " records");
                else
                    System.out.println("user " + String.valueOf(k + 1) + " has " + String.valueOf(data.size() - userpointer.get(k - 1)) + " records");
            }
            System.out.println();
            System.out.println("data size: " + data.size());
//            System.out.println(data.get(26939)[0]);
//            System.out.println(data.get(26939)[1]);
//            System.out.println(data.get(26939)[2]);
//            System.out.println(data.get(26939)[3]);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            if (conn != null && !conn.isClosed()) conn.close();
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB);
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("driver name: " + meta.getDriverName());
            System.out.println("file name:\t icc.db");
            String query = "CREATE TABLE IF NOT EXISTS apTable ("
                    + "_ap_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "_user_id INTEGER NOT NULL, "
                    + "_bssid TEXT NOT NULL, "
                    + "_ssid TEXT, "
                    + "_time_ap TEXT NOT NULL, "
                    + "_epoch INTEGER NOT NULL );";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            if (conn != null && !conn.isClosed()) conn.close();
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB);
            System.out.println("file name:\t" + DB);
            for (int i = 1; i < data.size() + 1; i++) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                // following line give epoch for exact time
                // Date date = formatter.parse(data.get(i)[3]);
                // if we set minutes and seconds to zero, we create time interval of one hour
                Date date = formatter.parse(data.get(i)[3].substring(0, 10) + "0000");
                long epoch = date.getTime();
                // System.out.println(data.get(i)[3].substring(0,8));
                // System.out.println(epoch);
                String query = "INSERT INTO apTable(_user_id,_bssid,_ssid,_time_ap,_epoch) VALUES(?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, Integer.valueOf(data.get(i)[0]));
                pstmt.setString(2, data.get(i)[1]);
                pstmt.setString(3, data.get(i)[2]);
                pstmt.setString(4, data.get(i)[3]);
                pstmt.setLong(5, epoch);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        UniteDB db = new UniteDB();
        db.run();
    }

}