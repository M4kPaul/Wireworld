import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

public class Grid implements Serializable {
  private int rowCount;
  private int columnCount;
  private HashMap<Point, Cell> selectedCells;

  public Grid() {
    this.rowCount = 32;
    this.columnCount = 32;
    selectedCells = new HashMap<>();
  }

  public int getColumnCount() {
    return columnCount;
  }

  public void setColumnCount(int columnCount) {
    this.columnCount = columnCount;
  }

  public int getRowCount() {
    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public HashMap<Point, Cell> getSelectedCells() {
    return selectedCells;
  }

  public Color getCellState(Point point) {
    Cell cell = selectedCells.get(point);

    if (cell != null) {
      return cell.getState();
    }
    return State.EMPTY;
  }
}
