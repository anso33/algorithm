package pg258705;

// https://tolerblanc.github.io/programmers/programmers-mountain-tiling/  여기 봄

public class Solution_Answer {
	public int solution(int n, int[] tops) {
		int MOD = 10007;
		int[] dp1 = new int[n];
		int[] dp2 = new int[n];

		dp1[0] = 1;
		dp2[0] = (2 + tops[0]) % MOD;

		for (int i = 1; i < n; i++) {
			dp1[i] = (dp1[i - 1] + dp2[i - 1]) % MOD;
			dp2[i] = ((dp1[i - 1] * (1 + tops[i])) + (dp2[i - 1] * (2 + tops[i]))) % MOD;
		}

		return (dp1[n - 1] + dp2[n - 1]) % MOD;
	}
}
