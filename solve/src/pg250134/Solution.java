package pg250134;

import java.util.*;

public class Solution {
	static int n;
	static int m;
	static List<Integer> turn = new ArrayList<>(); // 퍼즐 푸는데 필요한 turn count
	static int[][] mazeCopy;
	static int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};


	public void dfs(int[] a, int[] b, int time, int[] ae, int[] be, boolean[][] aVisited, boolean[][] bVisited) {
		int ax = a[0];
		int ay = a[1];
		int bx = b[0];
		int by = b[1];

		boolean aFinished = (ax == ae[0]) && (ay == ae[1]);
		boolean bFinished = (bx == be[0]) && (by == be[1]);

		// 둘 다 도착한 경우
		if (aFinished && bFinished) {
			turn.add(time);
			return;
		}

		aVisited[ax][ay] = true;
		bVisited[bx][by] = true;

		for (int i = 0; i < 4; i++) {
			int nax;
			int nay;
			if (aFinished) {
				nax = ax;
				nay = ay;
			} else {
				nax = ax + dir[i][0];
				nay = ay + dir[i][1];
				// 가지 못하는 경우, 1. 범위 벗어날 때  2. 이미 지나온 길  3. 벽일 때   4. a → 지금 b 위치
				if (nax < 0 || nax >= n || nay < 0 || nay >= m || aVisited[nax][nay] || mazeCopy[nax][nay] == 5 || (nax == bx && nay == by)) {
					continue;
				}
			}

			for (int j = 0; j < 4; j++) {
				int nbx;
				int nby;
				if (bFinished) {
					nbx = bx;
					nby = by;
				} else {
					nbx = bx + dir[j][0];
					nby = by + dir[j][1];
					// 가지 못하는 경우, 1. 범위 벗어날 때  2. 이미 지나온 길  3. 벽일 때  4. b → a가 가려고 하는 위치 (동시에 같은 곳 못감)
					if (nbx < 0 || nbx >= n || nby < 0 || nby >= m || bVisited[nbx][nby] || mazeCopy[nbx][nby] == 5 || (nbx == nax && nby == nay)) {
						continue;
					}
				}

				dfs(new int[]{nax, nay}, new int[]{nbx, nby}, time + 1, ae, be, aVisited, bVisited);
			}
		}

		aVisited[ax][ay] = false;
		bVisited[bx][by] = false;
	}

	public int solution(int[][] maze) {
		n = maze.length;
		m = maze[0].length;

		int[] rs = new int[2]; // red start
		int[] re = new int[2]; // red end
		int[] bs = new int[2]; // blue start
		int[] be = new int[2]; // blue end

		mazeCopy = new int[n][m];
		boolean[][] rVisited = new boolean[n][m];
		boolean[][] bVisited = new boolean[n][m];

		for (int i = 0; i < n; i++) { // 각 수레의 시작위치와 목표위치를 찾기
			for (int j = 0; j < m; j++) {
				if (maze[i][j] == 1) {
					rs = new int[]{i, j};
				} else if (maze[i][j] == 2) {
					bs = new int[]{i, j};
				} else if (maze[i][j] == 3) {
					re = new int[]{i, j};
				} else if (maze[i][j] == 4) {
					be = new int[]{i, j};
				}
				mazeCopy[i][j] = maze[i][j];
			}
		}

		dfs(rs, bs, 0, re, be, rVisited, bVisited);

		rVisited = new boolean[n][m];
		bVisited = new boolean[n][m];
		dfs(bs, rs, 0, be, re, bVisited, rVisited);

		if (turn.isEmpty()) {
			return 0;
		}
		return Collections.min(turn);
	}

	public static void main(String[] args) {
		Solution solution = new Solution();

// [[1, 0, 2], [0, 0, 0], [5, 0 ,5], [4, 0, 3]]
		int[][] maze = {
			{1, 0, 2},
			{0, 0, 0},
			{5, 0, 5},
			{4, 0, 3}
		};

		int result = solution.solution(maze);
		System.out.println("Minimum time to reach destination: " + result);
	}
}