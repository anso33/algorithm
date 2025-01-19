package a_data_structure;

public class LinkedList {
	public static class Node {
		Node prev;
		Node next;
		int value;

		public Node(int value) {
			this.value = value;
			prev = null;
			next = null;
		}
	}

	Node root;
	Node end;
	int length;

	public int get(int value) {
		Node node = root;
		int index = 0;
		while (node.next != null) {
			if (node.value == value) return index;
			node = node.next;
			index++;
		}
		return -1;
	}

	public void add(int value) {
		Node node = new Node(value);
		node.prev = end;
		end.next = node;
		length++;
	}

	public void delete(int value) {
		Node node = root;
		while (node.next != null) {
			if (node.value == value) break;
			node = node.next;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;
		length--;
	}
}
