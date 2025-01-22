package a_data_structure;

public class BSTreeFail {

	public static class Node {
		int key;
		String value;
		Node left;
		Node right;

		public Node(int key, String value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}

	Node root;

	public Node search(int key) {
		if (root == null) return null;

		Node node = root;
		while (node != null) {
			int compare = node.key - key;
			if (compare == 0) break;
			if (compare > 0) node = node.left;
			else node = node.right;
		}
		return node;
	}

	private void addNode(Node parent, Node node) {
		int compare = (parent.key - node.key);

		if (compare == 0) return ;
		else if (compare > 0) { // node < parent
			if (parent.left == null) parent.left = node;
			else addNode(parent.left, node);
		} else { // node > parent
			if (parent.right == null) parent.right = node;
			else addNode(parent.right, node);
		}
	}

	public void add(int key, String value) {
		if (root == null) {
			root = new Node(key, value, null, null);
		} else {
			addNode(root, new Node(key, value, null, null));
		}
	}

	public boolean remove(int key) {
		if (root == null) return false;

		// 검색해서 KEY에 해당하는 node 찾기
		Node parent = null;
		boolean isLeft = false; // node가 parent의 어느쪽 자식인지 알아야함
		Node node = root;
		while (node != null) {
			int compare = node.key - key;
			if (compare == 0) break;

			parent = node;
			if (compare > 0) {
				node = node.left;
				isLeft = true;
			}
			else node = node.right;
		}
		if (node == null) return false;

		// node의 경우의 수
		// node가 자식이 없을 때
		if (node.left == null && node.right == null) {
			if (isLeft) parent.left = null;
			else parent.right = null;
			return true;
		}
		// node가 자식이 하나일 때
		else if ((node.left != null && node.right == null) && (node.left == null && node.right != null)) {
			Node child = (node.left == null) ? node.left : node.right;
			if (isLeft) parent.left = child;
			else parent.right = child;
			return true;
		}
		// node가 자식이 둘일 때
		else {
			Node child = node.left;
			Node childP = node;
			while (child.right != null) {
				childP = child;
				child = child.right;   // node 미만 중 가장 큰 값
			}
			node.key = child.key;
			node.value = child.value;
			if (child.left == null && child.right == null) {
				childP.right = null;
			}
			// node가 자식이 하나일 때
			else {
				Node childC;
				if (child.left != null) {
					childC = child.left;
					childP.left = childC;
				} else {
					childC = child.right;
					childP.right = childC;
				}
			}
			return true;
		}
	}

	// public void print() {
	//
	// }
	public void print() {
		printInOrder(root);
	}

	private void printInOrder(Node node) {
		if (node == null) return;
		printInOrder(node.left);
		System.out.println("Key: " + node.key + ", Value: " + node.value);
		printInOrder(node.right);
	}

	public static void main(String[] args) {
		// BSTree 생성
		BSTreeFail tree = new BSTreeFail();

		// 노드 삽입
		tree.add(10, "Ten");
		tree.add(5, "Five");
		tree.add(15, "Fifteen");
		tree.add(3, "Three");
		tree.add(7, "Seven");
		tree.add(12, "Twelve");
		tree.add(18, "Eighteen");

		// 트리 출력 (삽입 후 상태)
		System.out.println("Tree created:");
		tree.print();

		// 검색 테스트
		System.out.println("\nSearch for keys:");
		System.out.println("Key 10: " + getSearchResult(tree, 10));
		System.out.println("Key 5: " + getSearchResult(tree, 5));
		System.out.println("Key 20: " + getSearchResult(tree, 20)); // 존재하지 않는 값

		// 삭제 테스트
		System.out.println("\nRemove nodes:");
		System.out.println("Remove key 5 (one child): " + removeAndPrint(tree, 5));
		System.out.println("Tree after removals:");
		tree.print();
		System.out.println("\nRemove key 15 (two children): " + removeAndPrint(tree, 15));
		System.out.println("Tree after removals:");
		tree.print();
		System.out.println("\nRemove key 20 (non-existent): " + removeAndPrint(tree, 20));
		System.out.println("Tree after removals:");
		tree.print();

		// 트리 출력 (삭제 후 상태 확인)
		System.out.println("\nTree after removals:");
		tree.print();
	}

	private static String getSearchResult(BSTreeFail tree, int key) {
		BSTreeFail.Node node = tree.search(key);
		if (node != null) {
			return "Found - Value: " + node.value;
		} else {
			return "Not Found";
		}
	}

	private static String removeAndPrint(BSTreeFail tree, int key) {
		boolean result = tree.remove(key);
		return result ? "Success - Key " + key + " removed" : "Failed - Key " + key + " not found";
	}
}
