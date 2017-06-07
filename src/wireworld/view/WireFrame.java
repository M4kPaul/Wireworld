package wireworld.view;

import wireworld.model.Simulator;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Odpowiada za rozmieszczenie komponentów w oknie programu.
 */
public class WireFrame extends JFrame implements Observer {
  private JMenuBar menuBar;
  private JPanel canvas;
  private JPanel gui;

  /**
   * Tworzy i inicjalizuję ramkę okna programu.
   */
  public WireFrame() {
    Simulator.getInstance().addObserver(this);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      ex.getStackTrace();
    }

    menuBar = new WireMenuBar();
    canvas = Simulator.getInstance().getCanvas();
    gui = new WireGui();

    setJMenuBar(menuBar);
    getContentPane().add(gui, BorderLayout.WEST);
    getContentPane().add(canvas, BorderLayout.CENTER);
    pack();

    setLocationRelativeTo(null);
  }

  /**
   * Aktualizuję ramkę, zmieniając planszę wizualizującą siatkę komórek, jeśli obietk obserwowany jest typu Simulator, a
   * argument funkcji typu JPanel.
   * @param o obiekt obserwowany
   * @param arg obiekt będący nową planszą wizualizującą siatkę komórek,
   */
  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof Simulator && arg instanceof JPanel) {
      remove(canvas);

      canvas = (JPanel) arg;

      getContentPane().add(canvas, BorderLayout.CENTER);
      pack();

      setLocationRelativeTo(null);
    }
  }
}
