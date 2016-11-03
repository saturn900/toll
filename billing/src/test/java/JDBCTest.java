import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.Properties;

/**
 * Created by saturn on 01.11.2016.
 */
public class JDBCTest {
    public static void main(String... args) throws SQLException {

        JDBCTest test = new JDBCTest();
        try (Connection conn = test.getConnection();) {
            System.out.println("conn=" + conn);
            viewTable(conn, "");
            System.out.println("==================");
            modifyPrices(conn, 0.34f);
            System.out.println("==================");
            viewTable(conn, "");
        }

    }

    public Connection getConnection() throws SQLException {

        Properties connectionProps = new Properties();
        connectionProps.put("user", "def");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/default", connectionProps);
    }

    public static void viewTable(Connection con, String dbName)
            throws SQLException {

        Statement stmt = null;
        String query =
                "select COF_NAME as COFFE1, SUP_ID, PRICE, " +
                        "SALES, TOTAL " +
                        "from " + (StringUtils.isEmpty(dbName) ? "" : dbName + ".") + "COFFEES";

        System.out.println(query);

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COFFE1");
                //int coffeeNameInt = rs.getInt("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                String supplierIDStr = rs.getString("SUP_ID");
                System.out.println("supplierIDStr " + supplierIDStr);
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t\t" + supplierID +
                        "\t" + price + "\t" + sales +
                        "\t" + total);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void modifyPrices(Connection con, float percentage) throws SQLException {

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM " + "COFFEES";
            ResultSet uprs = stmt.executeQuery(
                    sql);

            System.out.println(sql);

            while (uprs.next()) {
                float f = uprs.getFloat("PRICE");
                uprs.updateFloat( "PRICE", f * percentage);
                uprs.updateRow();
            }

        } catch (SQLException e ) {
            printSQLException(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }


    public static void printSQLException(SQLException ex) {

        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(
                        ((SQLException) e).
                                getSQLState()) == false) {

                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " +
                            ((SQLException) e).getSQLState());

                    System.err.println("Error Code: " +
                            ((SQLException) e).getErrorCode());

                    System.err.println("Message: " + e.getMessage());

                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

    public static boolean ignoreSQLException(String sqlState) {

        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }

        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32"))
            return true;

        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55"))
            return true;

        return false;
    }
}
