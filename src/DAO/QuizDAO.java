package DAO;

import java.sql.*;
import java.util.*;
import metier.Quiz;

public class QuizDAO {

    public List<Quiz> findAll() {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quiz";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                quizzes.add(
                    new Quiz(
                        rs.getInt("id"),
                        rs.getString("titre")
                    )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizzes;
    }
}
