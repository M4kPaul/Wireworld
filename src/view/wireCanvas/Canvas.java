package view.wireCanvas;

import model.Cell;
import model.Grid;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Canvas extends JPanel {
  private int rowCount;
  private int columnCount;
  private Dimension size;
  private String structureName;
  private Point selectedCell;
  private Color selectedColor;
  private List<Rectangle> cells;
  private HashMap<Point, Cell> selectedCells;

  public Canvas(Grid grid) {
    rowCount = grid.getRowCount();
    columnCount = grid.getColumnCount();
    size = new Dimension(rowCount * 20, columnCount * 20);

    setSelectedCell(null);
    setSelectedColor(State.CONDUCTOR);

    cells = new ArrayList<>();
    setSelectedCells(grid.getSelectedCells());

    addMouseListener(new CanvasMouseClickListener());
    addMouseMotionListener(new CanvasMouseMotionListener());

    setBackground(Color.BLACK);
    repaint();
  }

  public void clear() {
    getSelectedCells().clear();
    repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return size;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D)g;

    int width = getWidth();
    int height = getHeight();

    int cellWidth = width / getColumnCount();
    int cellHeight = height / getRowCount();

    int xOffset = (width - (getColumnCount() * cellWidth)) / 2;
    int yOffset = (height - (getRowCount() * cellHeight)) / 2;

    if (cells.isEmpty()) {
      for (int row = 0; row < getRowCount(); row++) {
        for (int col = 0; col < getColumnCount(); col++) {
          Rectangle cell = new Rectangle(xOffset + (col * cellWidth), yOffset + (row * cellHeight), cellWidth, cellHeight);
          cells.add(cell);
        }
      }
    }

    for (Cell cell : getSelectedCells().values()) {
      int index = cell.getX() + (cell.getY() * getColumnCount());
      Rectangle rectangleCell = cells.get(index);
      g2D.setColor(cell.getState());
      g2D.fill(rectangleCell);
    }

    g2D.setColor(Color.GRAY);
    for (Rectangle cell : cells) {
      g2D.draw(cell);
    }

    if (getSelectedCell() != null) {
      int index = getSelectedCell().x + (getSelectedCell().y * getColumnCount());
      Rectangle cell = cells.get(index);
      g2D.setColor(getSelectedColor());
      g2D.draw(cell);
    }

    g2D.dispose();
  }

  Point getSelectedCell() {
    return selectedCell;
  }

  void setSelectedCell(Point selectedCell) {
    this.selectedCell = selectedCell;
  }

  Color getSelectedColor() {
    return selectedColor;
  }

  public void setSelectedColor(Color color) {
    selectedColor = color;
  }

  HashMap<Point, Cell> getSelectedCells() {
    return selectedCells;
  }

  void setSelectedCells(HashMap<Point, Cell> selectedCells) {
    this.selectedCells = selectedCells;
  }

  int getRowCount() {
    return rowCount;
  }

  int getColumnCount() {
    return columnCount;
  }

  String getStructureName() {
    return structureName;
  }

  public void setStructureName(String structureName) {
    this.structureName = structureName;
  }
}
