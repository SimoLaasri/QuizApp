/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metier;

public class Utilisateur {

    private int id;
    private String login;
    private String motDePasse;
    private String nom;
    private String prenom;
    private RoleUtilisateur role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String login, String motDePasse,
                       String nom, String prenom, RoleUtilisateur role) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public RoleUtilisateur getRole() {
        return role;
    }

    public void setRole(RoleUtilisateur role) {
        this.role = role;
    }
}
