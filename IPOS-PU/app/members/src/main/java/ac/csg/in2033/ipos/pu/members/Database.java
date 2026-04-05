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

    private static final String DB_NAME = "users.db";

    public static Connection connect() {
        try {
            File dbFile = new File(DB_NAME);
            System.out.println("DB path: " + dbFile.getAbsolutePath());
            System.out.println("DB exists: " + dbFile.exists());

            if (!dbFile.exists()) {
                InputStream is = Database.class.getResourceAsStream("/sql/" + DB_NAME);
                if (is != null) {
                    Files.copy(is, dbFile.toPath());
                }
            }

            String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
            return DriverManager.getConnection(url);

        } catch (Exception e) {
            logger.error("Database connection failed:", e);
            return null;
        }
    }
}