import javax.swing.*;


public class Simulator {
  private static Simulator instance;
  private Grid grid;
  private Canvas canvas;
  private Logic logic;
  private Timer timer;

  private Simulator() {
    grid = new Grid();
    logic = new Logic();
    canvas = new Canvas(grid);
    timer = new Timer(550, e -> {
      logic.nextGeneration(grid);
      canvas.repaint();
    });
  }

  public static Simulator getInstance() {
    if (instance == null) {
      synchronized (Simulator.class) {
        instance = new Simulator();
      }
    }

    return instance;
  }

  public Grid getGrid() {
    return grid;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
    timer = new Timer(550, e -> {
      logic.nextGeneration(grid);
      canvas.repaint();
    });
    canvas.update();
  }

  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  public void setTimerDelay(int delay) {
    timer.setDelay(delay);
  }

  public void setSize(int columnCount, int rowCount) {
    grid.setColumnCount(columnCount);
    grid.setRowCount(rowCount);
    canvas.update();
  }

  public void start() {
    timer.start();
  }

  public void stop() {
    timer.stop();
  }
}
