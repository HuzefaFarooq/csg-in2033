package ac.csg.in2033.ipos.pu.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDatabase {

    // CREATE TABLE
    public static void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "username TEXT UNIQUE NOT  NULL,"
                + "password TEXT NOT NULL"
                + ");";

        try (Connection conn = Database.connect();
             Statement s = conn.createStatement()) {

            s.execute(sql);
            System.out.println("Users table created");

        } catch (Exception e) {
            System.out.println("Failed to create table");
        }
    }

    // INSERT USER (REGISTER)
    public static void insertUser(String username, String password) {

        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();
            System.out.println("User added to database");

        } catch (Exception e) {
            System.out.println("Failed to insert user");
        }
    }

    // LOGIN
    public static boolean login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Failed to login");
            return false;
        }
    }

    // CHANGE PASSWORD
    public static void changePassword(String username, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE username=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, username);

            ps.executeUpdate();
            System.out.println("Password changed");

        } catch (Exception e) {
            System.out.println("Failed to change password");
        }
    }
}