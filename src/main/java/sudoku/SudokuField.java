package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuField implements Observant {
    private int value = 0;
    private List<Observer> observers = new ArrayList<>();

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
        updateObserver();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public boolean deleteObserver(Observer observer) {

        if (observers.contains(observer)) {
            observers.remove(observer);
            return true;
        }
        return observers.contains(observer);
    }

    @Override
    public void updateObserver() {
        observers.forEach(Observer::update);
    }
}
