package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/quizapp";
            String user = "root";
            String password = ""; // put your MySQL password here

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTED TO DATABASE");

            conn.close();
        } catch (Exception e) {
            System.out.println("CONNECTION FAILED");
            e.printStackTrace();
        }
    }
}
