package Lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ProblemC {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static Node[] nodes;
	static int size;
	static boolean isBST = false;
	static Node root;
	static ArrayList<Integer> inOrder;
	static boolean[] isNotRootNode;// 寻找root node

	public static void main(String[] args) {
		int case_num = input.nextInt();
		for (int i = 0; i < case_num; i++) {
			out.println(isAVL() ? "Yes" : "No");
		}
		out.close();
	}

	private static boolean isAVL() {
		createBT();
		if (isBST() && isBalanced())
			return true;
		return false;
	}

	private static boolean isBalanced() {
		if (postOrderByStack(root))
			return false;
		return true;
	}

	private static void createBT() {
		root = null;
		size = input.nextInt();
		nodes = new Node[size + 1];/* 初始化，用于寻找root */
		isNotRootNode = new boolean[size + 1];
		inOrder = new ArrayList<>(size);
		treeInitial();
	}

	private static boolean isBST() {
		if (root == null)
			return false;
		/* 局部已经满足，使用中缀遍历，若为升序数组，则满足 */
		/* 是否是二叉搜索树 */
		inOrderByStack(root);
		for (int i = 0; i < inOrder.size() - 1; i++) {
			if (inOrder.get(i + 1) <= inOrder.get(i)) {
				return false;
			}
		}
		return true;
	}

	private static void treeInitial() {
		/* initialize the value of nodes */
		nodes = new Node[size + 1];/* 初始化，用于寻找root */
		for (int i = 1; i < nodes.length; i++)
			nodes[i] = new Node(input.nextInt());
		isNotRootNode = new boolean[size + 1];
		int l, r;
		/* 初始化树，若出现两个以上子节点，返回false */
		for (int i = 1; i < nodes.length; i++) {
			l = input.nextInt();
			r = input.nextInt();
			isNotRootNode[l] = true;
			isNotRootNode[r] = true;
			/* 已经保证是一颗二叉树 */
			nodes[i].leftChild = l == 0 ? null : nodes[l];
			nodes[i].rightChild = r == 0 ? null : nodes[r];
		}
		/* 得到root */
		for (int i = 1; i < isNotRootNode.length; i++) {
			if (!isNotRootNode[i]) {
				root = nodes[i];
				break;
			}
		}
	}

	private static void inOrderByStack(Node node) {
//		System.out.println("In");
		Stack<Node> stack = new Stack<Node>();
		Node current = node;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.leftChild;
			}
			if (!stack.isEmpty()) {
				current = stack.pop();
//				current.display();
				inOrder.add(current.value);
				current = current.rightChild;
			}
		}
	}

	public static boolean postOrderByStack(Node node) {
//		System.out.println("Post");
		Stack<Node> stack = new Stack<Node>();
		Node current = node;
		Node preNode = null;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.leftChild;
			}

			if (!stack.isEmpty()) {
				current = stack.peek().rightChild;
				if (current == null || current == preNode) {
					current = stack.pop();

					if (getDepth(current)) {
						return true;
					}
//					current.display();
					preNode = current;
					current = null;
				}
			}
		}
		return false;
	}

	public static boolean getDepth(Node node) {
		if (node.leftChild != null) {
			node.leftDepth = Math.max(node.leftChild.leftDepth, node.leftChild.rightDepth) + 1;
		}
		if (node.rightChild != null) {
			node.rightDepth = Math.max(node.rightChild.leftDepth, node.rightChild.rightDepth) + 1;
		}
		if (Math.abs(node.leftDepth - node.rightDepth) > 1)
			return true;
		else
			return false;
	}

	static class Node {
		int value;
		int leftDepth;
		int rightDepth;
		Node leftChild;
		Node rightChild;

		Node(int value) {
			this.leftDepth = 0;
			this.rightDepth = 0;
			this.value = value;
		}

		public void display() {
//			System.out.print(this.value + "\t");
			System.out.printf("[ Node: value = %d\t leftDepth = %d\t rightDepth = %d ]\n", this.value, this.leftDepth,
					this.rightDepth);
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char[] nextCharArray() {
			return next().toCharArray();
		}

		// public boolean hasNext() {
		// try {
		// return reader.ready();
		// } catch(IOException e) {
		// throw new RuntimeException(e);
		// }
		// }
		public boolean hasNext() {
			try {
				String string = reader.readLine();
				if (string == null) {
					return false;
				}
				tokenizer = new StringTokenizer(string);
				return tokenizer.hasMoreTokens();
			} catch (IOException e) {
				return false;
			}
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public BigDecimal nextBigDecinal() {
			return new BigDecimal(next());
		}
	}
}
/*
 * 对于任意一个节点，它的左右子树高度差不超过1
 */