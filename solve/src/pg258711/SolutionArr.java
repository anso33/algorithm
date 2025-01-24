package pg258711;

import java.util.*;

class SolutionArr {
	public int[] solution(int[][] edges) {
		int[] answer = {0, 0, 0, 0};

		int[] in = new int[1_000_000];
		int[] out = new int[1_000_000];
		for(int[] e : edges) {
			out[e[0]]++;
			in[e[1]]++;
		}

		for (int i = 1; i < 1_000_000; i++) {
			if (in[i] == 0 && out[i] == 0) continue;

			if (in[i] == 0 && out[i] > 1) answer[0] = i;
			if (in[i] >= 1 && out[i] == 0) answer[2]++;
			if (in[i] >= 2 && out[i] >= 2) answer[3]++;
		}
		answer[1] = out[answer[0]] - answer[2] - answer[3];
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
