package daos;
import models.Car;
import java.sql.*;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;
public class ConnectionFactory {
    public static final String URL = "jdbc:postgresql://localhost:5432/cars";
    public static final String USER = "chung";
    public static final String PASS = "chung";
    public static Connection getConnection(){
        try{
            DriverManager.registerDriver(new Driver() {
                @Override
                public Connection connect(String url, Properties info) throws SQLException {
                    return null;
                }
                @Override
                public boolean acceptsURL(String url) throws SQLException {
                    return false;
                }
                @Override
                public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                    return new DriverPropertyInfo[0];
                }
                @Override
                public int getMajorVersion() {
                    return 0;
                }
                @Override
                public int getMinorVersion() {
                    return 0;
                }
                @Override
                public boolean jdbcCompliant() {
                    return false;
                }
                @Override
                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                    return null;
                }
            });
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(SQLException e){
            throw new RuntimeException("Error Connecting to the database", e);
        }
    }
}