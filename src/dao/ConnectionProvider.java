package dao;

import java.sql.*;
public class ConnectionProvider {
    public static Connection getCon(){
        try{
            String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
            Connection con = DriverManager.getConnection(url,"myhotel","mypassword");
            System.out.println("connection successful y grande");
            return con;
        }
        catch(Exception e ){
            System.out.println(e);
            return null;
        }
    }
}
