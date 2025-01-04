package boj10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	static int N;
	static char[][] map;
	static char[][] mapCB;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
	static boolean[][] visitedCB;

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		map = new char[N][N];
		mapCB = new char[N][N];
		visited = new boolean[N][N];
		visitedCB = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = new StringTokenizer(br.readLine()).nextToken();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				map[i][j] = c;
				if (c == 'G') mapCB[i][j] = 'R';
				else mapCB[i][j] = c;
			}
		}
	}

	static void bfs(int x, int y, char[][] map, boolean[][] visited) {
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

	public static void main(String[] args) throws IOException {
		input();

		int general = 0;
		int colorBlind = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, map, visited);
					general++;
				}
				if (!visitedCB[i][j]) {
					bfs(i, j, mapCB, visitedCB);
					colorBlind++;
				}
			}
		}

		System.out.println(general + " " + colorBlind);
	}
}
