package boj12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int A, B, time, way;
	static boolean[] visited = new boolean[100001];

	public static class Case {
		int l, time;

		public Case(int l, int time) {
			this.l = l;
			this.time = time;
		}
	}

	static void bfs() {
		Queue<Case> q = new LinkedList<>();
		q.add(new Case(A, 0));

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Case current = q.poll();
				visited[current.l] = true;

				if (way != 0 && current.l == B && time == current.time) way++;
				if (way == 0 && current.l == B) {
					time = current.time;
					way++;
				}

				if (current.l - 1 >= 0 && !visited[current.l - 1]) q.add(new Case(current.l - 1, current.time + 1));
				if (current.l + 1 <= 100000 && !visited[current.l + 1]) q.add(new Case(current.l + 1, current.time + 1));
				if (current.l * 2 <= 100000 && !visited[current.l * 2]) q.add(new Case(current.l * 2, current.time + 1));
			}
		}
	}

	public static void main	(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		bfs();

		System.out.println(time);
		System.out.println(way);
	}
}
