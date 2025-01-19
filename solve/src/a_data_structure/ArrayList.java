package a_data_structure;

public class ArrayList {
	int[] value;
	int length;

	public int find(int v) {
		for (int i = 0; i < length; i++) {
			if (value[i] == v) return i;
		}
		return -1;
	}

	public void add(int v) {
		value[length] = v;
		length++;
	}

	public void delete(int v) {
		int index = find(v);
		if (index == -1) return;
		for (int i = index; i < length - 1; i++) {
			value[i] = value[i + 1];
		}
		length--;
	}
}
