import java.awt.*;
import java.io.Serializable;

public class Cell implements Serializable {
  private final Point point;
  private Color state;
  private int adjacentElectronHeadCount;

  public Cell(Point point, Color state) {
    this.point = point;
    this.state = state;
  }

  private Point getPoint() {
    return point;
  }

  public int getX() {
    return (int)point.getX();
  }

  public int getY() {
    return (int)point.getY();
  }

  public Color getState() {
    return state;
  }

  public void setState(Color state) {
    this.state = state;
  }

  public int getAdjacentElectronHeadCount() {
    return adjacentElectronHeadCount;
  }

  public void setAdjacentElectronHeadCount(int adjacentElectronHeadCount) {
    this.adjacentElectronHeadCount = adjacentElectronHeadCount;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Cell && point.equals(((Cell)obj).getPoint());
  }
}
