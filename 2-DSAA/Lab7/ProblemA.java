package Lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemA {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static Node[] nodes;
	static int size;
	static boolean isBST = false;
	static Node root;
	static boolean[] isNotRootNode;// 寻找root node

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			boolean result = process();
			out.println(result ? "Yes" : "No");
		}
		out.close();
	}

	private static boolean process() {
		root = null;
		size = input.nextInt();
		if (size == 0)
			return false;
		treeInitial();
		if (root == null)
			return false;
		return isCBT(root);
	}

	private static boolean isCBT(Node tmpNode) {
		Queue<Integer> queue = new LinkedList<>();
		/* the value of current node */
		int value = 0;
		queue.offer(tmpNode.value);
		while (!queue.isEmpty()) {
			value = queue.poll();
			if (value == -1)
				break;
			tmpNode = nodes[value];
			if (tmpNode.leftChild != null) {
				queue.offer(tmpNode.leftChild.value);
			} else {
				queue.offer(-1);
			}
			if (tmpNode.rightChild != null) {
				queue.offer(tmpNode.rightChild.value);
			} else {
				queue.offer(-1);
			}
		}
		while (!queue.isEmpty()) {
			value = queue.poll();
			if (value != -1)
				return false;
		}
		return true;
	}

	private static void treeInitial() {
		nodes = new Node[size + 1];/* 初始化，用于寻找root */
		isNotRootNode = new boolean[size + 1];
		/* initialize the value of nodes */
		for (int i = 1; i < nodes.length; i++)
			nodes[i] = new Node(i);
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