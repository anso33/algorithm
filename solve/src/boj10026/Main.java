package boj10026;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N;
	static char[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
	static boolean[][] visitedCB;

	static void input() {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		map = new char[N][N];
		visited = new boolean[N][N];
		visitedCB = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = scan.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
	}

	static void generalBfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});

		while (!q.isEmpty()) {
			int[] current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visited[nx][ny]) continue;
				if (map[nx][ny] != map[current[0]][current[1]]) continue;

				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
	}

	static void colorBlindBfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});

		while (!q.isEmpty()) {
			int[] current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visitedCB[nx][ny]) continue;

				if (map[nx][ny] == 'B' && map[nx][ny] != map[current[0]][current[1]]) continue;
				if (map[nx][ny] != 'B' && map[current[0]][current[1]] == 'B') continue;

				visitedCB[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
	}

	public static void main(String[] args) {
		input();

		int general = 0;
		int colorBlind = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					generalBfs(i, j);
					general++;
				}
				if (!visitedCB[i][j]) {
					colorBlindBfs(i, j);
					colorBlind++;
				}
			}
		}

		System.out.println(general + " " + colorBlind);
	}
}
