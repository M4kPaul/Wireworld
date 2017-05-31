package view;

import model.Simulator;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class WireFrame extends JFrame implements Observer {
  private JMenuBar menuBar;
  private JPanel canvas;
  private JPanel gui;

  public WireFrame() {
    Simulator.getInstance().addObserver(this);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException ex) {
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

  @Override
  public void update(Observable o, Object arg) {
    if (o instanceof Simulator && arg instanceof JPanel) {
      remove(canvas);

      canvas = Simulator.getInstance().getCanvas();

      getContentPane().add(canvas, BorderLayout.CENTER);
      pack();

      setLocationRelativeTo(null);
    }
  }
}
