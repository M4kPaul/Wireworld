package util;

import model.Grid;

import java.io.*;

public class GridOpener {
  public Grid openGrid(File file) throws IOException, ClassNotFoundException {
    ObjectInputStream inputStream = null;

    try {
      inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
      return (Grid)inputStream.readObject();
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }

  }
}
