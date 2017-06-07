package wireworld.view;

import wireworld.model.Cell;
import wireworld.model.State;

import java.awt.*;
import java.util.HashMap;

// The dumbest idea ever, yet it works and there is no more time; deadline in 30 minutes.

public class Structures implements State {
  private HashMap<Point, Cell> diode = new HashMap<Point, Cell>() {{
    put(new Point(7, 0), new Cell(new Point(7, 0), State.CONDUCTOR));
    put(new Point(8, 0), new Cell(new Point(6, 0), State.CONDUCTOR));
    put(new Point(0, 1), new Cell(new Point(0, 1), State.ELECTRON_HEAD));
    put(new Point(1, 1), new Cell(new Point(1, 1), State.CONDUCTOR));
    put(new Point(2, 1), new Cell(new Point(2, 1), State.CONDUCTOR));
    put(new Point(3, 1), new Cell(new Point(3, 1), State.CONDUCTOR));
    put(new Point(4, 1), new Cell(new Point(4, 1), State.CONDUCTOR));
    put(new Point(5, 1), new Cell(new Point(5, 1), State.CONDUCTOR));
    put(new Point(6, 1), new Cell(new Point(6, 1), State.CONDUCTOR));
    put(new Point(8, 1), new Cell(new Point(8, 1), State.CONDUCTOR));
    put(new Point(9, 1), new Cell(new Point(9, 1), State.CONDUCTOR));
    put(new Point(10, 1), new Cell(new Point(10, 1), State.CONDUCTOR));
    put(new Point(11, 1), new Cell(new Point(11, 1), State.CONDUCTOR));
    put(new Point(12, 1), new Cell(new Point(12, 1), State.CONDUCTOR));
    put(new Point(13, 1), new Cell(new Point(13, 1), State.CONDUCTOR));
    put(new Point(7, 2), new Cell(new Point(7, 2), State.CONDUCTOR));
    put(new Point(6, 2), new Cell(new Point(6, 2), State.CONDUCTOR));

    put(new Point(7, 5), new Cell(new Point(7, 5), State.CONDUCTOR));
    put(new Point(8, 5), new Cell(new Point(6, 5), State.CONDUCTOR));
    put(new Point(0, 6), new Cell(new Point(0, 6), State.ELECTRON_HEAD));
    put(new Point(1, 6), new Cell(new Point(1, 6), State.CONDUCTOR));
    put(new Point(2, 6), new Cell(new Point(2, 6), State.CONDUCTOR));
    put(new Point(3, 6), new Cell(new Point(3, 6), State.CONDUCTOR));
    put(new Point(4, 6), new Cell(new Point(4, 6), State.CONDUCTOR));
    put(new Point(5, 6), new Cell(new Point(5, 6), State.CONDUCTOR));
    put(new Point(7, 6), new Cell(new Point(7, 6), State.CONDUCTOR));
    put(new Point(8, 6), new Cell(new Point(8, 6), State.CONDUCTOR));
    put(new Point(9, 6), new Cell(new Point(9, 6), State.CONDUCTOR));
    put(new Point(10, 6), new Cell(new Point(10, 6), State.CONDUCTOR));
    put(new Point(11, 6), new Cell(new Point(11, 6), State.CONDUCTOR));
    put(new Point(12, 6), new Cell(new Point(12, 6), State.CONDUCTOR));
    put(new Point(13, 6), new Cell(new Point(13, 6), State.CONDUCTOR));
    put(new Point(7, 7), new Cell(new Point(7, 7), State.CONDUCTOR));
    put(new Point(8, 7), new Cell(new Point(6, 7), State.CONDUCTOR));
  }};

  private HashMap<Point, Cell> xor = new HashMap<Point, Cell>() {{
    put(new Point(0, 0), new Cell(new Point(0, 0), State.ELECTRON_HEAD));
    put(new Point(1, 0), new Cell(new Point(1, 0), State.CONDUCTOR));
    put(new Point(2, 0), new Cell(new Point(2, 0), State.CONDUCTOR));
    put(new Point(3, 0), new Cell(new Point(3, 0), State.CONDUCTOR));
    put(new Point(4, 0), new Cell(new Point(4, 0), State.CONDUCTOR));
    put(new Point(5, 0), new Cell(new Point(5, 0), State.CONDUCTOR));
    put(new Point(6, 1), new Cell(new Point(6, 1), State.CONDUCTOR));
    put(new Point(5, 2), new Cell(new Point(5, 2), State.CONDUCTOR));
    put(new Point(6, 2), new Cell(new Point(6, 2), State.CONDUCTOR));
    put(new Point(7, 2), new Cell(new Point(7, 2), State.CONDUCTOR));
    put(new Point(8, 2), new Cell(new Point(8, 2), State.CONDUCTOR));
    put(new Point(5, 3), new Cell(new Point(5, 3), State.CONDUCTOR));
    put(new Point(8, 3), new Cell(new Point(8, 3), State.CONDUCTOR));
    put(new Point(9, 3), new Cell(new Point(9, 3), State.CONDUCTOR));
    put(new Point(10, 3), new Cell(new Point(10, 3), State.CONDUCTOR));
    put(new Point(11, 3), new Cell(new Point(11, 3), State.CONDUCTOR));
    put(new Point(12, 3), new Cell(new Point(12, 3), State.CONDUCTOR));
    put(new Point(5, 4), new Cell(new Point(5, 4), State.CONDUCTOR));
    put(new Point(6, 4), new Cell(new Point(6, 4), State.CONDUCTOR));
    put(new Point(7, 4), new Cell(new Point(7, 4), State.CONDUCTOR));
    put(new Point(8, 4), new Cell(new Point(8, 4), State.CONDUCTOR));
    put(new Point(6, 5), new Cell(new Point(6, 5), State.CONDUCTOR));
    put(new Point(0, 6), new Cell(new Point(0, 6), State.ELECTRON_HEAD));
    put(new Point(1, 6), new Cell(new Point(1, 6), State.CONDUCTOR));
    put(new Point(2, 6), new Cell(new Point(2, 6), State.CONDUCTOR));
    put(new Point(3, 6), new Cell(new Point(3, 6), State.CONDUCTOR));
    put(new Point(4, 6), new Cell(new Point(4, 6), State.CONDUCTOR));
    put(new Point(5, 6), new Cell(new Point(5, 6), State.CONDUCTOR));
  }};
  private HashMap<String, HashMap<Point, Cell>> prebuilt = new HashMap<>();

  public Structures() {
    getPrebuilt().put("Diode", diode);
    getPrebuilt().put("Xor", xor);
  }

  public HashMap<String, HashMap<Point, Cell>> getPrebuilt() {
    return prebuilt;
  }
}
