package view.wireCanvas;

import model.Cell;
import model.Simulator;
import view.Structures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class CanvasMouseClickListener extends MouseAdapter {
  private Canvas canvas;
  private int rowCount;
  private int columnCount;
  private Point selectedCell;
  private Color selectedColor;
  private String selectedStructureName;
  private HashMap<Point, Cell> selectedCells;
  private HashMap<String, HashMap<Point, Cell>> prebuiltStructures;

  CanvasMouseClickListener() {
    prebuiltStructures = new Structures().getPrebuilt();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    canvas = Simulator.getInstance().getCanvas();
    rowCount = canvas.getRowCount();
    columnCount = canvas.getColumnCount();
    selectedCell = canvas.getSelectedCell();
    selectedColor = canvas.getSelectedColor();
    selectedStructureName = canvas.getStructureName();
    selectedCells = canvas.getSelectedCells();


    Cell cell = new Cell(selectedCell, selectedColor);
    if (selectedStructureName == null) {
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
      HashMap<Point, Cell> selectedStructure = prebuiltStructures.get(selectedStructureName);
      for (Cell c : selectedStructure.values()) {
        Point newPoint = new Point(selectedCell.x + c.getX(), selectedCell.y + c.getY());
        if (newPoint.x >= 0 && newPoint.y >= 0 && newPoint.x < columnCount && newPoint.y < rowCount) {
          if (selectedCells.containsKey(newPoint)) {
            selectedCells.get(newPoint).setState(c.getState());
          } else {
            selectedCells.put(newPoint, new Cell(newPoint, c.getState()));
          }
        }
      }
    }

    canvas.setSelectedCells(selectedCells);
    canvas.repaint();
  }
}
