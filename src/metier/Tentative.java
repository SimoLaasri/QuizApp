package metier;

public class Tentative {

    private Utilisateur etudiant;
    private Quiz quiz;
    private int score;

    public Tentative() {
    }

    public Tentative(Utilisateur etudiant, Quiz quiz, int score) {
        this.etudiant = etudiant;
        this.quiz = quiz;
        this.score = score;
    }

    public Utilisateur getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Utilisateur etudiant) {
        this.etudiant = etudiant;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
