package view;

import model.Grid;
import model.Simulator;
import util.GridOpener;
import util.GridSaver;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WireMenuBar extends JMenuBar {
  private JMenu fileMenu;
  private JMenu viewMenu;
  private JMenu helpMenu;
  private JMenu gridMenu;
  private JMenuItem newItem;
  private JMenuItem openItem;
  private JMenuItem saveItem;
  private JMenuItem saveAsItem;
  private JMenuItem exitItem;
  private JMenuItem firstSizeItem;
  private JMenuItem secondSizeItem;
  private JMenuItem thirdSizeItem;

  public WireMenuBar() {
    fileMenu = new JMenu("File");
    viewMenu = new JMenu("View");
    helpMenu = new JMenu("Help");

    gridMenu = new JMenu("Resize grid");

    newItem = new JMenuItem("New");
    openItem = new JMenuItem("Open");
    saveItem = new JMenuItem("Save");
    saveAsItem = new JMenuItem("Save as");
    exitItem = new JMenuItem("Exit");

    firstSizeItem = new JMenuItem("29x29", null);
    secondSizeItem = new JMenuItem("37x37", null);
    thirdSizeItem = new JMenuItem("47x47", null);

    newItem.addActionListener(null);
    openItem.addActionListener(new OpenAction());
    saveItem.addActionListener(new SaveAction());
    saveAsItem.addActionListener(null);
    exitItem.addActionListener(null);

    firstSizeItem.addActionListener(e -> Simulator.getInstance().setGridSize(29, 29));
    secondSizeItem.addActionListener(e -> Simulator.getInstance().setGridSize(37, 37));
    thirdSizeItem.addActionListener(e -> Simulator.getInstance().setGridSize(47, 47));

    add(fileMenu);
    add(viewMenu);
    add(helpMenu);

    fileMenu.add(newItem);
    fileMenu.add(openItem);
    fileMenu.addSeparator();
    fileMenu.add(saveItem);
    fileMenu.add(saveAsItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    viewMenu.add(gridMenu);

    gridMenu.add(firstSizeItem);
    gridMenu.add(secondSizeItem);
    gridMenu.add(thirdSizeItem);
  }

  private class OpenAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFileChooser fileChooser = new JFileChooser();

      fileChooser.setFileFilter(new FileFilter() {

        public String getDescription() {
          return "Wireworld file (*.wwd)";
        }

        public boolean accept(File f) {
          return f.isDirectory() || f.getName().toLowerCase().endsWith(".wwd");
        }
      });

      int status = fileChooser.showOpenDialog(null);
      if(status == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        GridOpener opener = new GridOpener();
        Simulator simulator = Simulator.getInstance();
        try {
          simulator.setGrid(opener.openGrid(file));
        } catch (IOException | ClassNotFoundException ex) {
          JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  private class SaveAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFileChooser fileChooser = new JFileChooser();

      fileChooser.setFileFilter(new FileFilter() {

        public String getDescription() {
          return "Wireworld file (*.wwd)";
        }

        public boolean accept(File f) {
          return f.isDirectory() || f.getName().toLowerCase().endsWith(".wwd");
        }
      });

      int status = fileChooser.showSaveDialog(null);
      if(status == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        GridSaver saver = new GridSaver();
        try {
          Grid grid = Simulator.getInstance().getGrid();
          saver.saveGrid(grid, file);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
