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
			
			System.out.println("Part One Answer is: " + partOne(input));
			System.out.println("Part Two Answer is: " + partTwo(input));
	}

	public static int partOne(String input) {
		int total = 0;

		for (String line : input.split("\n")) {
			line = line.replaceAll("[^\\d]", "");
			total += Integer.parseInt(line.substring(0, 1) + line.substring(line.length() - 1));
		}

		return total;
	}

	public static int partTwo(String input) {
		int total = 0;
		Map<String, String> numberMap = getRespectiveNumber();

		input = input
		.replaceAll("one", "o1e")
		.replaceAll("two", "t2o")
		.replaceAll("three", "t3e")
		.replaceAll("four", "f4r")
		.replaceAll("five", "5e")
		.replaceAll("six", "s6")
		.replaceAll("seven", "s7n")
		.replaceAll("eight", "e8t")
		.replaceAll("nine", "n9e");


		for (String line : input.split("\n")) {
			List<String> result = extractNumbers(line);
			total += Integer.parseInt(numberMap.get(result.get(0)) + numberMap.get(result.get(result.size() - 1)));;
		}

		return total;
	}

	public static Map<String, String> getRespectiveNumber() {
		Map<String, String> lookupTable = new HashMap<>();
		lookupTable.put("one", "1");
		lookupTable.put("1", "1");
		lookupTable.put("two", "2");
		lookupTable.put("2", "2");
		lookupTable.put("three", "3");
		lookupTable.put("3", "3");
		lookupTable.put("four", "4");
		lookupTable.put("4", "4");
		lookupTable.put("five", "5");
		lookupTable.put("5", "5");
		lookupTable.put("six", "6");
		lookupTable.put("6", "6");
		lookupTable.put("seven", "7");
		lookupTable.put("7", "7");
		lookupTable.put("eight", "8");
		lookupTable.put("8", "8");
		lookupTable.put("nine", "9");
		lookupTable.put("9", "9");

		return lookupTable;
	}

	public static List<String> extractNumbers(String line) {
		List<String> result = new ArrayList<>();
		Pattern pattern = Pattern.compile("(?:one|two|three|four|five|six|seven|eight|nine|1|2|3|4|5|6|7|8|9)");
		Matcher matcher = pattern.matcher(line);

		while (matcher.find()) {
			result.add(matcher.group());
		}

		return result;
	}
}
