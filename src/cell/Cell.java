package cell;

import java.awt.*;

public class Cell {
  private final Point point;
  private Color color;
  private int state;
  private int adjacentElectronHeadCount;

  public Cell(Point point, int state) {
    this.point = point;
    this.state = state;

    matchColor(state);
  }

  public Cell(Point point, Color color) {
    this.point = point;
    this.color = color;

    matchState(color);
  }

  public int getX() {
    return (int) point.getX();
  }

  public int getY() {
    return (int) point.getY();
  }

  private Point getPoint() {
    return point;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;

    matchState(color);
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;

    matchColor(state);
  }

  public int getAdjacentElectronHeadCount() {
    return adjacentElectronHeadCount;
  }

  public void setAdjacentElectronHeadCount(int adjacentElectronHeadCount) {
    this.adjacentElectronHeadCount = adjacentElectronHeadCount;
  }

  private void matchColor(int state) {
    switch (state) {
      case State.EMPTY:
        color = new Color(0, 0, 0);
        break;
      case State.ELECTRON_HEAD:
        color = new Color(0, 127, 255);
        break;
      case State.ELECTRON_TAIL:
        color = new Color(255, 64, 0);
        break;
      case State.CONDUCTOR:
        color = new Color(255, 214, 0);
        break;
    }
  }

  private void matchState(Color color) {
    if (color.equals(new Color(0, 0, 0)))
      state = State.EMPTY;
    else if (color.equals(new Color(0, 127, 255)))
      state = State.ELECTRON_HEAD;
    else if (color.equals(new Color(255, 64, 0)))
      state = State.ELECTRON_TAIL;
    else if (color.equals(new Color(255, 214, 0)))
      state = State.CONDUCTOR;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Cell && point.equals(((Cell) obj).getPoint());
  }
}
