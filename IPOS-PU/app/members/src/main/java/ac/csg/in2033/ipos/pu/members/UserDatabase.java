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
    public static void insertUser(String email, String password, String userType) {

        String sql = "INSERT INTO users(email, password , userType) VALUES(?,?,?,?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, userType);

            ps.executeUpdate();
            System.out.println("User added");

        } catch (Exception e) {
            System.out.println("Error adding user");
        }
    }

    // login
    public static boolean login(String email, String password) {

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Failed to login");
            return false;
        }
    }
    public static String getUserType(String email) {

        String sql = "SELECT userType FROM users WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("userType");
            }

        } catch (Exception e) {
            System.out.println("Failed to get user type");
        }

        return null;
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
    public static void setFirstLogin(String email, boolean input) {
        // Convert boolean to 1 or 0 to be stored in the DB
        String s = input ? "1" : "0";
        // Create query
        String sql = "UPDATE users SET firstLogin=? WHERE email=?";
        // Connect to database
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set first ? to boolean parameter
            ps.setString(1, s);
            // Set second ? to email parameter
            ps.setString(2, email);
            // Execute query
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("failed to set first login");
        }
    }
    public static boolean isFirstLogin(String email) {

        String sql = "SELECT firstLogin FROM users WHERE email=?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("firstLogin") == 1;
            }

        } catch (Exception e) {
            System.out.println("Failed to check first login");
        }

        return false;
    }
    public static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}