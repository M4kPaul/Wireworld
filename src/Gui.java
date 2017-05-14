import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Gui extends JPanel {
  private JPanel mainPanel;
  private JButton wireButton;
  private JButton headButton;
  private JButton tailButton;
  private JPanel canvas;

  Gui() {
    ActionListener colorSelect = (ActionEvent e) -> ((Canvas)canvas).setSelectedColor(((JButton)e.getSource()).getBackground());
    wireButton.addActionListener(colorSelect);
    headButton.addActionListener(colorSelect);
    tailButton.addActionListener(colorSelect);

    add(mainPanel);
  }

  private void createUIComponents() {
    canvas = new Canvas();
  }
}
