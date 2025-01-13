package boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int M, N, day, visited;
	static int[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static Queue<Tomato> q = new LinkedList<>();

	public static class Tomato {
		int x, y, day;

		public Tomato(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			Tomato current = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				day = Math.max(day, current.day);
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

				if (map[nx][ny] == -1) continue;
				if (map[nx][ny] == 1) continue;

				map[nx][ny] = 1;
				visited |= 1 << (M * ny + nx);
				q.add(new Tomato(nx, ny, current.day + 1));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = 0;
		day = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.add(new Tomato(i, j, 0));
					visited |= 1 << (M * i + j);
				}
				if (map[i][j] == -1) visited |= 1 << (M * i + j);
			}
		}

		bfs();
		System.out.println(visited);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					System.out.println(-1);
					return ;
				}
			}
		}

		System.out.println(day);
	}
}
