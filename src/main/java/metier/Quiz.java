package metier;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int id;
    private String titre;
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public Quiz(int id, String titre) {
        this.id = id;
        this.titre = titre;
        this.questions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void ajouterQuestion(Question q) {
        this.questions.add(q);
    }
}
