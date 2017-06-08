package wireworld.view;

import wireworld.controller.CanvasMouseClickListener;
import wireworld.controller.CanvasMouseMotionListener;
import wireworld.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Plansza wizualizująca siatkę komórek w oknie programu.
 *
 * @see javax.swing.JPanel
 */
public class Canvas extends JPanel {
  private int rowCount;
  private int columnCount;
  private Dimension size;
  private Point selectedCell;
  private Color selectedColor;
  private List<Rectangle> cells;
  private HashMap<Point, Cell> selectedCells;

  /**
   * Tworzy i inicjalizuje planszę dla danej siatki komórek.
   *
   * @param grid dana siatka komórek
   * @param firstInit stan inicjalizacji rozmiaru okna
   */
  public Canvas(Grid grid, boolean firstInit) {
    rowCount = grid.getRowCount();
    columnCount = grid.getColumnCount();

    if (firstInit) {
      size = new Dimension(rowCount * 20, columnCount * 20);
    } else {
      size = Simulator.getInstance().getCanvas().getSize();
    }

    setSelectedCell(null);
    setSelectedColor(State.CONDUCTOR);

    cells = new ArrayList<>();
    setSelectedCells(grid.getSelectedCells());

    addMouseListener(new CanvasMouseClickListener());
    addMouseMotionListener(new CanvasMouseMotionListener());

    setBackground(Color.BLACK);
    repaint();
  }

  /**
   * Zmienia stan wszystkich komórek na pusty.
   */
  void clear() {
    getSelectedCells().clear();
    repaint();
  }

  /**
   * Zwraca preferowany rozmiar planszy.
   *
   * @return preferowany rozmiar planszy
   */
  @Override
  public Dimension getPreferredSize() {
    return size;
  }

  /**
   * Rysuje obecny stan planszy
   *
   * @param g obiekt graficzny
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D)g;

    int width = getWidth();
    int height = getHeight();

    int cellWidth = width / columnCount;
    int cellHeight = height / rowCount;

    cells.clear();
    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < columnCount; col++) {
        Rectangle cell = new Rectangle(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
        cells.add(cell);
      }
    }

    for (Cell cell : getSelectedCells().values()) {
      if (cell.getX() < columnCount && cell.getY() < rowCount) {
        int index = cell.getX() + (cell.getY() * columnCount);
        Rectangle rectangleCell = cells.get(index);
        g2D.setColor(cell.getState());
        g2D.fill(rectangleCell);
      }
    }

    g2D.setColor(Color.GRAY);
    for (Rectangle cell : cells) {
      g2D.draw(cell);
    }

    if (selectedCell != null) {
      if (Simulator.getInstance().getSelectedCircuitName() == null) {
        int index = selectedCell.x + (selectedCell.y * columnCount);
        Rectangle cell = cells.get(index);
        g2D.setColor(selectedColor);
        g2D.draw(cell);
      } else {
        HashMap<Point, Cell> selectedStructure = Circuits.getPrebuilt().get(Simulator.getInstance().getSelectedCircuitName());
        selectedStructure.forEach((k, v) -> {
          Point newPoint = new Point(selectedCell.x + v.getX(), selectedCell.y + v.getY());
          if (newPoint.x >= 0 && newPoint.y >= 0 && newPoint.x < columnCount && newPoint.y < rowCount) {
            int index = newPoint.x + (newPoint.y * columnCount);
            Rectangle cell = cells.get(index);
            g2D.setColor(v.getState());
            g2D.draw(cell);
          }
        });
      }
    }

    g2D.dispose();
  }

  /**
   * Zwraca punkt określający położenie wskazanej przez kursor komórki.
   *
   * @return punkt określający położenie wskazanej przez kursor komórki
   */
  public Point getSelectedCell() {
    return selectedCell;
  }

  /**
   * Ustawia punkt określejący położenie wskazanej przez kursor komórki.
   *
   * @param selectedCell nowy punkt wskazujący położenie wskazanej przez kursor komórki
   */
  public void setSelectedCell(Point selectedCell) {
    this.selectedCell = selectedCell;
  }

  /**
   * Zwraca obecnie wybrany kolor do rysowania.
   *
   * @return obecnie wybrany kolor do rysowania
   */
  public Color getSelectedColor() {
    return selectedColor;
  }

  /**
   * Ustawia nowo wybrany kolor do rysowania.
   *
   * @param color nowo wybrany kolor do rysowania
   */
  void setSelectedColor(Color color) {
    selectedColor = color;
  }

  /**
   * Zwraca komórki na planszy niebędące w stanie pustym.
   *
   * @return tablica z hashowaniem zawierająca komórki na planszy niebędące w stanie pustym
   */
  public HashMap<Point, Cell> getSelectedCells() {
    return selectedCells;
  }

  /**
   * Ustawia nową tablicę z haszowaniem zawierającą komórki na planszy niebędące w stanie pustym.
   *
   * @param selectedCells nowa tablica z haszowaniem zawierająca komórki na planszy niebędące w stanie pustym.
   */
  public void setSelectedCells(HashMap<Point, Cell> selectedCells) {
    this.selectedCells = selectedCells;
  }

  /**
   * Zwraca liczbę wierszy danej planszy.
   *
   * @return liczba wierszy danej planszy
   */
  public int getRowCount() {
    return rowCount;
  }

  /**
   * Zwraca liczbę kolumn danej planszy.
   *
   * @return liczba kolumn danej planszy
   */
  public int getColumnCount() {
    return columnCount;
  }
}
