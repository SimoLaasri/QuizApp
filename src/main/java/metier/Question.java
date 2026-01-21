package metier;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private String enonce;
    private List<String> choix;
    private int bonneReponse;

    public Question() {
        this.choix = new ArrayList<>();
    }

    public Question(int id, String enonce, List<String> choix, int bonneReponse) {
        this.id = id;
        this.enonce = enonce;
        this.choix = choix;
        this.bonneReponse = bonneReponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public List<String> getChoix() {
        return choix;
    }

    public void setChoix(List<String> choix) {
        this.choix = choix;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(int bonneReponse) {
        this.bonneReponse = bonneReponse;
    }
}
