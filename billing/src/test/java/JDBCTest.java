import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by saturn on 01.11.2016.
 */
public class JDBCTest {
    public static void main (String... args) throws SQLException {

        JDBCTest test = new JDBCTest();
        try (Connection conn = test.getConnection();) {
            System.out.println("conn=" + conn);
        }

    }
    public  Connection getConnection() throws SQLException {

        Properties connectionProps = new Properties();
        connectionProps.put("user", "def");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/default", connectionProps);
    }
}
