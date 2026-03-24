package ac.csg.in2033.ipos.pu.members;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:/Users/bahjasuliman/Documents/GitHub/csg-in2033/IPOS-PU/app/members/src/main/resources/Users.db";


        public static Connection connect() {
            try {
                Connection conn = DriverManager.getConnection(URL);
                System.out.println("Connected to database");
                return conn;
            } catch (SQLException e) {
                System.out.println("Connection failed");
                e.printStackTrace();
                return null;
            }
        }
}
