package b_sorting.leetcode56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
	public int[][] merge(int[][] intervals) {
		List<int[]> arr = new ArrayList<>();
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

		for (int[] i : intervals) {
			if (!arr.isEmpty() && i[0] <= arr.get(arr.size() - 1)[1]) {
				arr.get(arr.size() - 1)[1] = Math.max(arr.get(arr.size() - 1)[1], i[1]);
			} else {
				arr.add(i);
			}
		}

		return arr.toArray(new int[arr.size()][]);
	}
}
