package wireworld.model;

import java.awt.*;
import java.io.Serializable;

/**
 * Zawiera informacje o położeniu, stanie i sąsiadujących komórkach danej komórki.
 */
public class Cell implements Serializable {
  private final Point point;
  private Color state;
  private int adjacentElectronHeadCount;

  /**
   * Tworzy i inicjalizuje komórkę w określonym przez punkt położeniu z podanym stanem.
   * @param point punkt określający położenie danej komórki
   * @param state stan danej komórki
   */
  public Cell(Point point, Color state) {
    this.point = point;
    this.state = state;
  }

  /**
   * Zwraca położenie danej komórki określone za pomocą punktu.
   * @return punkt określający położenie danej komórki.
   */
  private Point getPoint() {
    return point;
  }

  /**
   * Zwraca współrzędną X położenia danej komórki.
   * @return współrzędna X położenia danej komórki
   */
  public int getX() {
    return (int)point.getX();
  }

  /**
   * Zwraca współrzędną Y położenia danej komórki.
   * @return współrzędna Y położenia danej komórki
   */
  public int getY() {
    return (int)point.getY();
  }

  /**
   * Zwraca stan danej komórki.
   * @return stan danej komórki
   */
  public Color getState() {
    return state;
  }

  /**
   * Ustawia stan danej komórki.
   * @param state nowy stan danej komórki.
   */
  public void setState(Color state) {
    this.state = state;
  }

  /**
   * Zwraca liczbę sąsiadów danej komórki będących głowami elektronów.
   * @return liczba sąsiadów danej komórki będących głowami elektronów
   */
  public int getAdjacentElectronHeadCount() {
    return adjacentElectronHeadCount;
  }

  /**
   * Ustawia liczbę sąsiadów danej komórki będących głowami elektronów.
   * @param adjacentElectronHeadCount nowa liczba sąsiadów danej komórki będących głowami elektronów
   */
  public void setAdjacentElectronHeadCount(int adjacentElectronHeadCount) {
    this.adjacentElectronHeadCount = adjacentElectronHeadCount;
  }

  /**
   * Zwraca {@code hashcode} danej komórki wyznaczony z jej położenia.
   * @return {@code hashcode} danej komórki wyznaczony z jej położenia
   */
  @Override
  public int hashCode() {
    return point.hashCode();
  }

  /**
   * Określa, czy dwie komórki są równe porównując ich położenie.
   * @param obj obiekt porównywany z daną komórką
   * @return {@code true} jeśli obiekt, porównywany z daną komórką, jest typu {@code Cell} i ma jednakowe położenie;
   * {@code false} w przeciwnym wypadku
   */
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Cell && point.equals(((Cell)obj).getPoint());
  }
}
