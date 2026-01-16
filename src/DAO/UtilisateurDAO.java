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
}
