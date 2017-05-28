import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JPanel {
  private static Gui instance;
  private JPanel mainPanel;
  private JPanel canvasPanel;
  private JPanel controlPanel;
  private JPanel drawPanel;
  private JPanel runPanel;
  private JPanel emptyPanel;
  private JRadioButton conductorRadioButton;
  private JRadioButton electronHeadRadioButton;
  private JRadioButton electronTailRadioButton;
  private JButton cleanButton;
  private JButton startButton;
  private JButton stopButton;
  private JButton prevButton;
  private JButton nextButton;
  private JSlider speedSlider;
  private JLabel electronTailLabel;
  private JLabel electronHeadLabel;
  private JLabel conductorLabel;
  private Simulator simulator;

  private Gui() {
    electronHeadLabel.setIcon(new ImageIcon("electron_head.png"));
    electronTailLabel.setIcon(new ImageIcon("electron_tail.png"));
    conductorLabel.setIcon(new ImageIcon("conductor.png"));

    electronHeadRadioButton.addActionListener(e -> ((Canvas) canvasPanel).setSelectedColor(State.ELECTRON_HEAD));
    electronTailRadioButton.addActionListener(e -> ((Canvas) canvasPanel).setSelectedColor(State.ELECTRON_TAIL));
    conductorRadioButton.addActionListener(e -> ((Canvas) canvasPanel).setSelectedColor(State.CONDUCTOR));
    cleanButton.addActionListener(e -> canvasPanel.invalidate());
    startButton.addActionListener(e -> simulator.start());
    stopButton.addActionListener(e -> simulator.stop());
    prevButton.addActionListener(e -> prevButton.doClick()); // TODO
    nextButton.addActionListener(e -> nextButton.doClick()); // TODO
    speedSlider.addChangeListener(e -> simulator.setTimerDelay(1200 - speedSlider.getValue()));

    add(mainPanel);
  }

  public static Gui getInstance() {
    if (instance == null) {
      synchronized (Frame.class) {
        instance = new Gui();
      }
    }

    return instance;
  }

  private void createUIComponents() {
    simulator = Simulator.getInstance();
    canvasPanel = Simulator.getInstance().getCanvas();
  }
}
