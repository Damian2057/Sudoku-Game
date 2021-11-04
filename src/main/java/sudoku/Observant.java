package sudoku;

public interface Observant {
    void addObserver(Observer observer);

    boolean deleteObserver(Observer observer);

    void updateObserver();
}
