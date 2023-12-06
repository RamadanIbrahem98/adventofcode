import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Main {
	public static void main(String[] args) throws Exception {
			String input = new String(Files.readAllBytes(Paths.get("input.txt")));

			String[] timesString = input.split("\n")[0].strip().split(": ")[1].strip().split(" ");
			String[] distancesString = input.split("\n")[1].strip().split(": ")[1].strip().split(" ");

			List<Integer> times = new ArrayList<>();
			List<Integer> distances = new ArrayList<>();

			for (String timeString : timesString) {
				if (!timeString.matches("\\d+")) {
					continue;
				}
				times.add(Integer.parseInt(timeString.strip()));
			}
			
			for (String distanceString : distancesString) {
				if (!distanceString.matches("\\d+")) {
					continue;
				}
				distances.add(Integer.parseInt(distanceString.strip()));
			}
			
			System.out.println("Part One Answer is: " + partOne(times, distances));
			System.out.println("Part Two Answer is: " + partTwo(times, distances));
	}

	public static int partOne(List<Integer> times, List<Integer> distances) {
		int total = 1;
		for (int i = 0; i < times.size(); i++) {
			long first = getStartingPoint(1, times.get(i), times.get(i), distances.get(i));
			long last = getEndingPoint(1, times.get(i), times.get(i), distances.get(i));
			total *= (last - first + 1);
		}

		return total;
	}

	public static long getStartingPoint(long left, long right, long time, long distance) {
		long firstOccurance = 0l;
		while(left <= right) {
			long middle = left + (right - left) / 2;
			long val = (time - middle) * middle;
			
			if ( val > distance) {
				firstOccurance = middle;
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}

		return firstOccurance;
	}
	
	public static long getEndingPoint(long left, long right, long time, long distance) {
		long lastOccurance = 0l;
		while(left <= right) {
			long middle = left + (right - left) / 2;
			long val = (time - middle) * middle;
			
			if (val > distance) {
				left = middle + 1;
				lastOccurance = middle;
			} else {
				right = middle - 1;
			}
		}

		return lastOccurance;
	}

	public static long partTwo(List<Integer> times, List<Integer> distances) {
		long time = Long.parseLong(times.stream().map(Object::toString).collect(Collectors.joining()));
		long distance = Long.parseLong(distances.stream().map(Object::toString).collect(Collectors.joining()));

		long first = getStartingPoint(1, time, time, distance);
		long last = getEndingPoint(1, time, time, distance);

		return last - first + 1;
	}
}
