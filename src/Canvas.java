import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

@SuppressWarnings("SuspiciousMethodCalls")
class Canvas extends JPanel{
  private final int columnCount = 10;
  private final int rowCount = 10;
  private final List<Rectangle> cells;
  private Point selectedCell;
  private final List<ColorCell> selectedCells;
  private Color selectedColor = new Color(255, 214, 0);

  Canvas() {
    cells = new ArrayList<>(columnCount * rowCount);
    selectedCells = new ArrayList<>(columnCount * rowCount);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (selectedCells.contains(selectedCell)) {
          selectedCells.remove(selectedCell);
        } else {
          selectedCells.add(new ColorCell(selectedCell, selectedColor));
        }
        repaint();
      }
    });

    addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / columnCount;
        int cellHeight = height / rowCount;

        selectedCell = null;
        if (point.x >= 0 && point.y >= 0) {
          int column = point.x / cellWidth;
          int row = point.y / cellHeight;

          if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
            selectedCell = new Point(column, row);
          }
        }
        repaint();
      }
    });
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(256, 256);
  }

  @Override
  public void invalidate() {
    cells.clear();
    selectedCell = null;
    selectedCells.clear();
    super.invalidate();
  }

  void setSelectedColor(Color selectedColor) {
    this.selectedColor = selectedColor;
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

    for (ColorCell colorCell : selectedCells) {
      int index = colorCell.getPoint().x + (colorCell.getPoint().y * columnCount);
      Rectangle cell = cells.get(index);
      g2d.setColor(colorCell.getColor());
      g2d.fill(cell);
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
}
