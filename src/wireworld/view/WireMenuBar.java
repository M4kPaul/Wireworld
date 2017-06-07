package wireworld.view;

import wireworld.model.Grid;
import wireworld.model.Simulator;
import wireworld.util.GridOpener;
import wireworld.util.GridSaver;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Odpowiada za wygląd i obsługę paska menu.
 */
class WireMenuBar extends JMenuBar {
  private JMenu fileMenu;
  private JMenu viewMenu;
  private JMenu gridMenu;
  private JMenuItem openItem;
  private JMenuItem saveItem;
  private JMenuItem firstSizeItem;
  private JMenuItem secondSizeItem;
  private JMenuItem thirdSizeItem;
  private JMenuItem fourthSizeItem;

  /**
   * Tworzy i inicjalizuje pasek menu.
   */
  WireMenuBar() {
    fileMenu = new JMenu("File");
    viewMenu = new JMenu("View");

    gridMenu = new JMenu("Resize grid");

    openItem = new JMenuItem("Open");
    saveItem = new JMenuItem("Save");

    firstSizeItem = new JMenuItem("Small (32x32)", null);
    secondSizeItem = new JMenuItem("Medium (64x64)", null);
    thirdSizeItem = new JMenuItem("Big (128x128)", null);
    // fourthSizeItem = new JMenuItem("Custom", null); TODO

    openItem.addActionListener(new OpenAction());
    saveItem.addActionListener(new SaveAction());

    firstSizeItem.addActionListener(e -> Simulator.getInstance().setGridSize(32, 32));
    secondSizeItem.addActionListener(e -> Simulator.getInstance().setGridSize(64, 64));
    thirdSizeItem.addActionListener(e -> Simulator.getInstance().setGridSize(128, 128));
    // fourthSizeItem.addActionListener(null);

    add(fileMenu);
    add(viewMenu);

    fileMenu.add(openItem);
    fileMenu.add(saveItem);

    viewMenu.add(gridMenu);

    gridMenu.add(firstSizeItem);
    gridMenu.add(secondSizeItem);
    gridMenu.add(thirdSizeItem);
    // gridMenu.add(fourthSizeItem);
  }

  /**
   * Klasa wewnętrzna odpowiadająca za obsługę zdarzenia wczytywania siatki komórek z pliku do programu.
   */
  private class OpenAction implements ActionListener {

    /**
     * Metoda wywoływana, gdy słuchacz odbierze zdarzenie.
     *
     * @param e zdarzenie odebrane przez słuchacza
     */
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
      if (status == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        GridOpener opener = new GridOpener();
        Simulator simulator = Simulator.getInstance();
        try {
          simulator.setGrid(opener.openGrid(file));
        } catch (IOException | ClassNotFoundException ex) {
          JOptionPane.showMessageDialog(null, ex, "Error!", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  /**
   * Klassa wewnętrzna odpowiadająca za obsługę zdarzenia zapisywania siatki komórek z programu do pliku.
   */
  private class SaveAction implements ActionListener {

    /**
     * Metoda wywoływana, gdy słuchacz odbierze zdarzenie zapisywania.
     *
     * @param e zdarzenie odebrane przez słuchacza
     */
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
      if (status == JFileChooser.APPROVE_OPTION) {
        File file = new File(fileChooser.getSelectedFile() + ".wwd");
        GridSaver saver = new GridSaver();
        try {
          Grid grid = Simulator.getInstance().getGrid();
          saver.saveGrid(grid, file);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null, ex, "Error!", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
}
