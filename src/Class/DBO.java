package Class;
import java.sql.*;


public class DBO {
    public DBO() { }
    public Connection getConnection() throws SQLException{
        Connection cn = null;
        try{
            String server = "jdbc:mysql://localhost:3306/example_book";
            String drever = "com.mysql.jdbc.Driver";
            Class.forName(drever);
            cn = DriverManager.getConnection (server, "root","");
            return cn;
        }catch (SQLException se){
            System.out.println(se.toString());
            return null;
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
