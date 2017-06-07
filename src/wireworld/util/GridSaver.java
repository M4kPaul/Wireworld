package wireworld.util;

import wireworld.model.Grid;

import java.io.*;

/**
 * Eksporter siatki komórek z programu do pliku.
 */
public class GridSaver {

  /**
   * Zapisuje siatkę komórek z programu do danego pliku.
   * @param grid siatka komórek
   * @param file dany plik do zapisu siatki komórek
   * @throws IOException wyjątek powstał w wyniku nieudanej lub przerwanej operacji zapisywania siatki komórek
   */
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
