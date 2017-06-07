package wireworld.model;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Zawiera informacje o rozmiarze i komórkach danej siatki.
 */
public class Grid implements Serializable {
  private final int DEFAULT_ROW_COUNT = 32;
  private final int DEFAULT_COLUMN_COUNT = 32;

  private int rowCount;
  private int columnCount;
  private HashMap<Point, Cell> selectedCells;

  /**
   * Tworzy i inicjalizuje siatkę komórek o domyślnym rozmiarze.
   */
  Grid() {
    this.rowCount = DEFAULT_ROW_COUNT;
    this.columnCount = DEFAULT_COLUMN_COUNT;
    selectedCells = new HashMap<>();
  }

  /**
   * Zwraca liczbę wierszy danej siatki.
   * @return liczba wierszy danej siatki
   */
  public int getRowCount() {
    return rowCount;
  }

  /**
   * Ustawia liczbę wierszy danej siatki.
   * @param rowCount nowa liczba wierszy danej siatki.
   */
  void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  /**
   * Zwraca liczbę kolumn danej siatki.
   * @return liczba kolumn danej siatki
   */
  public int getColumnCount() {
    return columnCount;
  }

  /**
   * Ustawia liczbę kolumn danej siatki.
   * @param columnCount nowa liczba kolumn danej siatki
   */
  void setColumnCount(int columnCount) {
    this.columnCount = columnCount;
  }

  /**
   * Zwraca komórki siatki niębędące w stanie pustym.
   * @return tablica z hashowaniem zawierająca komórki danej siatki niebędące w stanie pustym
   * @see HashMap
   */
  public HashMap<Point, Cell> getSelectedCells() {
    return selectedCells;
  }

  /**
   * Zwraca stan danej komórki siatki.
   * @param point punkt określający położenie komórki
   * @return stan danej komórki
   */
  public Color getCellState(Point point) {
    Cell cell = selectedCells.get(point);

    if (cell != null) {
      return cell.getState();
    }

    return State.EMPTY;
  }
}
