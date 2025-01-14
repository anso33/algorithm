package boj7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, H, day, visited;
	static int[][][] map;
	static int[] dx = {1, -1, 0, 0, 0, 0};
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	static Queue<Tomato> q = new LinkedList<>();

	public static class Tomato {
		int x, y, z, day;

		public Tomato(int x, int y, int z, int day) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			Tomato current = q.poll();
			for (int i = 0; i < 6; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];
				int nz = current.z + dz[i];
				day = Math.max(day, current.day);
				if (nx < 0 || nx >= M || ny < 0 || ny >= N || nz < 0 || nz >= H) continue;

				if (map[nz][ny][nx] == -1) continue;
				if (map[nz][ny][nx] == 1) continue;

				map[nz][ny][nx] = 1;
				q.add(new Tomato(nx, ny, nz, current.day + 1));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][N][M];
		visited = 0;
		day = 0;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if (map[h][i][j] == 1) q.add(new Tomato(j, i, h, 0));
				}
			}
		}

		bfs();
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[h][i][j] == 0) {
						System.out.println(-1);
						return ;
					}
				}
			}
		}

		System.out.println(day);
	}
}