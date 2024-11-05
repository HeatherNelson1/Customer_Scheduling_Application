package Utilities;
/**
 * establishes connection to mysql database
 */

import com.sun.source.tree.TryTree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBConnection {
//JDBC URL PARTS
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress= "//wgudb.ucertify.com:3306/";
    private static  final String dbName="WJ05XK7";
//JDBC URL
    private static final String jdbcURL= protocol + vendorName + ipAddress +dbName;
//DRIVER AND CONNECTION INTERFACE
    private static final String MySQLDatabaseDriver = "com.mysql.jdbc.Driver";
    private static  Connection conn = null;

    private static final String username = "U05XK7";
    private static final String password= "53688633223";

    //Connection method

    public static Connection startConnection() {
        //establishing connection
        try {
            Class.forName(MySQLDatabaseDriver);
            //expects pass jdbc url and user/passwd
            conn = (Connection)DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("connection successful "+ LocalDateTime.now());
        }
        catch(ClassNotFoundException e)
            {
                e.printStackTrace();
               // System.out.println(e.getMessage());

            }
        catch(SQLException e)
        {
            e.printStackTrace();
           // System.out.println(e.getMessage());
        }
        return conn;
        }
        public static Connection getConnection(){
        return conn;
        }

        public static   void closeConnection(){
        try {
            conn.close();
            System.out.println("Connection_Closed "+ LocalDateTime.now());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //print stack trace
        }
        }


}
