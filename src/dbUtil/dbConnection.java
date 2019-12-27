package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN ="jdbc:mysql://localhost:3306/student";


    public static Connection getConnection() throws SQLException {

        Connection con = DriverManager.getConnection(CONN, USERNAME, PASSWORD);

        if(con== null)
                System.out.println("Not connected");

        return con;

    }

}
