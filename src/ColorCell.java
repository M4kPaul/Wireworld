import java.awt.Color;
import java.awt.Point;

class ColorCell {
  private final Point point;
  private Color color;

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

  void setColor(Color color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof ColorCell && point.equals(((ColorCell)o).getPoint());
  }
}
