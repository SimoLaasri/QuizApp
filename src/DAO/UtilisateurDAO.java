package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import metier.Utilisateur;
import metier.RoleUtilisateur;

public class UtilisateurDAO {

    public Utilisateur authentifier(String login, String motDePasse) {
        Utilisateur user = null;

        String sql = "SELECT * FROM utilisateur WHERE username = ? AND password = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, login);
            ps.setString(2, motDePasse);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("username"));
                user.setMotDePasse(rs.getString("password"));
                user.setRole(RoleUtilisateur.valueOf(rs.getString("role")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
