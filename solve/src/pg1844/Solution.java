package pg1844;

import java.util.ArrayDeque;

class Solution {
	private final static int[] dx = {-1, 0, 1, 0};
	private final static int[] dy = {0, 1, 0, -1};

	private static class State {
		int x, y, c;

		public State(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
			System.out.println("x " + x + " | y " + y + " | c " + c);
		}
	}

	public int solution(int[][] maps) {
		int m = maps.length - 1, n = maps[0].length - 1;
		State start = new State(0, 0, 1);
		ArrayDeque<State> queue = new ArrayDeque<>();
		queue.push(start);

		while (!queue.isEmpty()) {
			State s = queue.pollFirst();
			for (int i = 0; i < 4; i++) {
				int nx, ny;
				if (s.x == m && s.y == n) { nx = s.x; ny = s.y; }
				else { nx = s.x + dx[i]; ny = s.y + dy[i]; }

				// 가려고했는데 길이 아니라서 못감
				if (nx < 0 || nx > m || ny < 0 || ny > n || maps[nx][ny] == 0) continue;
				// 도착
				if (nx == m && ny == n) {
					System.out.println("nx " + nx + " | ny " + ny + " | s.c + 1 " + (s.c + 1));
					return s.c + 1;
				}

				queue.addLast(new State(nx, ny, s.c + 1));
				maps[nx][ny] = 0;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,1],[0,0,0,0,1]]
		int[][] maps = {
			{1, 0, 1, 1, 1},
			{1, 0, 1, 0, 1},
			{1, 0, 1, 1, 1},
			{1, 1, 1, 0, 1},
			{0, 0, 0, 0, 1}
		};
		System.out.println(new Solution().solution(maps)); // 11

		// // [[1,0,1,1,1],[1,0,1,0,1],[1,0,1,1,1],[1,1,1,0,0],[0,0,0,0,1]]
		// int maps2[][] = {
		// 	{1, 0, 1, 1, 1},
		// 	{1, 0, 1, 0, 1},
		// 	{1, 0, 1, 1, 1},
		// 	{1, 1, 1, 0, 0},
		// 	{0, 0, 0, 0, 1}
		// };
		// System.out.println(new Solution().solution(maps2)); // -1
	}
}