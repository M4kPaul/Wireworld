package wireworld.controller;

import java.awt.*;

/**
 * Zawiera statyczne metody wyznaczające współrzędne punktów sąsiadujących z danym punktem.
 */

class CompassPoint {

  /**
   * Zwraca punkt znajdujący się na północ od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na północ od danego punktu
   */
  static Point North(int x, int y) {
    return new Point(x, y + 1);
  }

  /**
   * Zwraca punkt znajdujący się na północny-wschód od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na północny-wschód od danego punktu
   */
  static Point NorthEast(int x, int y) {
    return new Point(x + 1, y + 1);
  }

  /**
   * Zwraca punkt znajdujący się na wschód od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na wschód od danego punktu
   */
  static Point East(int x, int y) {
    return new Point(x + 1, y);
  }

  /**
   * Zwraca punkt znajdujący się na południowy-wschód od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na południowy-wschód od danego punktu
   */
  static Point SouthEast(int x, int y) {
    return new Point(x + 1, y - 1);
  }

  /**
   * Zwraca punkt znajdujący się na południe od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na południe od danego punktu
   */
  static Point South(int x, int y) {
    return new Point(x, y - 1);
  }

  /**
   * Zwraca punkt znajdujący się na południowy-zachód od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na południowy-zachód od danego punktu
   */
  static Point SouthWest(int x, int y) {
    return new Point(x - 1, y - 1);
  }

  /**
   * Zwraca punkt znajdujący się na zachód od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na zachód od danego punktu
   */
  static Point West(int x, int y) {
    return new Point(x - 1, y);
  }

  /**
   * Zwraca punkt znajdujący się na północny-zachód od danego punktu.
   * @param x współrzędna X danego punktu
   * @param y współrzędna Y danego punktu
   * @return punktu znajdujący się na północny-zachód od danego punktu
   */
  static Point NorthWest(int x, int y) {
    return new Point(x - 1, y + 1);
  }
}
