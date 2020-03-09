package yatzy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Yatzy {

	public static int chance(int[] values) {
		return Arrays.stream(values).sum();
	}

	public static int yatzy(int[] values) {
		int score = 0;
		Map<Integer, Integer> occurences = getOccurencesByValue(values);
		if (occurences.size() == 1) {
			score = 50;
		}
		return score;
	}

	public static int ones(int[] values) {
		return sumAllValuesOf(1, values);
	}

	public static int twos(int[] values) {
		return sumAllValuesOf(2, values);
	}

	public static int threes(int[] values) {
		return sumAllValuesOf(3, values);
	}

	public static int fours(int[] values) {
		return sumAllValuesOf(4, values);
	}

	public static int fives(int[] values) {
		return sumAllValuesOf(5, values);
	}

	public static int sixes(int[] values) {
		return sumAllValuesOf(6, values);
	}

	public static int score_pair(int[] values) {
		Map<Integer, Integer> occurences = getOccurencesByValue(values);
		Optional<Integer> score = occurences.entrySet().stream().filter(e -> e.getValue().intValue() == 2)
				.map(t -> t.getKey()).max(Comparator.naturalOrder());

		return score.isPresent() ? score.get().intValue() * 2 : 0;

	}

	public static int two_pair(int[] values) {
		int score = 0;
		Map<Integer, Integer> occurences = getOccurencesByValue(values);
		long occurenceOfTwo = occurences.entrySet().stream().filter(e -> e.getValue().intValue() >= 2).count();
		if (occurenceOfTwo == 2) {
			score = occurences.entrySet().stream().filter(e -> e.getValue().intValue() >= 2).map(t -> t.getKey())
					.reduce((a, b) -> a + b).get();
		}
		return score * 2;
	}

	public static int four_of_a_kind(int[] values) {
		int score = 0;
		Map<Integer, Integer> occurences = getOccurencesByValue(values);

		long numberOfDiceWith4TimesSameNumber = occurences.entrySet().stream().filter(e -> e.getValue().intValue() >= 4)
				.count();

		if (numberOfDiceWith4TimesSameNumber >= 1) {
			score = occurences.entrySet().stream().filter(e -> e.getValue().intValue() >= 4).mapToInt(t -> t.getKey())
					.findFirst().getAsInt() * 4;

		}

		return score;
	}

	public static int three_of_a_kind(int[] values) {
		int score = 0;
		Map<Integer, Integer> occurences = getOccurencesByValue(values);
		long numberOfDiceWith3AtLeastTimesSameNumber = occurences.entrySet().stream()
				.filter(e -> e.getValue().intValue() >= 3).count();

		if (numberOfDiceWith3AtLeastTimesSameNumber >= 1) {
			score = occurences.entrySet().stream().filter(e -> e.getValue().intValue() >= 3).mapToInt(t -> t.getKey())
					.findFirst().getAsInt() * 3;
		}

		return score;
	}

	public static int smallStraight(int[] values) {
		boolean hasAllTheDistinctValues = Arrays.stream(values).distinct().count() == values.length;
		int score = 0;
		if (hasAllTheDistinctValues) {
			score = Arrays.stream(values).distinct().sum();
		}
		return score;

	}

	public static int largeStraight(int[] values) {
		int score = 0;
		int[] largeStraight = { 2, 3, 4, 5, 6 };
		Arrays.sort(largeStraight);
		Arrays.sort(values);
		if (Arrays.equals(values, largeStraight)) {
			score = Arrays.stream(values).sum();
		}
		return score;
	}

	public static int fullHouse(int[] values) {
		int score = 0;
		Map<Integer, Integer> occurences = getOccurencesByValue(values);
		boolean hasTwoOfKind = occurences.entrySet().stream().filter(e -> e.getValue().intValue() == 2).count() > 0;
		boolean hasThreeOfKind = occurences.entrySet().stream().filter(e -> e.getValue().intValue() == 3).count() > 0;
		if (hasTwoOfKind && hasThreeOfKind) {
			score = occurences.entrySet().stream().mapToInt(k -> k.getKey() * k.getValue()).sum();
		}
		return score;

	}

	private static Map<Integer, Integer> getOccurencesByValue(int[] values) {
		Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
		for (int i = 0; i < values.length; i++) {
			if (occurences.get(values[i]) == null) {
				occurences.put(values[i], 1);
			} else {
				occurences.put(values[i], occurences.get(values[i]).intValue() + 1);
			}

		}
		return occurences;
	}

	private static int sumAllValuesOf(Integer valueToSum, int[] values) {
		return Arrays.stream(values).filter(t -> t == valueToSum).sum();

	}
}
