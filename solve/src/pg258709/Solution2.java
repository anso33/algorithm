package pg258709;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
	static int lowerBound(int[] arr, int k) {
		int s = 0;
		int e = arr.length;
		int m;

		while (e - s > 0) {
			m = (s + e) / 2;
			if (arr[m] < k)
				s = m + 1;
			else
				e = m;
		}

		return e;
	}

	static int upperBound(int[] arr, int k) {
		int s = 0;
		int e = arr.length;
		int m;

		while (e - s > 0) {
			m = (s + e) / 2;
			if (arr[m] <= k)
				s = m + 1;
			else
				e = m;
		}

		return e;
	}

	public static int[][] generateDiceSets(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = i + 1;
		}

		List<int[]> result = new ArrayList<>();
		int n = arr.length;

		for (int i = 0; i < (1 << n); i++) {
			List<Integer> comb = new ArrayList<>();

			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					comb.add(arr[j]);
				}
			}

			if (comb.size() == size / 2) {
				result.add(comb.stream().mapToInt(Integer::intValue).toArray());
			}
		}

		return result.toArray(new int[result.size()][]);
	}

	private int[] calculateSums(int[] diceSet, int[][] dice) {
		int diceSetSize = diceSet.length;
		int numberOfDiceSum = (int)Math.pow(6, diceSetSize);
		int[] sums = new int[numberOfDiceSum];

		for (int i = 0; i < numberOfDiceSum; i++) {
			int sum = 0;
			int temp = i;

			for (int j = 0; j < diceSetSize; j++) {
				int diceIndex = diceSet[j] - 1;
				int diceValueIndex = temp % 6;
				sum += dice[diceIndex][diceValueIndex];
				temp /= 6;
			}
			sums[i] = sum;
		}
		return sums;
	}

	public int setWinCounts(int[] ADiceSum, int[] BDiceSum) {
		Arrays.sort(ADiceSum);
		Arrays.sort(BDiceSum);

		int u, l;
		int firstWinCount = 0;
		int drawCount = 0;
		int secondWinCount = 0;
		for (int i : ADiceSum) {
			l = lowerBound(BDiceSum, i);
			u = upperBound(BDiceSum, i);

			firstWinCount += l;
			drawCount += u - l;
		}
		secondWinCount = BDiceSum.length * ADiceSum.length - firstWinCount - drawCount;
		System.out.print(" firstWinCount: " + firstWinCount);
		System.out.print(" drawCount: " + drawCount);
		System.out.println(" secondWinCount: " + secondWinCount);

		if (firstWinCount >= secondWinCount) {
			return firstWinCount;
		}
		return -1 * secondWinCount;
	}

	public int[] solution(int[][] dice) {
		int[][] diceSets = generateDiceSets(dice.length);

		int[] bestDiceSet = null;
		int maxWinCount = 0;

		for (int i = 0; i < diceSets.length / 2; i++) {
			int[] AdiceSum = calculateSums(diceSets[i], dice);
			int[] BdiceSum = calculateSums(diceSets[diceSets.length - i - 1], dice);

			int winCount = setWinCounts(AdiceSum, BdiceSum);
			if (winCount < 0) {
				winCount = -1 * winCount;
				if (winCount > maxWinCount) {
					maxWinCount = winCount;
					bestDiceSet = diceSets[diceSets.length - i - 1];
				}
			} else {
				if (winCount > maxWinCount) {
					maxWinCount = winCount;
					bestDiceSet = diceSets[i];
				}
			}
		}

		return bestDiceSet;
	}

	public static void main(String[] args) {
		// // [[40, 41, 42, 43, 44, 45], [43, 43, 42, 42, 41, 41], [1, 1, 80, 80, 80, 80], [70, 70, 1, 1, 70, 70]]
		// int[][] dice = {
		// 	{40, 41, 42, 43, 44, 45},
		// 	{43, 43, 42, 42, 41, 41},
		// 	{1, 1, 80, 80, 80, 80},
		// 	{70, 70, 1, 1, 70, 70}
		// };

		// // [[1, 2, 3, 4, 5, 6], [3, 3, 3, 3, 4, 4], [1, 3, 3, 4, 4, 4], [1, 1, 4, 4, 5, 5]]
		// int[][] dice = {
		// 	{1, 2, 3, 4, 5, 6},
		// 	{3, 3, 3, 3, 4, 4},
		// 	{1, 3, 3, 4, 4, 4},
		// 	{1, 1, 4, 4, 5, 5}
		// };

		// 	[[1, 2, 3, 4, 5, 6], [2, 2, 4, 4, 6, 6]]
		int[][] dice = {
			{1, 2, 3, 4, 5, 6},
			{2, 2, 4, 4, 6, 6}
		};

		Solution2 solution = new Solution2();
		int[] result = solution.solution(dice);

		for (int i : result) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}