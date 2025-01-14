package boj9205;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Position {

		int X, Y, B;

		public Position(int x, int y, int b) {
			X = x; Y = y; B = b;
		}
	}

	static int T, N;
	static int[] posX = new int[103];
	static int[] posY = new int[103];
	static boolean[] visited = new boolean[103];
	static boolean ans;

	static boolean bfs() {
		Queue<Position> q = new LinkedList<>();
		visited[0] = true;
		q.add(new Position(posX[0], posY[0], 20));

		while (!q.isEmpty()) {
			Position current = q.poll();
			int x = current.X;
			int y = current.Y;
			int b = current.B;

			if ((x == posX[N + 1]) && (y == posY[N + 1])) {
				return true;
			}

			for (int i = 1; i < (N + 2); i++) {
				if (visited[i]) continue;
				int nx = posX[i];
				int ny = posY[i];
				int distance = Math.abs(nx - x) + Math.abs(ny - y);
				if (distance <= (b * 50)) {
					visited[i] = true;
					q.add(new Position(nx, ny, 20));
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			for (int j = 0; j < (N + 2); j++) {
				String[] inputs = br.readLine().split(" ");
				posX[j] = Integer.parseInt(inputs[0]);
				posY[j] = Integer.parseInt(inputs[1]);
			}
			ans = bfs();
			if (ans) bw.write("happy" + "\n");
			else bw.write("sad" + "\n");
		}
		bw.flush();
		bw.close();
	}
}
