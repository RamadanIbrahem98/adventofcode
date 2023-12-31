import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
  public static void main(String[] args) throws Exception {
    String input = new String(Files.readAllBytes(Paths.get("input.txt")));
    List<String> data = new ArrayList<>();

    for (String line : input.strip().split("\n")) {
      data.add(line);
    }

    System.out.println("Part One Answer is: " + partOne(data));
    System.out.println("Part Two Answer is: " + partTwo(data));
  }

  public static int partOne(List<String> input) {
    int total = 0;

    for (String line : input) {
      String game = line.strip().split(": ")[1];
      String[] split = game.strip().replace(" | ", "\t").split("\t");
      String[] winningCardsString = split[0].replace("  ", " ").split(" ");
      String[] myHandString = split[1].replace("  ", " ").split(" ");
      Map<String, Integer> occur = new HashMap<>();
      int count = 0, runningTotal = 0;

      for (String num : winningCardsString) {
        occur.put(num, 0);
      }

      for (String num : myHandString) {
        if (occur.get(num) != null) {
          count++;
        }
      }

      if (count > 0) {
        runningTotal += 1;
        count--;
      }

      while (count > 0) {
        runningTotal *= 2;
        count--;
      }

      total += runningTotal;
    }
    return total;
  }

  public static int partTwo(List<String> input) {
    List<Integer> totalCards = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {totalCards.add(1);}

    for (int i = 0; i < input.size(); i++) {
      String game = input.get(i).strip().split(": ")[1];
      String[] split = game.strip().replace(" | ", "\t").split("\t");
      String[] winningCardsString = split[0].replace("  ", " ").split(" ");
      String[] myHandString = split[1].replace("  ", " ").split(" ");
      Map<String, Integer> occur = new HashMap<>();
      int count = 0;

      for (String num : winningCardsString) {
        occur.put(num, 0);
      }

      for (String num : myHandString) {
        if (occur.get(num) != null) {
          count++;
        }
      }

      for (int k = 0; k < totalCards.get(i); k++) {
        for (int j = 0; j < count; j++) {
          totalCards.set(i + j + 1, totalCards.get(i + j + 1) + 1);
        }
      }
    }

    return totalCards.stream().reduce(0, (partialCount, el) -> partialCount + el);
  }

}
