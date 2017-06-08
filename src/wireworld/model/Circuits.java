package wireworld.model;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Tworzy i inicjalizuje gotowe struktury.
 */
public class Circuits implements State {
  private static final HashMap<Point, Cell> OR = readSerializedCircuit("OR");
  private static final HashMap<Point, Cell> AND = readSerializedCircuit("AND");
  private static final HashMap<Point, Cell> XOR = readSerializedCircuit("XOR");
  private static final HashMap<Point, Cell> DIODE = readSerializedCircuit("Diode");
  private static final HashMap<Point, Cell> AND_NOT = readSerializedCircuit("AND-NOT");

  private static final HashMap<String, HashMap<Point, Cell>> prebuilt = new HashMap<String, HashMap<Point, Cell>>() {
    {
      put("OR", OR);
      put("AND", AND);
      put("XOR", XOR);
      put("Diode", DIODE);
      put("AND-NOT", AND_NOT);
    }
  };

  /**
   * Metoda do uzyskania gotowych struktur
   *
   * @return hashmapa struktur
   */
  public static HashMap<String, HashMap<Point, Cell>> getPrebuilt() {
    return prebuilt;
  }

  /**
   * Metoda do wczytania gotowej struktur z pliku
   *
   * @param fileName nazwa pliku ze strukturą
   * @return gotowa struktura
   */
  private static HashMap<Point, Cell> readSerializedCircuit(String fileName) {
    try {
      FileInputStream fileIn = new FileInputStream("resources/data/" + fileName + ".ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      HashMap<Point, Cell> circuit = (HashMap<Point, Cell>)in.readObject();
      in.close();
      fileIn.close();
      return circuit;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}
