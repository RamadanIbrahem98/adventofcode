import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws IOException{
    String input = new String(Files.readAllBytes(Paths.get("input.txt")));

    System.out.println(partOne(input));
    System.out.println(partTwo(input));
  }

  public static int partOne(String input) {
    int total = 0;
    boolean validGame = true;
    Map<String, Integer> colorMax = new HashMap<>();
    colorMax.put("red", 12);
    colorMax.put("green", 13);
    colorMax.put("blue", 14);

    for (String line : input.split("\n")) {
      String[] s = line.split(": ");

      int gameNumber = Integer.parseInt(s[0].split(" ")[1]);

      for (String game : s[1].split("; ")) {
        for (String pair : game.split(", ")) {
          String[] numColor = pair.split(" ");
          int count = Integer.parseInt(numColor[0]);
          String color = numColor[1];

          if (count > colorMax.get(color.trim())) {
            validGame = false;
            continue;
          }
        }
      }

      if (validGame) {
        total += gameNumber;
      }

      validGame = true;
    }

    return total;
  }
  
  public static int partTwo(String input) {
    int total = 0;
    Map<String, Integer> colorMax = new HashMap<>();
    colorMax.put("red", 0);
    colorMax.put("green", 0);
    colorMax.put("blue", 0);

    for (String line : input.split("\n")) {
      String[] s = line.split(": ");

      for (String game : s[1].split("; ")) {
        for (String pair : game.split(", ")) {
          String[] numColor = pair.split(" ");
          int count = Integer.parseInt(numColor[0]);
          String color = numColor[1].trim();

          if (count > colorMax.get(color)) {
            colorMax.put(color, count);
          }
        }
      }

      total += (colorMax.get("red") * colorMax.get("green") * colorMax.get("blue"));

      colorMax.put("red", 0);
      colorMax.put("green", 0);
      colorMax.put("blue", 0);
    }

    return total;
  }
}
