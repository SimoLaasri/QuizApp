package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import metier.Tentative;

public class ResultatTableModel extends AbstractTableModel {
    
    private static final int COLUMN_ETUDIANT = 0;
    private static final int COLUMN_QUIZ = 1;
    private static final int COLUMN_SCORE = 2;
    
    private String[] colonnes = {"Étudiant", "Quiz", "Score"};
    private List<Tentative> tentatives;
    
    public ResultatTableModel() {
        this.tentatives = new ArrayList<>();
    }
    
    public void setItems(List<Tentative> tentatives) {
        this.tentatives = tentatives;
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
        return tentatives.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tentative tentative = tentatives.get(rowIndex);
        switch (columnIndex) {
            case COLUMN_ETUDIANT:
                return tentative.getEtudiant().getLogin();
            case COLUMN_QUIZ:
                return tentative.getQuiz().getTitre();
            case COLUMN_SCORE:
                return tentative.getScore();
            default:
                return null;
        }
    }
    
    public Tentative getTentativeAt(int rowIndex) {
        return tentatives.get(rowIndex);
    }
}
