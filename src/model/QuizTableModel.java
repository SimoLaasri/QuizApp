package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import metier.Quiz;

public class QuizTableModel extends AbstractTableModel {
    
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_TITRE = 1;
    private static final int COLUMN_NB_QUESTIONS = 2;
    
    private String[] colonnes = {"ID", "Titre", "Nombre de Questions"};
    private List<Quiz> quizzes;
    
    public QuizTableModel() {
        this.quizzes = new ArrayList<>();
    }
    
    public void setItems(List<Quiz> quizzes) {
        this.quizzes = quizzes;
        fireTableDataChanged();
    }
    
    @Override
    public int getColumnCount() {
        return colonnes.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colonnes[column];
    }
    
    @Override
    public int getRowCount() {
        return quizzes.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Quiz quiz = quizzes.get(rowIndex);
        switch (columnIndex) {
            case COLUMN_ID:
                return quiz.getId();
            case COLUMN_TITRE:
                return quiz.getTitre();
            case COLUMN_NB_QUESTIONS:
                return quiz.getQuestions().size();
            default:
                return null;
        }
    }
    
    public Quiz getQuizAt(int rowIndex) {
        return quizzes.get(rowIndex);
    }
}
