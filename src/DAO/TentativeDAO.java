package DAO;

import java.sql.*;
import java.util.*;
import metier.Tentative;
import metier.Utilisateur;
import metier.Quiz;

public class TentativeDAO {
    Connection con;
    Statement stmt;
    
    public TentativeDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/quizapp","root","");
            stmt = con.createStatement();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public int ajouterTentative(int etudiantId, int quizId, int score) {
        int nbr = 0;
        try {
            String request = "INSERT INTO tentative (etudiant_id, quiz_id, score) VALUES("+etudiantId+","+quizId+","+score+")";
            nbr = stmt.executeUpdate(request);
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Tentative");
            ex.printStackTrace();
        }
        return nbr;
    }

    public List<Tentative> recupererListTentativeParEtudiant(int etudiantId) {
        List<Tentative> tentatives = new ArrayList<>();
        try {
            String request = "SELECT t.*, u.id as uid, u.username, u.password, u.nom, u.prenom, u.role, " +
                           "q.id as qid, q.titre " +
                           "FROM tentative t " +
                           "JOIN utilisateur u ON t.etudiant_id = u.id " +
                           "JOIN quiz q ON t.quiz_id = q.id " +
                           "WHERE t.etudiant_id = "+etudiantId;
            ResultSet rs = stmt.executeQuery(request);

            while (rs.next()) {
                Utilisateur etudiant = new Utilisateur();
                etudiant.setId(rs.getInt("uid"));
                etudiant.setLogin(rs.getString("username"));

                Quiz quiz = new Quiz(rs.getInt("qid"), rs.getString("titre"));

                Tentative tentative = new Tentative(etudiant, quiz, rs.getInt("score"));
                tentatives.add(tentative);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur recupererListTentativeParEtudiant");
            ex.printStackTrace();
        }
        return tentatives;
    }

    public List<Tentative> recupererListTentativeParQuiz(int quizId) {
        List<Tentative> tentatives = new ArrayList<>();
        try {
            String request = "SELECT t.*, u.id as uid, u.username, u.password, u.nom, u.prenom, u.role, " +
                           "q.id as qid, q.titre " +
                           "FROM tentative t " +
                           "JOIN utilisateur u ON t.etudiant_id = u.id " +
                           "JOIN quiz q ON t.quiz_id = q.id " +
                           "WHERE t.quiz_id = "+quizId;
            ResultSet rs = stmt.executeQuery(request);

            while (rs.next()) {
                Utilisateur etudiant = new Utilisateur();
                etudiant.setId(rs.getInt("uid"));
                etudiant.setLogin(rs.getString("username"));

                Quiz quiz = new Quiz(rs.getInt("qid"), rs.getString("titre"));

                Tentative tentative = new Tentative(etudiant, quiz, rs.getInt("score"));
                tentatives.add(tentative);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur recupererListTentativeParQuiz");
            ex.printStackTrace();
        }
        return tentatives;
    }

    public List<Tentative> recupererToutesTentatives() {
        List<Tentative> tentatives = new ArrayList<>();
        try {
            String request = "SELECT t.*, u.id as uid, u.username, u.password, u.nom, u.prenom, u.role, " +
                           "q.id as qid, q.titre " +
                           "FROM tentative t " +
                           "JOIN utilisateur u ON t.etudiant_id = u.id " +
                           "JOIN quiz q ON t.quiz_id = q.id";
            ResultSet rs = stmt.executeQuery(request);

            while (rs.next()) {
                Utilisateur etudiant = new Utilisateur();
                etudiant.setId(rs.getInt("uid"));
                etudiant.setLogin(rs.getString("username"));

                Quiz quiz = new Quiz(rs.getInt("qid"), rs.getString("titre"));

                Tentative tentative = new Tentative(etudiant, quiz, rs.getInt("score"));
                tentatives.add(tentative);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur recupererToutesTentatives");
            ex.printStackTrace();
        }
        return tentatives;
    }
}
