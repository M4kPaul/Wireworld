import javax.swing.*;
import java.awt.event.*;

class GuiTest extends JFrame {
  private JPanel panel1;
  private JButton button1;
  private JButton button2;
  private JButton button3;
  private JTextField textField1;

  private GuiTest() {
    ActionListener buttonListener = (ActionEvent e) -> textField1.setText(((JButton)e.getSource()).getText());
    button1.addActionListener(buttonListener);
    button2.addActionListener(buttonListener);
    button3.addActionListener(buttonListener);

    add(panel1);
  }

  public static void main(String[] args) {
    SwingConsole.run(new GuiTest(), 306, 100);
  }
}
