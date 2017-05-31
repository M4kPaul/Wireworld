package view;

import model.Simulator;
import model.State;

import javax.swing.*;

public class WireGui extends JPanel {
  private JPanel controlPanel;
  private JPanel drawPanel;
  private JPanel runPanel;
  private JPanel emptyPanel;
  private JLabel conductorLabel;
  private JLabel electronTailLabel;
  private JLabel electronHeadLabel;
  private JRadioButton conductorRadioButton;
  private JRadioButton electronTailRadioButton;
  private JRadioButton electronHeadRadioButton;
  private JButton cleanButton;
  private JButton startButton;
  private JButton stopButton;
  private JSlider speedSlider;

  public WireGui() {
    electronHeadLabel.setIcon(new ImageIcon("electron_head.png"));
    electronTailLabel.setIcon(new ImageIcon("electron_tail.png"));
    conductorLabel.setIcon(new ImageIcon("conductor.png"));

    electronHeadRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.ELECTRON_HEAD));
    electronTailRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.ELECTRON_TAIL));
    conductorRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.CONDUCTOR));
    cleanButton.addActionListener(e -> Simulator.getInstance().getCanvas().clear());
    startButton.addActionListener(e -> Simulator.getInstance().start());
    stopButton.addActionListener(e -> Simulator.getInstance().stop());
    speedSlider.addChangeListener(e -> Simulator.getInstance().setTimerDelay(1200 - speedSlider.getValue()));

    add(controlPanel);
  }
}
