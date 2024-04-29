package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDatabase {
    private final String URL = "jdbc:mysql://localhost:3306/code_red";
    private final String USER = "root";
    private final String PASS = "";
    MyDatabase cnx;
    private Connection connection;

    private  static MyDatabase instance;
    public MyDatabase (){
        try {
            connection = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Connection réussite");
        } catch (SQLException e) {
            System.err.println("Échec de la connexion : " + e.getMessage());
        }
    }
   public  MyDatabase getCnx(){ return cnx;}
    public static MyDatabase getInstance() {
        if(instance == null)
            instance = new MyDatabase();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
