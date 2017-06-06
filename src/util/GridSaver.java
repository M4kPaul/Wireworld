package util;

import model.Grid;

import java.io.*;

public class GridSaver {

  public void saveGrid(Grid grid, File file) throws IOException {
    ObjectOutputStream outputStream = null;

    try {
      outputStream = new ObjectOutputStream((new BufferedOutputStream(new FileOutputStream(file))));
      outputStream.writeObject(grid);
    } finally {
      if (outputStream != null) {
        outputStream.flush();
        outputStream.close();
      }
    }
  }
}
