package Lab7;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ProblemB {
	static Scanner input = new Scanner(System.in);
	static Node[] nodes;
	static int size;
	static boolean isBST = false;
	static Node root;
	static ArrayList<Integer> inOrder;
	static boolean[] isNotRootNode;// 寻找root node

	public static void main(String[] args) {
		int case_num = input.nextInt();
		for (int i = 0; i < case_num; i++) {
			System.out.printf("Case #%d: ", i + 1);
			System.out.println(process() ? "YES" : "NO");
		}
	}

	private static boolean process() {
		root = null;
		size = input.nextInt();
		if (size == 0)
			return false;
		nodes = new Node[size + 1];/* 初始化，用于寻找root */
		isNotRootNode = new boolean[size + 1];
		inOrder = new ArrayList<>(size);
		treeInitial();
		/* 建树过程中判断局部是否满足要求 */
		if (!isBST)
			return false;
		/* 得到root */
		for (int i = 1; i < isNotRootNode.length; i++) {
			if (!isNotRootNode[i]) {
				root = nodes[i];
				break;
			}
		}
		if (root == null)
			return false;
		/* 局部已经满足，使用中缀遍历，若为升序数组，则满足 */
		inOrderByStack(root);
		for (int i = 0; i < inOrder.size() - 1; i++) {
			if (inOrder.get(i + 1) < inOrder.get(i)) {
				return false;
			}
		}
		return true;
	}

	private static void treeInitial() {
		/* initialize the value of nodes */
		for (int i = 1; i < nodes.length; i++)
			nodes[i] = new Node(input.nextInt());
		int p, c;
		isBST = true;
		/* 初始化树，若出现两个以上子节点，返回false */
		for (int i = 0; i < size - 1; i++) {
			p = input.nextInt();
			c = input.nextInt();
			isNotRootNode[c] = true;
			if (nodes[c].value < nodes[p].value) {
				/* 尝试放入左子节点 */
				if (nodes[p].leftChild == null)
					nodes[p].leftChild = nodes[c];
				else
					isBST = false;
			} else if (nodes[c].value > nodes[p].value) {
				/* 尝试放入右子节点 */
				if (nodes[p].rightChild == null)
					nodes[p].rightChild = nodes[c];
				else
					isBST = false;
			} else/* 相等则错误 */
				isBST = false;
		}
	}

	private static void inOrderByStack(Node node) {
		Stack<Node> stack = new Stack<Node>();
		Node current = node;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.leftChild;
			}
			if (!stack.isEmpty()) {
				current = stack.pop();
				inOrder.add(current.value);
				current = current.rightChild;
			}
		}
	}

	static class Node {
		int value;
		Node leftChild;
		Node rightChild;

		Node(int value) {
			this.value = value;
		}

		public void display() {
			System.out.print(this.value + "\t");
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
}
