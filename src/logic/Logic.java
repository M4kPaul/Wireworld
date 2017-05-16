package logic;

import cell.Cell;
import cell.State;
import gui.Canvas;

public class Logic {

  public void nextGeneration(Canvas canvas) {
    countAdjacentElectronHead(canvas);

    for (Cell cell : canvas.getSelectedCells().values()) {
      switch (cell.getState()) {
        case State.ELECTRON_HEAD:
          cell.setState(State.ELECTRON_TAIL);
          break;
        case State.ELECTRON_TAIL:
          cell.setState(State.CONDUCTOR);
          break;
        case State.CONDUCTOR:
          if (cell.getAdjacentElectronHeadCount() == 1 || cell.getAdjacentElectronHeadCount() == 2) {
            cell.setState(State.ELECTRON_HEAD);
          }
          break;
      }
    }
  }

  private void countAdjacentElectronHead(Canvas canvas) {
    for (Cell cell : canvas.getSelectedCells().values()) {
      int x = cell.getX();
      int y = cell.getY();
      int tmp = 0;

      if (cell.getState() == State.CONDUCTOR) {
        if (canvas.getCellState(CompassPoint.North(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.NorthEast(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.East(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.SouthEast(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.South(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.SouthWest(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.West(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
        if (canvas.getCellState(CompassPoint.NorthWest(x, y)) == State.ELECTRON_HEAD) {
          tmp++;
        }
      }

      cell.setAdjacentElectronHeadCount(tmp);
    }
  }

}
