package model;

import controller.Logic;
import view.wireCanvas.Canvas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Simulator extends Observable {
  private final int DEFAULT_DELAY = 550;

  private List<Observer> observers;
  private Grid grid;
  private JPanel canvas;
  private Logic logic;
  private Timer timer;

  private static Simulator instance = new Simulator();

  public static Simulator getInstance() {
    return instance;
  }

  private Simulator() {
    grid = new Grid();
    canvas = new Canvas(grid);
    logic = new Logic();
    timer = new Timer(DEFAULT_DELAY, e -> {
      logic.nextGeneration(grid);
      canvas.repaint();
    });
    observers = new ArrayList<>();
  }

  public Grid getGrid() {
    return grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
    canvas = new Canvas(grid);
    notifyObservers();
  }

  public Canvas getCanvas() {
    return (Canvas)canvas;
  }

  public void setGridSize(int columnCount, int rowCount) {
    grid.setColumnCount(columnCount);
    grid.setRowCount(rowCount);

    canvas = new Canvas(grid);
    notifyObservers();
  }

  public void setTimerDelay(int delay) {
    timer.setDelay(delay);
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

  @Override
  public synchronized void addObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public synchronized void deleteObserver(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(this, canvas);
    }
  }
}
