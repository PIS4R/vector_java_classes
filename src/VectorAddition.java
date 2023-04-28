import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

class Vector {
    private List<Double> elements;

    public Vector() {
        elements = new ArrayList<Double>();
    }

    public void addElement(Double element) {
        elements.add(element);
    }

    public int getSize() {
        return elements.size();
    }

    public Double getElement(int index) {
        return elements.get(index);
    }
}

class WektoryRoznejDlugosciException extends Exception {
    private int len1;
    private int len2;

    public WektoryRoznejDlugosciException(int len1, int len2) {
        this.len1 = len1;
        this.len2 = len2;
    }
    public int getLength1() {
        return len1;
    }
    public int getLength2() {
        return len2;
    }
}
public class VectorAddition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Vector vector1 = new Vector();
            Vector vector2 = new Vector();

            System.out.println("Podaj pierwszy wektor: ");
            readVector(sc, vector1);

            System.out.println("Podaj drugi wektor: ");
            readVector(sc, vector2);

            try {
                Double[] result = addVectors(vector1, vector2);
                writeToFile(result);
                System.out.println("Dane zostaly poprawnie zapisane do pliku");
                break;
            } catch (WektoryRoznejDlugosciException e) {
                System.out.println("Długość pierwszego wektora to " + e.getLength1() + " a drugiego to " + e.getLength2());
                System.out.println("Wektory mają różną długość. Podaj je ponownie.");
                continue;
            } catch (IOException e) {
                System.out.println("Wystąpił błąd podczas zapisywania do pliku.");
            }
        }
    }

    private static void readVector(Scanner sc, Vector vector) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            try {
                Double element = Double.parseDouble(line);
                vector.addElement(element);
            } catch (NumberFormatException ignored) {}
        }
    }

    private static Double[] addVectors(Vector vector1, Vector vector2) throws WektoryRoznejDlugosciException {
       // int size = vector1.getSize();
        if (vector1.getSize() != vector2.getSize()) {
            throw new WektoryRoznejDlugosciException(vector1.getSize(), vector2.getSize());
        }

        Double[] result = new Double[vector1.getSize()];
        for (int j= 0; j < vector1.getSize(); j++) {
            result[j] = vector1.getElement(j) + vector2.getElement(j);
        }
        return result;
    }

    private static void writeToFile(Double[] result) throws IOException {
        FileWriter writer = new FileWriter("vector.txt");
        for (Double element : result) {
            writer.write(element.toString() + " | ");
        }
        writer.close();
    }

}