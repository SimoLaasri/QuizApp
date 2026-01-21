package DAO;

import java.sql.*;
import java.util.*;
import metier.Utilisateur;
import metier.RoleUtilisateur;

public class UtilisateurDAO {
    Connection con;
    Statement stmt;
    
    public UtilisateurDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/quizapp","root","");
            stmt = con.createStatement();
        }
        catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    public Utilisateur authentifier(String login, String motDePasse) {
        Utilisateur user = null;
        try {
            String request = "SELECT * FROM utilisateur WHERE username = \""+login+"\" AND password = \""+motDePasse+"\"";
            ResultSet rs = stmt.executeQuery(request);
            if (rs.next()) {
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("username"));
                user.setMotDePasse(rs.getString("password"));
                user.setRole(RoleUtilisateur.valueOf(rs.getString("role")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur Authentification");
            ex.printStackTrace();
        }
        return user;
    }
    
    public List<Utilisateur> recupererListUtilisateur(){
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            String request = "SELECT * FROM utilisateur";
            ResultSet rs = stmt.executeQuery(request);
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("username"));
                user.setMotDePasse(rs.getString("password"));
                user.setRole(RoleUtilisateur.valueOf(rs.getString("role")));
                utilisateurs.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur recupererListUtilisateur");
            ex.printStackTrace();
        }
        return utilisateurs;
    }
    
    /**
     * Register a new student in the database
     * @param username Student's username
     * @param password Student's password
     * @param nom Student's last name
     * @param prenom Student's first name
     * @return The ID of the newly created user, or -1 if failed
     */
    public int inscrireEtudiant(String username, String password, String nom, String prenom) {
        int userId = -1;
        try {
            String request = "INSERT INTO utilisateur (username, password, nom, prenom, role) VALUES (?, ?, ?, ?, 'ETUDIANT')";
            PreparedStatement pstmt = con.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, nom);
            pstmt.setString(4, prenom);
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    userId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur inscrireEtudiant");
            ex.printStackTrace();
        }
        return userId;
    }
    
    /**
     * Check if a username already exists in the database
     * @param username Username to check
     * @return true if username exists, false otherwise
     */
    public boolean usernameExiste(String username) {
        try {
            String request = "SELECT COUNT(*) FROM utilisateur WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(request);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur usernameExiste");
            ex.printStackTrace();
        }
        return false;
    }
}
