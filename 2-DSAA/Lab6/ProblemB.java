package Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemB {
	static int[] nodes;
	static BinaryTree bt;
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			process();
		}
	}

	private static void process() {
		bt = new BinaryTree(1);
		bt.initiate();
		bt.preOrderTraverse();
		bt.inOrderTraverse();
		bt.postOrderTraverse();
	}

	private static class BinaryTree {
		class Node {
			int value;
			Node leftChild;
			Node rightChild;

			Node(int value) {
				this.value = value;
			}

			public void display() {
				System.out.print(this.value + " ");
			}

			@Override
			public String toString() { // TODO Auto-generated method stub
				return String.valueOf(value);
			}
		}

		private Node root = null;

		BinaryTree(int value) {
			root = new Node(value);
			root.leftChild = null;
			root.rightChild = null;
		}

		private void initiate() {
			int size = input.nextInt();
			int parent = 0;
			int child = 0;
			Node[] nodes = new Node[size + 1];
			/* nodes[0] 是无效的 */
			/* 对每个node进行初始化 */
			for (int i = 1; i < nodes.length; i++) {
				nodes[i] = new Node(i);
				nodes[i].leftChild = null;
				nodes[i].rightChild = null;
//				System.out.println("nodes[i] = " + nodes[i].value);
			}
			/* 给对应的节点设置子节点 */
			while (--size > 0) {
				parent = input.nextInt();
				child = input.nextInt();
				if (nodes[parent].leftChild == null) {
					nodes[parent].leftChild = nodes[child];
				} else {
					nodes[parent].rightChild = nodes[child];
				}
			}
//			System.out.println("finish read data");
			/* 根节点重新定向 */
			this.root = nodes[1];
		}

		public void inOrderTraverse() {
//			System.out.print("中序遍历:");
			inOrderTraverse(root);
			System.out.println();
		}

		private void inOrderTraverse(Node node) {
			if (node == null)
				return;
			inOrderTraverse(node.leftChild);
			node.display();
			inOrderTraverse(node.rightChild);
		}

		public void preOrderTraverse() {
//			System.out.print("前序遍历:");
			preOrderTraverse(root);
			System.out.println();
		}

		private void preOrderTraverse(Node node) {
			if (node == null)
				return;

			node.display();
			preOrderTraverse(node.leftChild);
			preOrderTraverse(node.rightChild);
		}

		public void postOrderTraverse() {
//			System.out.print("后序遍历:");
			postOrderTraverse(root);
			System.out.println();
		}

		private void postOrderTraverse(Node node) {
			if (node == null)
				return;
			postOrderTraverse(node.leftChild);
			postOrderTraverse(node.rightChild);
			node.display();
		}
	}

	private static final class FastReader {

		private final BufferedReader bufferedReader;
		/* legacy class preferred over String#split and Scanner for performance */
		private StringTokenizer tokenizer;

		private FastReader(final BufferedReader bufferedReader) {
			this.bufferedReader = bufferedReader;
			this.tokenizer = null;
		}

		public static final FastReader from(final InputStream inputStream) {
			return new FastReader(new BufferedReader(new InputStreamReader(inputStream)));
		}

		public String next() {
			return tokenize() ? tokenizer.nextToken() : null;
		}

		private boolean tokenize() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				// read a line, see if end of stream has been reached
				String line = null;
				try {
					if ((line = bufferedReader.readLine()) == null)
						return false;
				} catch (IOException unexpected) {
					throw new RuntimeException(unexpected);
				}
				tokenizer = new StringTokenizer(line);
			}
			return true;
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
