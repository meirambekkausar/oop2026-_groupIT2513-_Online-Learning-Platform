package platform.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConfig {
    private static final String URL =
            "jdbc:postgresql://aws-1-ap-northeast-2.pooler.supabase.com:5432/postgres?sslmode=require";
    private static final String USER = "postgres.eblswscbdczprhchtdpx";
    private static final String PASSWORD = "KLrwZ3Sv4XNzIErR";  // ← DATABASE PASSWORD
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
