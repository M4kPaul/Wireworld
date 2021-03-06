package wireworld.model;

import wireworld.controller.Logic;
import wireworld.view.Canvas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Zarządza całym procesem pracy automatu komórkowego.
 */
public class Simulator extends Observable {
  private static Simulator instance = new Simulator();
  private final int DEFAULT_DELAY = 550;
  private List<Observer> observers;
  private Grid grid;
  private JPanel canvas;
  private Logic logic;
  private Timer timer;
  private String selectedCircuitName;

  /**
   * Tworzy i inicjalizuje symulator zarządzający automatem komórkowym.
   */
  private Simulator() {
    grid = new Grid();
    canvas = new Canvas(grid, true);
    logic = new Logic();
    timer = new Timer(DEFAULT_DELAY, e -> {
      logic.nextGeneration(grid);
      canvas.repaint();
    });
    observers = new ArrayList<>();
  }

  /**
   * Zwraca jedyną instancję klasy {@code Simulator}.
   *
   * @return jedyna instancja klasy {@code Simulator}
   */
  public static Simulator getInstance() {
    return instance;
  }

  /**
   * Zwraca siatkę komórek.
   *
   * @return siatka komórek
   */
  public Grid getGrid() {
    return grid;
  }

  /**
   * Ustawia siatkę komórek, zmienia planszę wizualizującą siatkę komórek i powiadamia obserwatorów o zmianie.
   *
   * @param grid now siatka komórek
   */
  public void setGrid(Grid grid) {
    this.grid = grid;
    canvas = new Canvas(grid, false);
    notifyObservers();
  }

  /**
   * Zwraca planszę wizualizującą siatkę komórek.
   *
   * @return plansza wizualizująca siatkę komórek
   */
  public Canvas getCanvas() {
    return (Canvas)canvas;
  }

  /**
   * Ustawia nowy rozmiar siatki komórek, zmienia planszę wizualizującą siatkę komórek i powiadamia obserwatorów o
   * zmianie.
   *
   * @param columnCount nowa liczba kolumn siatki komórek
   * @param rowCount    nowa liczba wierszy siatki komórek
   */
  public void setGridSize(int columnCount, int rowCount) {
    if (grid.getColumnCount() == columnCount && grid.getRowCount() == rowCount) {
      canvas = new Canvas(grid, true);
    } else {
      grid.setColumnCount(columnCount);
      grid.setRowCount(rowCount);

      canvas = new Canvas(grid, false);
    }
    notifyObservers();
  }

  /**
   * Ustawia nowe opóźnienie stopera.
   *
   * @param delay nowe opóźnienie stopera
   */
  public void setTimerDelay(int delay) {
    timer.setDelay(delay);
  }

  /**
   * Zwraca nazwę wybranego gotowego obwodu
   *
   * @return nazwa wybranego gotowego obwodu
   */
  public String getSelectedCircuitName() {
    return selectedCircuitName;
  }

  /**
   * Ustawia nazwę wybranego gotowego obwodu
   *
   * @param selectedCircuitName nazwa wybranego gotowego obwodu
   */
  public void setSelectedCircuitNameName(String selectedCircuitName) {
    this.selectedCircuitName = selectedCircuitName;
  }

  /**
   * Uruchamia symulację automatu komórkowego.
   */
  public void start() {
    timer.start();
  }

  /**
   * Zatrzymuje symulację automatu komórkowego.
   */
  public void stop() {
    timer.stop();
  }

  /**
   * Dodaja danego obserwatora do listy obserwatorów.
   *
   * @param o nowy obserwator
   */
  @Override
  public synchronized void addObserver(Observer o) {
    observers.add(o);
  }

  /**
   * Usuwa danego obserwatora z listy obserwatorów.
   *
   * @param o obserwator, któreg trzeba usunąć
   */
  @Override
  public synchronized void deleteObserver(Observer o) {
    observers.remove(o);
  }

  /**
   * Powiadamia obserwatorów o zmianie.
   */
  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(this, canvas);
    }
  }
}
