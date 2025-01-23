package pg258711;

import java.util.*;

class Solution {
	public int[] solution(int[][] edges) {
		int[] answer = {0, 0, 0, 0};

		Map<Integer, int[]> info = new HashMap<>(); // int[0] -> in, int[1] -> out
		for(int[] e : edges) {
			if (!info.containsKey(e[0])) info.put(e[0], new int[2]);
			if (!info.containsKey(e[1])) info.put(e[1], new int[2]);
			info.get(e[0])[1]++;
			info.get(e[1])[0]++;
		}

		Set<Map.Entry<Integer, int[]>> entrySet = info.entrySet();
		for (Map.Entry<Integer, int[]> element : entrySet) {
			int[] v = element.getValue();
			if (v[0] == 0 && v[1] > 1) answer[0] = element.getKey();
			if (v[0] >= 1 && v[1] == 0) answer[2]++;
			if (v[0] >= 2 && v[1] >= 2) answer[3]++;
		}
		answer[1] = info.get(answer[0])[1] - answer[2] - answer[3];
		return answer;
	}

	public static void main(String[] args) {
		// [[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]
		int[][] edges = {
			{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}
		};
		int[] result = new Solution().solution(edges);
		System.out.println(Arrays.toString(result));
	}
}
