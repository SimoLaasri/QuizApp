package DAO;

import java.sql.*;
import java.util.*;
import metier.Tentative;
import metier.Utilisateur;
import metier.Quiz;

public class TentativeDAO {

    public void save(Tentative tentative) {
        String sql = "INSERT INTO tentative (etudiant_id, quiz_id, score) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tentative.getEtudiant().getId());
            ps.setInt(2, tentative.getQuiz().getId());
            ps.setInt(3, tentative.getScore());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Tentative> findByEtudiant(int etudiantId) {
        List<Tentative> tentatives = new ArrayList<>();
        String sql = "SELECT t.*, u.id as uid, u.username, u.password, u.nom, u.prenom, u.role, " +
                     "q.id as qid, q.titre " +
                     "FROM tentative t " +
                     "JOIN utilisateur u ON t.etudiant_id = u.id " +
                     "JOIN quiz q ON t.quiz_id = q.id " +
                     "WHERE t.etudiant_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, etudiantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur etudiant = new Utilisateur();
                etudiant.setId(rs.getInt("uid"));
                etudiant.setUsername(rs.getString("username"));

                Quiz quiz = new Quiz(rs.getInt("qid"), rs.getString("titre"));

                Tentative tentative = new Tentative(etudiant, quiz, rs.getInt("score"));
                tentatives.add(tentative);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tentatives;
    }

    public List<Tentative> findByQuiz(int quizId) {
        List<Tentative> tentatives = new ArrayList<>();
        String sql = "SELECT t.*, u.id as uid, u.username, u.password, u.nom, u.prenom, u.role, " +
                     "q.id as qid, q.titre " +
                     "FROM tentative t " +
                     "JOIN utilisateur u ON t.etudiant_id = u.id " +
                     "JOIN quiz q ON t.quiz_id = q.id " +
                     "WHERE t.quiz_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur etudiant = new Utilisateur();
                etudiant.setId(rs.getInt("uid"));
                etudiant.setUsername(rs.getString("username"));

                Quiz quiz = new Quiz(rs.getInt("qid"), rs.getString("titre"));

                Tentative tentative = new Tentative(etudiant, quiz, rs.getInt("score"));
                tentatives.add(tentative);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tentatives;
    }
}
