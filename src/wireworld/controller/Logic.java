package wireworld.controller;

import wireworld.model.Cell;
import wireworld.model.Grid;
import wireworld.model.State;

/**
 * Zawiera informacje o sposobie tworzenia kolejnych generacji.
 */
public class Logic {

  /**
   * Tworzy następną generację komórek według ustalonej logiki.
   *
   * @param grid siatka komórek
   */
  public void nextGeneration(Grid grid) {
    countAdjacentElectronHead(grid);

    for (Cell cell : grid.getSelectedCells().values()) {
      if (cell.getState() == State.ELECTRON_HEAD) {
        cell.setState(State.ELECTRON_TAIL);
      } else if (cell.getState() == State.ELECTRON_TAIL) {
        cell.setState(State.CONDUCTOR);
      } else if (cell.getAdjacentElectronHeadCount() == 1 || cell.getAdjacentElectronHeadCount() == 2) {
        cell.setState(State.ELECTRON_HEAD);
      }
    }
  }

  /**
   * Wylicza danym komórkom siatki ich sąsiadów będących głowami elektronów.
   *
   * @param grid siatka komórek
   */
  private void countAdjacentElectronHead(Grid grid) {
    for (Cell cell : grid.getSelectedCells().values()) {
      int x = cell.getX();
      int y = cell.getY();
      int adjacent = 0;

      if (cell.getState().equals(State.CONDUCTOR)) {
        if (grid.getCellState(CompassPoint.North(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.NorthEast(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.East(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.SouthEast(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.South(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.SouthWest(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.West(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
        if (grid.getCellState(CompassPoint.NorthWest(x, y)) == State.ELECTRON_HEAD) {
          adjacent++;
        }
      }

      cell.setAdjacentElectronHeadCount(adjacent);
    }
  }
}
