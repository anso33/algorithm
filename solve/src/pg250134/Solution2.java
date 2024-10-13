package pg250134;

import java.util.ArrayDeque;

public class Solution2 {
	static int[][] dir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

	private static class State {
		int x1, y1, v1, x2, y2, v2, s;

		public State(int x1, int y1, int v1, int x2, int y2, int v2, int s) {
			this.x1 = x1;
			this.y1 = y1;
			this.v1 = v1;
			this.x2 = x2;
			this.y2 = y2;
			this.v2 = v2;
			this.s = s;
		}
	}

	public int solution(int[][] maze) {
		int n = maze.length, m = maze[0].length;
		State start = new State(0, 0, 0, 0, 0, 0, 0);

		int[] re = new int[2]; // red end
		int[] be = new int[2]; // blue end

		for (int i = 0; i < n; i++) { // 각 수레의 시작위치와 목표위치를 찾기
			for (int j = 0; j < m; j++) {
				if (maze[i][j] == 1) {
					start.x1 = i;
					start.y1 = j;
					start.v1 = 1 << (m * i + j);
				} else if (maze[i][j] == 2) {
					start.x2 = i;
					start.y2 = j;
					start.v2 = 1 << (m * i + j);
				} else if (maze[i][j] == 3) {
					re = new int[] {i, j};
				} else if (maze[i][j] == 4) {
					be = new int[] {i, j};
				}
			}
		}

		ArrayDeque<State> deque = new ArrayDeque<>();
		deque.push(start);

		while (!deque.isEmpty()) {
			State s = deque.pollFirst();

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					int nx1, ny1;
					if (s.x1 == re[0] && s.y1 == re[1]) {
						nx1 = s.x1;
						ny1 = s.y1;
					} else {
						nx1 = s.x1 + dir[i][0];
						ny1 = s.y1 + dir[i][1];
					}

					int nx2, ny2;
					if (s.x2 == be[0] && s.y2 == be[1]) {
						nx2 = s.x2;
						ny2 = s.y2;
					} else {
						nx2 = s.x2 + dir[j][0];
						ny2 = s.y2 + dir[j][1];
					}

					// 가지 못하는 경우, 1. 범위 벗어날 때 3. 벽일 때  4. b → a가 가려고 하는 위치 (동시에 같은 곳 못감)
					if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= m || nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= m) continue;
					if (maze[nx1][ny1] == 5 || maze[nx2][ny2] == 5) continue;
					if (nx1 == s.x2 && ny1 == s.y2 && nx2 == s.x1 && ny2 == s.y1) continue;
					if (nx1 == nx2 && ny1 == ny2) continue;

					if (nx1 == re[0] && ny1 == re[1] && nx2 == be[0] && ny2 == be[1]) return s.s + 1;

					int v1 = 1 << (m * nx1 + ny1);
					int v2 = 1 << (m * nx2 + ny2);
					if (!(s.x1 == re[0] && s.y1 == re[1]) && ((s.v1 & v1) != 0)) continue;
					if (!(s.x2 == be[0] && s.y2 == be[1]) && ((s.v2 & v2) != 0)) continue;

					deque.add(new State(nx1, ny1, s.v1 | v1, nx2, ny2, s.v2|v2, s.s + 1));
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		Solution2 solution = new Solution2();

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