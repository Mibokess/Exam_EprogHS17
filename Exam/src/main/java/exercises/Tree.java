package exercises;

import java.util.List;

/**
 * Created by mikeb on 04-Dec-18
 */
public class Tree {
	private Node root;

	public Tree(int rootValue) {
		this.root = new LeafNode(rootValue);
	}

	public void insert(int value, String path) {
		root = insert(value, path, root);
	}

	private Node insert(int value, String path, Node position) {
		if (path.length() <= 0) {
			return new LeafNode(value);
		}

		if (position == null || position instanceof LeafNode) {
			position = new InnerNode(null, null, null);
		}

		switch (path.charAt(0)) {
			case 'L':
				((InnerNode) position).setL(insert(value, path.substring(1), ((InnerNode) position).getL()));
				break;
			case 'M':
				((InnerNode) position).setM(insert(value, path.substring(1), ((InnerNode) position).getM()));
				break;
			case 'R':
				((InnerNode) position).setR(insert(value, path.substring(1), ((InnerNode) position).getR()));
		}

		return position;
	}

	public int lookUp(String path) {
		// Todo: Implement
		return 43;
	}

	public List<Integer> toList() {
		// Todo: Implement
		return null;
	}
}

interface Node {}

class LeafNode implements Node {
	private int value;

	LeafNode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class InnerNode implements Node {
	private Node L, M, R;

	InnerNode(Node L, Node M, Node R) {
		this.L = L;
		this.M = M;
		this.R = R;
	}

	Node getL() {
		return L;
	}

	void setL(Node l) {
		L = l;
	}

	Node getM() {
		return M;
	}

	void setM(Node m) {
		M = m;
	}

	Node getR() {
		return R;
	}

	void setR(Node r) {
		R = r;
	}
}

