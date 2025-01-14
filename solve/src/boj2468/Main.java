package boj2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> heights = new ArrayList<>();
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};

	static void dfs(int x, int y, int rain) {
		Deque<int[]> s = new ArrayDeque<>();
		s.push(new int[] {x, y});

		while (!s.isEmpty()) {
			int[] current = s.pop();
			visited[current[0]][current[1]] = true;

			for (int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visited[nx][ny]) continue;

				if (map[nx][ny] >= rain) s.add(new int[] {nx, ny});
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (!heights.contains(map[i][j])) {
					heights.add(map[i][j]);
				}
			}
		}

		int max = 1;
		for (Integer height : heights) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] >= height) {
						count++;
						dfs(i, j, height);
					}
				}
			}
			System.out.println("height: " + height + ", count: " + count);
			max = Math.max(max, count);
		}
		System.out.println(max);
	}
}
