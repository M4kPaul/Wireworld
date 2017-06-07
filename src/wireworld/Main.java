package wireworld;

import wireworld.view.WireFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa główna programu
 */
class Main {
  /**
   * Odpowiada za wykonanie instrukcji w wątku dystrybucji zdarzeń.
   *
   * @param args tablica argumentów wywołania programu
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      JFrame frame = new WireFrame();

      frame.setTitle("Wireworld");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setVisible(true);
    });
  }
}
