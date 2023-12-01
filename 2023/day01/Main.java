import java.nio.file.Files;
import java.nio.file.Paths;

class Main {
	public static void main(String[] args) throws Exception {
			String input = new String(Files.readAllBytes(Paths.get("input.txt")));
			int total = 0;

			for (String line : input.split("\n")) {
				line = line.replaceAll("[^\\d]", "");
				total += Integer.parseInt(line.substring(0, 1) + line.substring(line.length() - 1));
			}

			System.out.println(total);
	}
}
