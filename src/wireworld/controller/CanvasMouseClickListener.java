package wireworld.controller;

import wireworld.circuits.Circuits;
import wireworld.model.Cell;
import wireworld.model.Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Obsługuje zdarzenia związane z naciśnięciem przycisku myszy.
 */
public class CanvasMouseClickListener extends MouseAdapter {
  private wireworld.view.Canvas canvas;
  private int rowCount;
  private int columnCount;
  private Point selectedCell;
  private Color selectedColor;
  private String selectedCircuitName;
  private HashMap<Point, Cell> selectedCells;

  /**
   * Metoda wywoływana, gdy słuchacz odbierze zdarzenie naciśnięcia przycisku myszy.
   *
   * @param e zdarzenie odebrane przez słuchacza
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    canvas = Simulator.getInstance().getCanvas();
    rowCount = canvas.getRowCount();
    columnCount = canvas.getColumnCount();
    selectedCell = canvas.getSelectedCell();
    selectedColor = canvas.getSelectedColor();
    selectedCircuitName = Simulator.getInstance().getSelectedCircuitName();
    selectedCells = canvas.getSelectedCells();


    Cell cell = new Cell(selectedCell, selectedColor);
    if (selectedCircuitName == null) {
      if (SwingUtilities.isLeftMouseButton(e)) {
        if (selectedCells.containsKey(selectedCell)) {
          if (selectedColor.equals(selectedCells.get(selectedCell).getState())) {
            selectedCells.remove(selectedCell);
          } else {
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
    } else {
      HashMap<Point, Cell> selectedStructure = Circuits.getPrebuilt().get(selectedCircuitName);
      selectedStructure.forEach((k, v) -> {
        Point newPoint = new Point(selectedCell.x + v.getX(), selectedCell.y + v.getY());
        if (newPoint.x >= 0 && newPoint.y >= 0 && newPoint.x < columnCount && newPoint.y < rowCount) {
          if (selectedCells.containsKey(newPoint)) {
            selectedCells.get(newPoint).setState(v.getState());
          } else {
            selectedCells.put(newPoint, new Cell(newPoint, v.getState()));
          }
        }
      });
    }

    canvas.setSelectedCells(selectedCells);
    canvas.repaint();
  }
}
