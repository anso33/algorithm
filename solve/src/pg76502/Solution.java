package pg76502;

import java.util.*;

// 완탐
class Solution {
	public int solution(String s) {
		int answer = 0;
		Map<Character, Character> pair = new HashMap<>();
		pair.put('}', '{');
		pair.put(')', '(');
		pair.put(']', '[');

		for (int i = 0; i < s.length(); i++) {
			String now = s.substring(i, s.length()) + s.substring(0, i);
			Deque<Character> stack = new ArrayDeque<>();
			boolean isRight = true;
			for (int j = 0; j < now.length(); j++) {
				if (now.charAt(j) == '{' || now.charAt(j) == '[' || now.charAt(j) == '(') {
					stack.add(now.charAt(j));
				} else {
					if (stack.isEmpty() || pair.get(now.charAt(j)) != stack.removeLast()) {
						isRight = false;
						break;
					}
				}
			}
			if (isRight && stack.isEmpty()) answer++;
		}
		return answer;
	}

	public static void main(String[] args) {
		String s = "(){{";
		int result = new Solution().solution(s);
		System.out.println(result);
	}
}
