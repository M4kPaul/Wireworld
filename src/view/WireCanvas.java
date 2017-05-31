package view;

import model.Cell;
import model.Grid;
import model.Simulator;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

public class WireCanvas extends JPanel{
  private int DEFAULT_CELL_WIDTH = 13;
  private int DEFAULT_CELL_HEIGHT = 13;

  private int rowCount;
  private int columnCount;
  private Dimension size;
  private Point selectedCell;
  private Color selectedColor;
  private List<Rectangle> cells;
  private HashMap<Point, Cell> selectedCells;

  public WireCanvas(Grid grid) {
    rowCount = grid.getRowCount();
    columnCount = grid.getColumnCount();
    size = new Dimension(columnCount * DEFAULT_CELL_WIDTH, rowCount * DEFAULT_CELL_HEIGHT );

    selectedCell = null;
    selectedColor = State.CONDUCTOR;

    cells = new ArrayList<>();
    selectedCells = grid.getSelectedCells();

    addMouseListener(new WireMouseListener());
    addMouseMotionListener(new WireMouseMotionListener());

    setBackground(Color.BLACK);
    repaint();
  }

  public void setSelectedColor(Color color) {
    selectedColor = color;
  }

  public void clear() {
    selectedCells.clear();
    repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    return size;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D) g;

    int width = getWidth();
    int height = getHeight();

    int cellWidth = width / columnCount;
    int cellHeight =  height / rowCount;

    int xOffset = (width - (columnCount * cellWidth)) / 2;
    int yOffset = (height - (rowCount * cellHeight)) / 2;

    if (cells.isEmpty()) {
      for (int row = 0; row < rowCount; row++) {
        for (int col = 0; col < columnCount; col++) {
          Rectangle cell = new Rectangle(xOffset + (col * cellWidth),
                  yOffset + (row * cellHeight), cellWidth, cellHeight);
          cells.add(cell);
        }
      }
    }

    for (Cell cell : selectedCells.values()) {
      int index = cell.getX() + (cell.getY() * columnCount);
      Rectangle rectangleCell = cells.get(index);
      g2D.setColor(cell.getState());
      g2D.fill(rectangleCell);
    }

    g2D.setColor(Color.GRAY);
    for (Rectangle cell : cells) {
      g2D.draw(cell);
    }

    if (selectedCell != null) {
      int index = selectedCell.x + (selectedCell.y * columnCount);
      Rectangle cell = cells.get(index);
      g2D.setColor(selectedColor);
      g2D.draw(cell);
    }

    g2D.dispose();
  }

  private class WireMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      Cell cell = new Cell(selectedCell, selectedColor);

      if (selectedCells.containsKey(selectedCell)) {
        if (selectedColor.equals(selectedCells.get(selectedCell).getState())) {
          selectedCells.remove(selectedCell);
        } else {
          selectedCells.get(selectedCell).setState(selectedColor);
        }
      } else {
        selectedCells.put(selectedCell, cell);
      }

      repaint();
    }
  }

  private class WireMouseMotionListener implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {
      Point point = e.getPoint();

      int width = getWidth();
      int height = getHeight();

      int cellWidth = width / columnCount;
      int cellHeight = height / rowCount;

      selectedCell = null;
      if (point.getX() >= 0 && point.getY() >= 0) {
        int column = (int) point.getX() / cellWidth;
        int row = (int) point.getY() / cellHeight;

        if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
          selectedCell = new Point(column, row);
        }
      }

      if (selectedCell != null) {
        Cell cell = new Cell(selectedCell, selectedColor);

        if (selectedCells.containsKey(selectedCell)) {
          if (!selectedColor.equals(selectedCells.get(selectedCell).getState())) {
            selectedCells.get(selectedCell).setState(selectedColor);
          }
        } else {
          selectedCells.put(selectedCell, cell);
        }

        repaint();
      }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      Point point = e.getPoint();

      int width = getWidth();
      int height = getHeight();

      int cellWidth = width / columnCount;
      int cellHeight = height / rowCount;

      selectedCell = null;
      if (point.getX() >= 0 && point.getY() >= 0) {
        int column = (int) point.getX() / cellWidth;
        int row = (int) point.getY() / cellHeight;

        if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
          selectedCell = new Point(column, row);
        }
      }

      repaint();
    }
  }
}
