package wireworld.controller;

import wireworld.model.Cell;
import wireworld.model.Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

/**
 * Obsługuje zdarzenia związane z ruchem myszy.
 */
public class CanvasMouseMotionListener implements MouseMotionListener {
  private wireworld.view.Canvas canvas;
  private int rowCount;
  private int columnCount;
  private Point selectedCell;
  private Color selectedColor;
  private HashMap<Point, Cell> selectedCells;

  /**
   * Tworzy słuchacza obsługującego zdarzenia związane z ruchem myszy.
   */
  public CanvasMouseMotionListener() {
  }

  /**
   *
   */
  private void refreshData() {
    canvas = Simulator.getInstance().getCanvas();
    rowCount = canvas.getRowCount();
    columnCount = canvas.getColumnCount();
    selectedCell = canvas.getSelectedCell();
    selectedColor = canvas.getSelectedColor();
    selectedCells = canvas.getSelectedCells();
  }

  /**
   *
   * @param e zdarzenie odebrane przez słuchacza
   */
  private void updateCell(MouseEvent e) {
    Point point = e.getPoint();

    int width = canvas.getWidth();
    int height = canvas.getHeight();

    int cellWidth = width / columnCount;
    int cellHeight = height / rowCount;

    Point newSelectedCell = null;
    if (point.getX() >= 0 && point.getY() >= 0) {
      int column = (int)point.getX() / cellWidth;
      int row = (int)point.getY() / cellHeight;

      if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
        newSelectedCell = new Point(column, row);
      }
    }

    if (newSelectedCell != null) {
      if (!newSelectedCell.equals(selectedCell)) {
        selectedCell = newSelectedCell;
      }
    } else {
      selectedCell = null;
    }
  }

  /**
   * Metoda wywoływana, gdy słuchacz odbierze zdarzenie przeciągnięcia myszy z wciśniętym przyciskiem.
   * @param e zdarzenie odebrane przez słuchacza
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    refreshData();
    updateCell(e);

    if (selectedCell != null) {
      Cell cell = new Cell(selectedCell, selectedColor);

      if (SwingUtilities.isLeftMouseButton(e)) {
        if (selectedCells.containsKey(selectedCell)) {
          if (!selectedColor.equals(selectedCells.get(selectedCell).getState())) {
            selectedCells.get(selectedCell).setState(selectedColor);
          }
        } else {
          selectedCells.put(selectedCell, cell);
        }
      } else {
        if (selectedCells.containsKey(selectedCell)) {
          selectedCells.remove(selectedCell);
        }
      }

      if (selectedCell != null) {
        canvas.setSelectedCells(selectedCells);
        canvas.setSelectedCell(selectedCell);
        canvas.repaint();
      }
    }
  }

  /**
   *  Metoda wywoływana, gdy słuchacz odbierze zdarzenie poruszenia myszy.
   * @param e zdarzenie odebrane przez słuchacza
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    refreshData();
    updateCell(e);

    if (selectedCell != null) {
      canvas.setSelectedCell(selectedCell);
      canvas.repaint();
    }
  }
}
