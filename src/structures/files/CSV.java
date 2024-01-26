package structures.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import structures.List;
import structures.Node;
import structures.exceptions.DomainException;

public class CSV {

    public static void write(Node node, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            while (node != null) {
                writer.write(node.getRegistration() + ";" + node.getDate() + "\n");
                node = node.getNextNode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List read(List list, String path) throws ParseException, DomainException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int registration = Integer.parseInt(parts[0]);
                    LocalDate date = LocalDate.parse(parts[1]);

                    list.addTheEnd(new Node(registration, date));
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}