import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuBar extends JMenuBar {
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

  public MenuBar() {
    fileMenu = new JMenu("File");
    viewMenu = new JMenu("View");
    helpMenu = new JMenu("Help");

    newItem = new JMenuItem("New");
    openItem = new JMenuItem("Open");
    saveItem = new JMenuItem("Save");
    saveAsItem = new JMenuItem("Save as");
    exitItem = new JMenuItem("Exit");

    gridMenu = new JMenu("Resize grid");

    firstSizeItem = new JMenuItem("32x32", null);
    secondSizeItem = new JMenuItem("64x64", null);
    thirdSizeItem = new JMenuItem("128x128", null);

    openItem.addActionListener(new OpenAction());
    saveItem.addActionListener(new SaveAction());

    firstSizeItem.addActionListener(e -> Simulator.getInstance().setSize(32, 32));
    secondSizeItem.addActionListener(e -> Simulator.getInstance().setSize(64, 64));
    thirdSizeItem.addActionListener(e -> Simulator.getInstance().setSize(128, 128));

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

    add(fileMenu);
    add(viewMenu);
    add(helpMenu);
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
