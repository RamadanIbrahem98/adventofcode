import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static List<String> data = new ArrayList<>();
  static List<Integer> siblings = new ArrayList<>();
  static int length;

  public static void main(String[] args) throws IOException{
    String input = new String(Files.readAllBytes(Paths.get("input.txt")));

    length = input.split("\n")[0].strip().replaceAll(" ", "").length();

    siblings.add(-1); // previous
    siblings.add(1); // next
    siblings.add(length * -1); // upove
    siblings.add(length); // under
    siblings.add(length - 1); // under left diagonal
    siblings.add(length + 1); // under right diagonal
    siblings.add(length * -1 - 1); // upove left diagonal
    siblings.add(length * -1 + 1); // upove right diagonal

    for (String line : input.split("\n")) {
      String[] x = line.trim().split("");

      for (String el : x) {
        data.add(el);
      }
    }

    System.out.println(partOne());
    System.out.println(partTwo());
  }

  public static int getNextIdx(int idx) {
    int next = idx++;

    while(next < data.size() && data.get(next).matches("\\d")) {
      next++;
    }

    return next;
  }

  public static int getPreviousIdx(int idx) {
    int prev = idx--;

    while(prev > 0 && data.get(prev).matches("\\d")) {
      prev--;
    }

    if (data.get(prev).matches("\\d")) {
      return prev;
    } else {
      return ++prev;
    }
  }

  public static int getWholeNumber(int start, int end) {
    int wholeNumber;

    if (start == end) {
      wholeNumber = Integer.parseInt(data.get(start));
    } else {
      wholeNumber = Integer.parseInt(data.subList(start, end).stream().reduce("", (partialString, el) -> partialString + el).toString());
    }

    return wholeNumber;
  }

  public static int partOne() {
    int total = 0;

    for (int i = 0; i < data.size(); i++) {
      if (!data.get(i).matches("\\d")) {
        continue;
      }
      for (Integer sibling : siblings) {
        if (
          i + sibling < 0 ||
          i + sibling >= data.size() ||
          data.get(i + sibling).equals(".") ||
          data.get(i + sibling).matches("\\d")
        ) {
          continue;
        }

        int start = getPreviousIdx(i);
        int end = getNextIdx(i);
        total += getWholeNumber(Math.min(start, i), Math.max(i, end));
        if (end > 0 && end < data.size() && data.get(end).matches("\\d") && data.get(end - 1).matches("\\d")) {
          i = end + 1;
        } else {
          i = end;
        }
        break;
      }
    }

    return total;
  }
  
  public static int partTwo() {
    int total = 0;
    long runningTotal = 1;
    int gearsCount = 0;

    for (int i = 0; i < data.size(); i++) {
      if (!data.get(i).equals("*")) {
        continue;
      }

      if (!(i - length < 0) && data.get(i - length).matches("\\d")) {
        gearsCount++;
        int start = getPreviousIdx(i - length);
        int end = getNextIdx(i - length);
        runningTotal *= getWholeNumber(start, end);
      } else if (!(i - length < 0) && !data.get(i - length).matches("\\d")) {
        if (!(i - length - 1 < 0) && data.get(i - length - 1).matches("\\d")) {
          gearsCount++;
          int start = getPreviousIdx(i - length - 1);
          runningTotal *= getWholeNumber(start, i - length);
        }
        
        if (!(i - length + 1 < 0) && data.get(i - length + 1).matches("\\d")) {
          gearsCount++;
          int end = getNextIdx(i - length + 1);
          runningTotal *= getWholeNumber(i - length + 1, end);
        }
      }

      if (i + length < data.size() && data.get(i + length).matches("\\d")) {
        gearsCount++;
        int start = getPreviousIdx(i + length);
        int end = getNextIdx(i + length);
        runningTotal *= getWholeNumber(start, end);
      } else if (i + length < data.size() && !data.get(i + length).matches("\\d")) {
        if (i + length - 1 < data.size() && data.get(i + length - 1).matches("\\d")) {
          gearsCount++;
          int start = getPreviousIdx(i + length - 1);
          runningTotal *= getWholeNumber(start, i + length);
        }
        
        if (i + length + 1 < data.size() && data.get(i + length + 1).matches("\\d")) {
          gearsCount++;
          int end = getNextIdx(i + length + 1);
          runningTotal *= getWholeNumber(i + length + 1, end);
        }
      }

      if (!(i - 1 < 0) && data.get(i - 1).matches("\\d")) {
        gearsCount++;
        int start = getPreviousIdx(i - 1);
        runningTotal *= getWholeNumber(start, i);
      }
      
      if (i + 1 < data.size() && data.get(i + 1).matches("\\d")) {
        gearsCount++;
        int end = getNextIdx(i + 1);
        runningTotal *= getWholeNumber(i + 1, end);
      }
      
      if (gearsCount == 2) {
        total += runningTotal;
      }

      runningTotal = 1;
      gearsCount = 0;
    }

    return total;
  }
}
