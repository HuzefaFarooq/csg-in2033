package ac.csg.in2033.ipos.pu.members;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private final static Logger logger = LoggerFactory.getLogger(Database.class);

    private static final String DB_DIR = System.getProperty("user.dir") + "/data";
    private static final String DB_NAME = "users.db";

    public static Connection connect() {
        try {
            // Define data directory
            File dir = new File(DB_DIR);
            // If directory does not exist
            if (!dir.exists()) {
                // Create data directory
                // If data directory cannot be created
                if (!dir.mkdirs()) {
                    // Log failure to create directory
                    logger.error("Failed to create database directory: {}", dir.getAbsolutePath());
                }
            }

            // Create user database file
            File dbFile = new File(dir, DB_NAME);

            // FIXME: Temporary debugging printing
            System.out.println("DB path: " + dbFile.getAbsolutePath());
            System.out.println("DB exists: " + dbFile.exists());

            // If database doesn't exist and test db does exist
            // Copy test data to real database
            if (!dbFile.exists()) {
                InputStream is = Database.class.getResourceAsStream("/sql/" + DB_NAME);
                if (is != null) {
                    Files.copy(is, dbFile.toPath());
                }
            }

            // Return connection to database
            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            return DriverManager.getConnection(url);

        } catch (Exception e) {
            logger.error("Database connection failed:", e);
            return null;
        }
    }
}