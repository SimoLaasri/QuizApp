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
}
