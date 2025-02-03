package pg86971;

public class Solution {

	static private int[] parents;

	public static int find(int i) {
		if (parents[i] == i) return i;
		return parents[i] = find(parents[i]);
	}

	public static void union(int v1, int v2) {
		v1 = find(v1);
		v2 = find(v2);

		if (v1 != v2) {
			if (v1 > v2) parents[v1] = v2;
			else parents[v2] = v1;
		}
	}

	public static int findDiff(int n) {
		int a = 0, b = 0;
		for (int i = 1; i <= n; i++)
			if (find(parents[i]) == 1) a++;

		b = n - a;
		return Math.abs(a - b);
	}

	public static int solution(int n, int[][] wires) {
		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < wires.length; i++) {
			parents = new int[n + 1];
			for (int p = 0; p <= n; p++) parents[p] = p;

			for (int j = 0; j < wires.length; j++) {
				if (i == j) continue;
				int v1 = wires[j][0], v2 = wires[j][1];

				union(v1, v2);
			}

			int temp = findDiff(n);
			answer = Math.min(answer, temp);
		}

		return answer;
	}



	public static void main(String[] args) {
		int n = 9;
		int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
		System.out.println(new Solution().solution(n, wires));
	}
}
