import java.awt.*;

public class CompassPoint {

  static Point North(int x, int y) {
    return new Point(x, y + 1);
  }

  static Point NorthEast(int x, int y) {
    return new Point(x + 1, y + 1);
  }

  static Point East(int x, int y) {
    return new Point(x + 1, y);
  }

  static Point SouthEast(int x, int y) {
    return new Point(x + 1, y - 1);
  }

  static Point South(int x, int y) {
    return new Point(x, y - 1);
  }

  static Point SouthWest(int x, int y) {
    return new Point(x - 1, y - 1);
  }

  static Point West(int x, int y) {
    return new Point(x - 1, y);
  }

  static Point NorthWest(int x, int y) {
    return new Point(x - 1, y + 1);
  }
}
