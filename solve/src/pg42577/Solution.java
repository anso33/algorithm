package pg42577;

import java.util.HashSet;
import java.util.List;

class Solution {
	public boolean solution(String[] phone_book) {
		HashSet<String> set = new HashSet<>(List.of(phone_book));

		for (int i = 0; i < phone_book.length; i++) {
			for (int j = 0; j < phone_book[i].length(); j++) {
				if (set.contains(phone_book[i].substring(0, j)))
					return false;
			}
		}
		return true;
	}
}
