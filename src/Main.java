import view.WireFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      JFrame frame = new WireFrame();

      frame.setTitle("Wireworld");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setVisible(true);
    });
  }
}
