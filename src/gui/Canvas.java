package gui;

import cell.Cell;
import cell.State;
import logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Canvas extends JPanel {
  private int columnCount;
  private int rowCount;
  private Logic logic;
  private Timer timer;
  private Point selectedCell;
  private List<Rectangle> cells;
  private HashMap<Point, Cell> selectedCells;
  private Color selectedColor = new Color(255, 214, 0);

  Canvas() {
    columnCount = 32;
    rowCount = 32;
    logic = new Logic();
    cells = new ArrayList<>(columnCount * rowCount);
    selectedCells = new HashMap<>();
    timer = new Timer(300, e -> {
      logic.nextGeneration(this);
      repaint();
    });

    addMouseListener(new CanvasMouseListener());
    addMouseMotionListener(new CanvasMouseMotionListener());

  }

  public HashMap<Point, Cell> getSelectedCells() {
    return selectedCells;
  }

  public int getCellState(Point point) {
    Cell cell = selectedCells.get(point);

    if (cell != null)
      return cell.getState();
    return State.EMPTY;
  }

  void setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
  }

  void startTimer() {
    timer.start();
  }

  void stopTimer() {
    timer.stop();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(512, 512);
  }

  @Override
  public void invalidate() {
    selectedCell = null;
    selectedCells.clear();
    super.invalidate();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g.create();

    int width = getWidth();
    int height = getHeight();

    int cellWidth = width / columnCount;
    int cellHeight = height / rowCount;

    int xOffset = (width - (columnCount * cellWidth)) / 2;
    int yOffset = (height - (rowCount * cellHeight)) / 2;

    if (cells.isEmpty()) {
      for (int row = 0; row < rowCount; row++) {
        for (int col = 0; col < columnCount; col++) {
          Rectangle cell = new Rectangle(xOffset + (col * cellWidth), yOffset + (row * cellHeight), cellWidth, cellHeight);
          cells.add(cell);
        }
      }
    }

    for (Cell cell : selectedCells.values()) {
      int index = cell.getX() + (cell.getY() * columnCount);
      Rectangle rectangleCell = cells.get(index);
      g2d.setColor(cell.getColor());
      g2d.fill(rectangleCell);
    }

    g2d.setColor(Color.GRAY);
    for (Rectangle cell : cells) {
      g2d.draw(cell);
    }

    if (selectedCell != null) {
      int index = selectedCell.x + (selectedCell.y * columnCount);
      Rectangle cell = cells.get(index);
      g2d.setColor(selectedColor);
      g2d.draw(cell);
    }

    g2d.dispose();
  }


  private class CanvasMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      Cell cell = new Cell(selectedCell, selectedColor);

      if (selectedCells.containsKey(selectedCell)) {
        if (selectedColor.equals(selectedCells.get(selectedCell).getColor()))
          selectedCells.remove(selectedCell);
        else
          selectedCells.get(selectedCell).setColor(selectedColor);
      } else {
        selectedCells.put(selectedCell, cell);
      }

      repaint();
    }
  }

  private class CanvasMouseMotionListener implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

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
