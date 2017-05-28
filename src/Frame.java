import javax.swing.*;

public class Frame extends JFrame {
  private MenuBar menuBar;
  private Gui gui;

  public Frame() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      ex.getStackTrace();
    }

    menuBar = new MenuBar();
    gui = Gui.getInstance();

    setJMenuBar(menuBar);
    getContentPane().add(gui);

    pack();
  }

}
