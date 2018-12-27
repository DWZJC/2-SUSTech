package Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemF {

	static int[] nodes;
	static int[] colors;
	static BinaryTree bt;
	static FastReader input = FastReader.from(System.in);
	static int size;
	static int[] numOfBlack;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			size = input.nextInt();
			nodes = new int[size + 1];
			colors = new int[size + 1];
			process();
		}
	}

	private static void process() {
		/* store the info. of the color for each node */
		for (int i = 1; i < colors.length; i++) {
			colors[i] = input.nextInt();
		}
		/* Create connection between two nodes */
		bt = new BinaryTree(1);
		bt.initiate(size);
		bt.BFS();
		bt.printResult();
	}

	/* 广度搜索 */

	private static class BinaryTree {
		class Node {
			int value;
			Node leftChild;
			Node rightChild;

			Node(int value) {
				this.value = value;
			}
		}

		private Node root = null;

		BinaryTree(int value) {
			root = new Node(value);
			root.leftChild = null;
			root.rightChild = null;
		}

		Node[] nodes;

		private void initiate(int size) {
			int parent = 0;
			int child = 0;
			nodes = new Node[size + 1];
			/* nodes[0] 是无效的 */
			/* 对每个node进行初始化 */
			for (int i = 1; i < nodes.length; i++) {
				nodes[i] = new Node(i);
				nodes[i].leftChild = null;
				nodes[i].rightChild = null;
			}
			/* 给对应的节点设置子节点 */
			while (--size > 0) {
				parent = input.nextInt();
				child = input.nextInt();
				if (nodes[parent].leftChild == null)
					nodes[parent].leftChild = nodes[child];
				else
					nodes[parent].rightChild = nodes[child];
			}
			/* 根节点重新定向 */
			this.root = nodes[1];
		}

		private void BFS() {
			Queue<Integer> queue = new LinkedList<>();
			numOfBlack = new int[(int) (Math.log(size) / Math.log(2)) + 1];
			/* store the number of processed nodes */
			int count = 0;
			/* the value of current node */
			int value = 0;
			Node tmpNode = nodes[root.value];
			queue.offer(tmpNode.value);
			while (!queue.isEmpty()) {
				count++;
				value = queue.poll();
				if (value == -1)
					continue;
				tmpNode = nodes[value];
				/*  */
				if (colors[tmpNode.value] == 1) {
					numOfBlack[(int) (Math.log(count) / Math.log(2))]++;
				}
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
		}

		void printResult() {
			if (numOfBlack[0] == 0) {
				for (int i = 1; i < numOfBlack.length; i++) {
					if (numOfBlack[i] % 2 == 1) {
						System.out.println("YES");
						return;
					}
				}
				System.out.println("NO");
				return;
			} else {
				boolean iszero = true;
				for (int i = 1; i < numOfBlack.length; i++) {
					if (numOfBlack[i] != 0) {
						iszero = false;
						break;
					}
				}
				if (iszero) {
					System.out.println("YES");
					return;
				}
				for (int i = 1; i < numOfBlack.length; i++) {
					if (numOfBlack[i] % 2 == 1) {
						System.out.println("YES");
						return;
					}
				}
				System.out.println("NO");
				return;
			}
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
