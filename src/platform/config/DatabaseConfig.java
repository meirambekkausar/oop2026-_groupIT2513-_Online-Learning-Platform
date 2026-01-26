package platform.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
public class DatabaseConfig {
    private static final String URL =
            "jdbc:postgresql://aws-1-ap-northeast-2.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.eblswscbdczprhchtdpx";
    private static final String PASSWORD ;

    static {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);  // load all key=value pairs from the file
            PASSWORD = properties.getProperty("DB_PASSWORD");  // copy the DB_PASSWORD value into PASSWORD
        } catch (IOException e) {
            throw new RuntimeException("Cannot load database password", e);
        }

    }
    private DatabaseConfig() {

    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
