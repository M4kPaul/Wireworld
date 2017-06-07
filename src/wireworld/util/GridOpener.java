package wireworld.util;

import wireworld.model.Grid;

import java.io.*;

/**
 * Importer siatki komórek z pliku do programu.
 */
public class GridOpener {

  /**
   * Wczytuje siatkę komórek z pliku do programu.
   * @param file plik do wczytania zawierający siatkę komórek
   * @return siatka komórek wczytana z pliku
   * @throws IOException wyjątek powstały w wyniku niudanej lub przerwanej operacji wczytywania siatki komórek
   * @throws ClassNotFoundException wyjątke powstały w wyniku nieznalezienie określonej klasy
   */
  public Grid openGrid(File file) throws IOException, ClassNotFoundException {
    ObjectInputStream inputStream = null;

    try {
      inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
      return (Grid) inputStream.readObject();
    } finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }

  }
}
