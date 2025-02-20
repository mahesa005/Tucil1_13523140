import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan file path: ");
        String filename = scanner.nextLine();
        String filepath = "test/input/" + filename;

        try {
            List<String> inputList = Files.readAllLines(Paths.get(filepath));
            for (String line : inputList) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
        scanner.close();
    }
}