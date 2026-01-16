package DAO;

import java.sql.*;
import java.util.*;
import metier.Question;

public class QuestionDAO {

    public List<Question> findByQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE quiz_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    public void save(Question question, int quizId) {
        String sql = "INSERT INTO question (quiz_id, enonce, choixA, choixB, choixC, choixD, bonneReponse) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ps.setString(2, question.getEnonce());
            
            List<String> choix = question.getChoix();
            ps.setString(3, choix.get(0));
            ps.setString(4, choix.get(1));
            ps.setString(5, choix.get(2));
            ps.setString(6, choix.get(3));
            ps.setInt(7, question.getBonneReponse());
            
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
