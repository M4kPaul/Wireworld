import javax.swing.*;
import java.awt.*;

class SwingConsole {
  static void run(final JFrame f, final int width, final int height) {
    SwingUtilities.invokeLater(() -> {
      f.setTitle(f.getClass().getSimpleName());
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      f.setMinimumSize(new Dimension(width, height));
      f.setVisible(true);
    });
  }
}