package view;

import model.Simulator;
import model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WireGui extends JPanel {
  private JPanel controlPanel;
  private JPanel drawPanel;
  private JPanel runPanel;
  private JPanel prebuiltCircuitsPanel;
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
  private JComboBox<String> prebuiltCircuitsList;
  private JLabel selectedCircuitLabel;
  private JPanel emptyPanel;

  WireGui() {
    electronHeadLabel.setIcon(new ImageIcon("src/drawable/electron_head.png"));
    electronTailLabel.setIcon(new ImageIcon("src/drawable/electron_tail.png"));
    conductorLabel.setIcon(new ImageIcon("src/drawable/conductor.png"));

    electronHeadRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.ELECTRON_HEAD));
    electronTailRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.ELECTRON_TAIL));
    conductorRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.CONDUCTOR));
    cleanButton.addActionListener(e -> Simulator.getInstance().getCanvas().clear());
    startButton.addActionListener(e -> Simulator.getInstance().start());
    stopButton.addActionListener(e -> Simulator.getInstance().stop());
    speedSlider.addChangeListener(e -> Simulator.getInstance().setTimerDelay(1200 - speedSlider.getValue()));

    selectedCircuitLabel.setIcon(new ImageIcon("src/drawable/Diode.png"));
    prebuiltCircuitsList.addItemListener(e -> {
      selectedCircuitLabel.setIcon(new ImageIcon("src/drawable/" + e.getItem() + ".png"));
      if (selectedCircuitLabel.getBorder() != null) {
        Simulator.getInstance().getCanvas().setStructureName((String)prebuiltCircuitsList.getSelectedItem());
      }
    });

    selectedCircuitLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (selectedCircuitLabel.getBorder() == null) {
          selectedCircuitLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
          Simulator.getInstance().getCanvas().setStructureName((String)prebuiltCircuitsList.getSelectedItem());
        } else {
          selectedCircuitLabel.setBorder(null);
          Simulator.getInstance().getCanvas().setStructureName(null);
        }
      }
    });

    add(controlPanel);
  }

  private void createUIComponents() {
    prebuiltCircuitsList = new JComboBox<>();
    prebuiltCircuitsList.addItem("Diode");
    prebuiltCircuitsList.addItem("Xor");
  }
}
