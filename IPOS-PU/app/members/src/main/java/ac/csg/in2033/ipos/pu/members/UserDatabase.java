package ac.csg.in2033.ipos.pu.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDatabase {

    // create user table
    public static void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT UNIQUE NOT NULL,"
                + "password TEXT NOT NULL,"
                + "userType TEXT NOT NULL,"
                + "email TEXT UNIQUE NOT NULL,"
                + "firstLogin INTEGER DEFAULT 1"
                + ");";

        try (Connection conn = Database.connect();
             Statement s = conn.createStatement()) {

            s.execute(sql);
            System.out.println("Users table created");

        } catch (Exception e) {
            System.out.println("Failed to create table");
        }
    }

    // insert user
    public static void insertUser(String username, String email, String password, String userType) {

        String sql = "INSERT INTO users(username, password, userType , email) VALUES(?,?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, userType );
            ps.setString(4, email);

            ps.executeUpdate();
            System.out.println("User added");

        } catch (Exception e) {
            System.out.println("Error adding user");
        }
    }

    // login
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

    // change password
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
    public static void setFirstLoginFalse(String email) {

        String sql = "UPDATE users SET firstLogin=0 WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("failed to set first login");
        }
    }
}