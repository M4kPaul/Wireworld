package wireworld.view;

import wireworld.model.Circuits;
import wireworld.model.Simulator;
import wireworld.model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Odowiada za wygląd i obsługę panelu do kontroli działania programu.
 */
class WireGui extends JPanel {
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

  /**
   * Tworzy i inicjalizuje panel do kontorli działania programu.
   */
  WireGui() {
    electronHeadLabel.setIcon(new ImageIcon("resources/drawable/electron_head.png"));
    electronTailLabel.setIcon(new ImageIcon("resources/drawable/electron_tail.png"));
    conductorLabel.setIcon(new ImageIcon("resources/drawable/conductor.png"));

    electronHeadRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.ELECTRON_HEAD));
    electronTailRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.ELECTRON_TAIL));
    conductorRadioButton.addActionListener(e -> Simulator.getInstance().getCanvas().setSelectedColor(State.CONDUCTOR));
    cleanButton.addActionListener(e -> Simulator.getInstance().getCanvas().clear());
    startButton.addActionListener(e -> Simulator.getInstance().start());
    stopButton.addActionListener(e -> Simulator.getInstance().stop());
    speedSlider.addChangeListener(e -> Simulator.getInstance().setTimerDelay(1200 - speedSlider.getValue()));

    selectedCircuitLabel.setIcon(new ImageIcon("resources/drawable/" + prebuiltCircuitsList.getSelectedItem() + ".png"));
    prebuiltCircuitsList.addItemListener(e -> {
      selectedCircuitLabel.setIcon(new ImageIcon("resources/drawable/" + e.getItem() + ".png"));
      if (selectedCircuitLabel.getBorder() != null) {
        Simulator.getInstance().setSelectedCircuitNameName((String)prebuiltCircuitsList.getSelectedItem());
      }
    });

    selectedCircuitLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (selectedCircuitLabel.getBorder() == null) {
          selectedCircuitLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
          Simulator.getInstance().setSelectedCircuitNameName((String)prebuiltCircuitsList.getSelectedItem());
        } else {
          selectedCircuitLabel.setBorder(null);
          Simulator.getInstance().setSelectedCircuitNameName(null);
        }
      }
    });

    add(controlPanel);
  }

  /**
   * Inicjalizuje niestandardowe komponenty panelu.
   */
  private void createUIComponents() {
    prebuiltCircuitsList = new JComboBox<>();
    Circuits.getPrebuilt().forEach((k, v) -> prebuiltCircuitsList.addItem(k));
  }
}
