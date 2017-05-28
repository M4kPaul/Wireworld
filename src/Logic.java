public class Logic {

  void nextGeneration(Grid grid) {
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

  private void countAdjacentElectronHead(Grid grid) {
    for (Cell cell : grid.getSelectedCells().values()) {
      int x = cell.getX();
      int y = cell.getY();
      int tmp = 0;

      if (cell.getState().equals(State.CONDUCTOR)) {
        if (grid.getCellState(CompassPoint.North(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.NorthEast(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.East(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.SouthEast(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.South(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.SouthWest(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.West(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (grid.getCellState(CompassPoint.NorthWest(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
      }

      cell.setAdjacentElectronHeadCount(tmp);
    }
  }

}
