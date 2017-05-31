package model;

import java.awt.*;

public interface State {
  Color EMPTY = new Color(0, 0, 0);
  Color ELECTRON_HEAD = new Color(0, 127, 255);
  Color ELECTRON_TAIL = new Color(255, 64, 0);
  Color CONDUCTOR = new Color(255, 214, 0);
}
