package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {
//    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PAYROLL_MANAGEMENT";
//    private static final String USER = "sa";
//    private static final String PASSWORD = "123456";

    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=PAYROLL_MANAGEMENT";
    private static final String serverName = "localhost";
    private static final String portNumber = "1433";
    private static final String databaseName = "PAYROLL_MANAGEMENT";
    private static final String userName = "sa";
    private static final String password = "123456";


    static String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=PAYROLL_MANAGEMENT;"
                        + "user=sa;"
                        + "password=123456;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    private static String getConnectionUrl() {
        return url + serverName + ":" + portNumber + ";databaseName=" + databaseName ;
    }

    public static java.sql.Connection GetConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = java.sql.DriverManager.getConnection(connectionUrl);
            if (con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return con;
    }
}




