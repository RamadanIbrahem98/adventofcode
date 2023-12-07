import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Main {
	static List<Long> seeds = new ArrayList<>();
	static List<List<Long>> seedsToSoil = new ArrayList<>();
	static List<List<Long>> soilToFertilizer = new ArrayList<>();
	static List<List<Long>> fertilizerToWater = new ArrayList<>();
	static List<List<Long>> waterToLight = new ArrayList<>();
	static List<List<Long>> lightToTemperature = new ArrayList<>();
	static List<List<Long>> temperatureToHumidity = new ArrayList<>();
	static List<List<Long>> humidityToLocation = new ArrayList<>();
	static List<List<List<Long>>> original = new ArrayList<>();
	static final int SOURCE = 0;
	static final int DESTINATION = 1;
	static final int STEPS = 2;

	public static void main(String[] args) throws Exception {
		String input = new String(Files.readAllBytes(Paths.get("input.txt")));

		String[] arr = input.split("\n\n");

		for (String seed : arr[0].split(": ")[1].strip().split(" ")) {
			seeds.add(Long.parseLong(seed.strip()));
		}
		
		for (int i = 1; i < arr.length; i++) {
			String[] lines = arr[i].split("\n");

			for (int j = 1; j < lines.length; j++) {
				String[] data = lines[j].split(" ");
				List<Long> line = new ArrayList<>();

				line.add(Long.parseLong(data[1].strip()));
				line.add(Long.parseLong(data[0].strip()));
				line.add(Long.parseLong(data[2].strip()));
				getRespectiveMap(i).add(line);
			}
			original.add(getRespectiveMap(i));
		}

		System.out.println("Part One Answer is: " + partOne());
		System.out.println("Part Two Answer is: " + partTwo(input));
	}

	public static List<List<Long>> getRespectiveMap(int idx) {
		switch (idx) {
			case 1:
				return seedsToSoil;
			case 2:
				return soilToFertilizer;
			case 3:
				return fertilizerToWater;
			case 4:
				return waterToLight;
			case 5:
				return lightToTemperature;
			case 6:
				return temperatureToHumidity;
			case 7:
				return humidityToLocation;
			default:
				return new ArrayList<>();
		}
	}

	public static Long partOne() {
		for (List<List<Long>> block : original) {
			List<Long> tempArr = new ArrayList<>();
			for (Long seed : seeds) {
				for (List<Long> line : block) {
					if (line.get(SOURCE) <= seed && seed < line.get(SOURCE) + line.get(STEPS)) {
						if (tempArr.indexOf(seed) != -1) {
							tempArr.remove(tempArr.indexOf(seed));
						}
						tempArr.add(seed - line.get(SOURCE) + line.get(DESTINATION));
						break;
					} else {
						if (tempArr.indexOf(seed) == -1 || tempArr.indexOf(seed - line.get(SOURCE) + line.get(DESTINATION)) != -1) {
							tempArr.add(seed);
						}
					}
				}
			}

			seeds = new ArrayList<>();
			seeds.addAll(tempArr);
		}

		Collections.sort(seeds);

		return seeds.get(0);
	}

	public static int partTwo(String input) {
		int total = 0;

		return total;
	}
}
