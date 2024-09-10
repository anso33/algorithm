package pg258709;

import java.util.*;

class DiceSet {
	Set<Integer> first;
	Set<Integer> second;
	int[] diceSumsOfFirst;
	int[] diceSumsOfSecond;
	int firstWinCount;
	int drawCount;
	int secondWinCount;

	DiceSet(Set<Integer> first, Set<Integer> second) {
		this.first = first;
		this.second = second;
	}

	public void setDiceSums(int[][] dice) {
		diceSumsOfFirst = calculateSums(new ArrayList<>(first), dice);
		diceSumsOfSecond = calculateSums(new ArrayList<>(second), dice);
	}

	private int[] calculateSums(List<Integer> diceSet, int[][] dice) {
		int diceSetSize = diceSet.size();
		int numberOfDiceSum = (int) Math.pow(6, diceSetSize);
		int[] sums = new int[numberOfDiceSum];

		for (int i = 0; i < numberOfDiceSum; i++) {
			int sum = 0;
			int temp = i;

			for (int j = 0; j < diceSetSize; j++) {
				int diceIndex = diceSet.get(j) - 1;
				int diceValueIndex = temp % 6;
				sum += dice[diceIndex][diceValueIndex];
				temp /= 6;
			}
			sums[i] = sum;
		}
		return sums;
	}

	public void setWinCounts() {
		Arrays.sort(diceSumsOfFirst);
		Arrays.sort(diceSumsOfSecond);

		int i = 0;
		int previousFirstWinCount = 0;
		int previousDrawCount = 0;

		while (i < diceSumsOfFirst.length) {
			if (i > 0 && diceSumsOfFirst[i] == diceSumsOfFirst[i - 1]) {
				firstWinCount += previousFirstWinCount;
				drawCount += previousDrawCount;
				i++;
				continue;
			}

			int j = 0;
			int firstWin = 0;
			int draw = 0;
			int secondWin = 0;


			while (j < diceSumsOfSecond.length && diceSumsOfSecond[j] < diceSumsOfFirst[i]) {
				firstWin++;
				j++;
			}

			while (j < diceSumsOfSecond.length && diceSumsOfSecond[j] == diceSumsOfFirst[i]) {
				draw++;
				j++;
			}

			previousFirstWinCount = firstWin;
			previousDrawCount = draw;

			firstWinCount += firstWin;
			drawCount += draw;

			i++;
		}

		secondWinCount = diceSumsOfSecond.length * diceSumsOfFirst.length - firstWinCount - drawCount;
	}

	public int[] getFirstDiceList() {
		int[] array = first.stream().mapToInt(Integer::intValue).toArray();
		Arrays.sort(array);

		return array;
	}

	public int getFirstWinCount() {
		return firstWinCount;
	}

	public int getSecondWinCount() {
		return secondWinCount;
	}
}

public class Solution {
	public static List<DiceSet> generateDiceSets(int n) {
		List<DiceSet> diceSets = new ArrayList<>();

		List<Integer> elements = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			elements.add(i);
		}
		distributeDice(diceSets, elements, 0, new HashSet<>(), new HashSet<>());

		return diceSets;
	}

	private static void distributeDice(List<DiceSet> diceSets, List<Integer> elements, int index,
		Set<Integer> firstSet, Set<Integer> secondSet) {
		if (index == elements.size()) {
			if (firstSet.size() == secondSet.size()) {
				diceSets.add(new DiceSet(new HashSet<>(firstSet), new HashSet<>(secondSet)));
			}
			return;
		}

		Integer current = elements.get(index);

		firstSet.add(current);
		distributeDice(diceSets, elements, index + 1, firstSet, secondSet);
		firstSet.remove(current);

		secondSet.add(current);
		distributeDice(diceSets, elements, index + 1, firstSet, secondSet);
		secondSet.remove(current);
	}

	public int[] findCaseOfAisWinning(List<DiceSet> diceSets) {
		DiceSet bestDiceSet = null;

		for (DiceSet diceSet : diceSets) {
			if (diceSet.getFirstWinCount() > diceSet.getSecondWinCount()) {
				if (bestDiceSet == null || diceSet.getFirstWinCount() > bestDiceSet.getFirstWinCount()) {
					bestDiceSet = diceSet;
				}
			}
		}

		return bestDiceSet.getFirstDiceList();
	}

	public int[] solution(int[][] dice) {
		List<DiceSet> diceSets = generateDiceSets(dice.length);
		diceSets.forEach(s -> {
			s.setDiceSums(dice);
			s.setWinCounts();
		});

		return findCaseOfAisWinning(diceSets);
	}
}