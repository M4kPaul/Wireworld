package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JPanel {
  private JPanel mainPanel;
  private JButton wireButton;
  private JButton headButton;
  private JButton tailButton;
  private JPanel canvas;
  private JButton startButton;
  private JButton stopButton;
  private JButton cleanButton;

  public Gui() {
    ActionListener colorSelect = (ActionEvent e) -> ((Canvas)canvas).setSelectedColor(((JButton)e.getSource()).getBackground());
    ActionListener startButtonClicked = (ActionEvent e) -> ((Canvas)canvas).startTimer();
    ActionListener stopButtonClicked = (ActionEvent e) -> ((Canvas)canvas).stopTimer();

    wireButton.addActionListener(colorSelect);
    headButton.addActionListener(colorSelect);
    tailButton.addActionListener(colorSelect);
    startButton.addActionListener(startButtonClicked);
    stopButton.addActionListener(stopButtonClicked);
    cleanButton.addActionListener((ActionEvent e) -> canvas.invalidate());

    add(mainPanel);
  }

  private void createUIComponents() {
    canvas = new Canvas();
  }
}
