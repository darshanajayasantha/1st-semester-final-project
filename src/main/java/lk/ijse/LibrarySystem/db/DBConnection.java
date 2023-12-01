package lk.ijse.LibrarySystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

    public class DBConnection {

        private static final String URL = "jdbc:mysql://localhost:3306/librarySystem";

        private static final Properties prop = new Properties();

        static {
            prop.setProperty("user", "root");
            prop.setProperty("password", "1234");
        }

        private static DBConnection dbConnection;
        private Connection connection;

        private DBConnection() throws SQLException {
            connection = DriverManager.getConnection(URL, prop);
        }

        public static DBConnection getInstance() throws SQLException {
            if (dbConnection == null) {
                return dbConnection = new DBConnection();
            } else {
                return dbConnection;
            }
        }

        public Connection getConnection() {
            return connection;
        }
    }