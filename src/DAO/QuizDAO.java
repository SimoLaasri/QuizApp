package DAO;

import java.sql.*;
import java.util.*;
import metier.Quiz;
import metier.Question;

public class QuizDAO {
    Connection con;
    Statement stmt;
    
    public QuizDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/quizapp","root","");
            stmt = con.createStatement();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    public int ajouterQuiz(String titre){
        int generatedId = -1;
        try {
            String request = "INSERT INTO quiz (titre) VALUES(\""+titre+"\")";
            stmt.executeUpdate(request, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Quiz");
            ex.printStackTrace();
        }
        return generatedId;
    }
    
    public List<Quiz> recupererListQuiz() {
        List<Quiz> quizzes = new ArrayList<>();
        try {
            String request = "SELECT * FROM quiz";
            ResultSet rs = stmt.executeQuery(request);
            
            QuestionDAO questionDAO = new QuestionDAO();
            
            while (rs.next()) {
                Quiz quiz = new Quiz(
                    rs.getInt("id"),
                    rs.getString("titre")
                );
                
                // Load questions for this quiz
                List<Question> questions = questionDAO.recupererListQuestionParQuiz(quiz.getId());
                for (Question q : questions) {
                    quiz.ajouterQuestion(q);
                }
                
                quizzes.add(quiz);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur recupererListQuiz");
            ex.printStackTrace();
        }
        return quizzes;
    }
    
    public int supprimerQuiz(int id){
        int nbr = 0;
        try {
            String request = "DELETE FROM quiz WHERE id="+id;
            nbr = stmt.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println("Erreur Suppression Quiz");
            ex.printStackTrace();
        }
        return nbr;
    }
    
    public int modifierQuiz(int id, String titre){
        int nbr = 0;
        try {
            String request = "UPDATE quiz SET titre=\""+titre+"\" WHERE id="+id;
            nbr = stmt.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println("Erreur Modification Quiz");
            ex.printStackTrace();
        }
        return nbr;
    }
}
