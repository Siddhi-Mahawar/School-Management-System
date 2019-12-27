package loginapp;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModal {

    Connection connection;

    public LoginModal(){
        try{
            this.connection  = dbConnection.getConnection();
            System.out.println("Connected");
        }catch (SQLException e){
            System.out.println("Not Connected");
            e.printStackTrace();
        }

        if(this.connection == null){
            System.out.println("Not Connected");
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected(){

        return this.connection  != null;
    }

    public boolean isLogin(String user,String pass,String div) throws SQLException {

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login WHERE username = ? AND password = ? AND division = ? ";

        try{

            pr = this.connection.prepareStatement(sql);
            pr.setString(1,user);
            pr.setString(2,pass);
            pr.setString(3,div);

            rs = pr.executeQuery();

            if(rs.next())
                return true;

            return false;

        }catch (SQLException e) {
            return false;
        }

        finally {
            {
                pr.close();
                rs.close();
            }
        }


    }

}
