package pg150366;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static Pos[][] merged = new Pos[51][51];
	static String[][] value = new String[51][51];

	public String[] solution(String[] commands) {
		for (int i = 1; i <= 50; i++) {
			for (int j = 1; j <= 50; j++) {
				merged[i][j] = new Pos(i, j);
			}
		}

		Queue<String> res = new LinkedList<>();

		for (int i = 0; i < commands.length; i++) {
			String command = commands[i];
			StringTokenizer st = new StringTokenizer(command);

			String type = st.nextToken();

			switch (type) {
				case "UPDATE" -> {
					if (st.countTokens() == 3) {
						int r = Integer.parseInt(st.nextToken());
						int c = Integer.parseInt(st.nextToken());
						String str = st.nextToken();
						value[merged[r][c].r][merged[r][c].c] = str;
					}

					if (st.countTokens() == 2) {
						String str1 = st.nextToken();
						String str2 = st.nextToken();

						for (int r = 1; r <= 50; r++) {
							for (int c = 1; c <= 50; c++) {
								if (Objects.equals(value[r][c], str1)) value[r][c] = str2;
							}
						}
					}
				}
				case "MERGE" -> {
					int r1 = Integer.parseInt(st.nextToken());
					int c1 = Integer.parseInt(st.nextToken());
					int r2 = Integer.parseInt(st.nextToken());
					int c2 = Integer.parseInt(st.nextToken());

					int newR1 = merged[r1][c1].r;
					int newC1 = merged[r1][c1].c;
					int newR2 = merged[r2][c2].r;
					int newC2 = merged[r2][c2].c;

					for (int j = 1; j <= 50; j++) {
						for (int k = 1; k <= 50; k++) {
							if (merged[j][k].r == newR2 && merged[j][k].c == newC2) merged[j][k] = new Pos(newR1, newC1);
						}
					}

					if (newR1 != newR2 || newC1 != newC2) {
						if (value[newR1][newC1] != null && value[newR2][newC2] == null) value[newR2][newC2] = value[newR1][newC1];
						else if (value[newR1][newC1] == null && value[newR2][newC2] != null) value[newR1][newC1] = value[newR2][newC2];
						else if (value[newR1][newC1] != null && value[newR2][newC2] != null) value[newR2][newC2] = value[newR1][newC1];
					}
				}

				case "UNMERGE" -> {
					int r = Integer.parseInt(st.nextToken());
					int c = Integer.parseInt(st.nextToken());

					int newR = merged[r][c].r;
					int newC = merged[r][c].c;
					String str = value[newR][newC];
					for (int j = 1; j <= 50; j++) {
						for (int k = 1; k <= 50; k++) {
							if (merged[j][k].r == newR && merged[j][k].c == newC) {
								merged[j][k] = new Pos(j, k);
								value[j][k] = null;
							}
						}
					}
					value[r][c] = str;
				}
				case "PRINT" -> {
					int r = Integer.parseInt(st.nextToken());
					int c = Integer.parseInt(st.nextToken());

					int newR = merged[r][c].r;
					int newC = merged[r][c].c;
					String str = value[newR][newC];

					if (str == null) res.add("EMPTY");
					else res.add(str);
				}

			}
		}

		String[] answer = new String[res.size()];
		res.toArray(answer);
		return answer;
	}
}
