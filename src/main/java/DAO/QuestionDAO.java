package DAO;

import java.sql.*;
import java.util.*;
import metier.Question;

public class QuestionDAO {
    Connection con;
    Statement stmt;
    
    public QuestionDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/quizapp","root","");
            stmt = con.createStatement();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public List<Question> recupererListQuestionParQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();
        try {
            String request = "SELECT * FROM question WHERE quiz_id = "+quizId;
            ResultSet rs = stmt.executeQuery(request);

            while (rs.next()) {
                List<String> choix = Arrays.asList(
                    rs.getString("choixA"),
                    rs.getString("choixB"),
                    rs.getString("choixC"),
                    rs.getString("choixD")
                );

                questions.add(
                    new Question(
                        rs.getInt("id"),
                        rs.getString("enonce"),
                        choix,
                        rs.getInt("bonneReponse")
                    )
                );
            }
        } catch (SQLException ex) {
            System.out.println("Erreur recupererListQuestionParQuiz");
            ex.printStackTrace();
        }
        return questions;
    }

    public int ajouterQuestion(String enonce, String choixA, String choixB, String choixC, String choixD, int bonneReponse, int quizId) {
        int nbr = 0;
        try {
            String request = "INSERT INTO question (quiz_id, enonce, choixA, choixB, choixC, choixD, bonneReponse) VALUES("+quizId+",\""+enonce+"\",\""+choixA+"\",\""+choixB+"\",\""+choixC+"\",\""+choixD+"\","+bonneReponse+")";
            nbr = stmt.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Question");
            ex.printStackTrace();
        }
        return nbr;
    }
    
    public int supprimerQuestion(int id){
        int nbr = 0;
        try {
            String request = "DELETE FROM question WHERE id="+id;
            nbr = stmt.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println("Erreur Suppression Question");
            ex.printStackTrace();
        }
        return nbr;
    }
}
