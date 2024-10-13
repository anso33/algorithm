package pg340210;

import java.util.*;

class Solution {
	public String[] solution(String[] expressions) {
		List<String> answer = new ArrayList<>();
		boolean[] cb = new boolean[10];
		Arrays.fill(cb, true);

		int minBase = 2;
		for (int i = 0; i < expressions.length; i++) { // 들어온 수식 순회
			String[] e = expressions[i].split(" "); // 한 개의 수식을 쪼개기

			for (int j = 0; j <= 4; j += 2) { // 0번째, 2번째, 4번째 원소에 대해 반복
				if (e[j].equals("X")) continue;
				for (char c : e[j].toCharArray()) { // 각 문자에 대해 반복
					minBase = Math.max(minBase, Character.getNumericValue(c) + 1);
				}
			}

			for (int b = 0; b < minBase; b++) {
				cb[b] = false;
			}

			if (e[4].equals("X")) {
				answer.add(expressions[i]);
				continue;
			}

			for (int b = minBase; b < 10; b++) { // 적용 가능한 수식인지 계산해보기
				if (!cb[b]) continue;
				int e0 = Integer.parseInt(e[0], b); // 10진법으로 바꾼,
				int e2 = Integer.parseInt(e[2], b);
				int res = e[1].equals("+") ? e0 + e2 : e0 - e2;
				int e4 = Integer.parseInt(e[4], b);
				if (res != e4) cb[b] = false;
			}
		}

		for (int i = 0; i < answer.size(); i++) {
			String[] e = answer.get(i).split(" "); // 한 개의 수식을 쪼개기
			for (int b = minBase; b < 10; b++) { // 적용 가능한 수식인지 계산해보기
				if (!cb[b]) continue;
				int e0 = Integer.parseInt(e[0], b); // 10진법으로 바꾼,
				int e2 = Integer.parseInt(e[2], b); // 10진법
				int cal = e[1].equals("+") ? e0 + e2 : e0 - e2; // 10진법 기준 계산한 값
				String calToB;
				if (cal == 0) calToB = "0";
				else {
					StringBuilder r = new StringBuilder();
					while (cal > 0) {
						r.insert(0, cal % b);
						cal /= b;
					}
					calToB = r.toString();
				}

				if (!e[4].equals("X") && !e[4].equals(calToB)) {
					e[4] = "?";
					continue;
				}
				e[4] = calToB;
			}
			answer.set(i, answer.get(i).replace("X", e[4]));
		}

		return answer.toArray(new String[0]);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		// ["14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"]
		// String[] expressions = {
		// 	"14 + 3 = 17",
		// 	"13 - 6 = X",
		// 	"51 - 5 = 44"
		// };
		// ["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"]
		String[] expressions = {
			"2 - 1 = 1",
			"2 + 2 = X",
			"7 + 4 = X",
			"5 - 5 = X"
		};
		String[] result = sol.solution(expressions);
		for (String str: result) {
			System.out.println(str);
		}
	}
}

