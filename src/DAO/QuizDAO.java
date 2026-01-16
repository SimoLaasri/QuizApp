package DAO;

import java.sql.*;
import java.util.*;
import metier.Quiz;
import metier.Question;

public class QuizDAO {

    public List<Quiz> findAll() {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quiz";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            QuestionDAO questionDAO = new QuestionDAO();
            
            while (rs.next()) {
                Quiz quiz = new Quiz(
                    rs.getInt("id"),
                    rs.getString("titre")
                );
                
                // Load questions for this quiz
                List<Question> questions = questionDAO.findByQuiz(quiz.getId());
                for (Question q : questions) {
                    quiz.ajouterQuestion(q);
                }
                
                quizzes.add(quiz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public int save(Quiz quiz) {
        String sql = "INSERT INTO quiz (titre) VALUES (?)";
        int generatedId = -1;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, quiz.getTitre());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }
}
