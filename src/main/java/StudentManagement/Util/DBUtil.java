package StudentManagement.Util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBUtil {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conn = null;

    private static final String url = "jdbc:mysql://localhost:3306/StudentManagement";
    private static final String userName = "StudentAdmin";
    private static final String password = "adminpassword";

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        //Setting MySQL JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
            throw e;
        }
    }

    public static ResultSet dbExcuteQuery(String SqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        try {
            dbConnect();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(SqlStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null)
                resultSet.close();
            if (stmt != null)
                stmt.close();
            dbDisconnect();
        }
        return crs;
    }

    public static void dbExcuteUpdate(String SqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;

        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(SqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null)
                stmt.close();
            dbDisconnect();
        }
    }
}
