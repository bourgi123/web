package cdu.connexion3A37.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection {


    private String url= "jdbc:mysql://localhost:3306/optihealth";
    private String login= "root";
    private String pwd= "";
    public static Myconnection instance;
    Connection cnx;
    public Myconnection()  {

        try {
          cnx =  DriverManager.getConnection(url,login,pwd);
            System.out.println("connexion établie...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  Connection getCnx() {
        return cnx;
    }

    public static Myconnection getInstance() {
        if(instance == null){
            instance = new Myconnection();
        }
        return instance;
    }
}
