import java.awt.Color;
import java.awt.Point;

class ColorCell {
  private final Point point;
  private final Color color;

  ColorCell(Point point, Color color) {
    this.point = point;
    this.color = color;
  }

  Point getPoint() {
    return point;
  }

  Color getColor() {
    return color;
  }
}
