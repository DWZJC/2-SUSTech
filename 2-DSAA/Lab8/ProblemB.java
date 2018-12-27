package Lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemB {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static Node[] nums;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int n = input.nextInt();
		int m = input.nextInt();
		nums = new Node[n + 1];
		for (int i = 1; i < n + 1; i++)
			nums[i] = new Node();
		/* store the info. of the nodes */

		while (m-- > 0)
			nums[input.nextInt()].vertex.add(input.nextInt());
		int Q = input.nextInt();
		while (Q-- > 0)
			out.println(find(n) ? "YES" : "NO");
	}

	private static boolean find(int size) {
		boolean[] isProcessed = new boolean[size + 1];
		int x = input.nextInt();
		int y = input.nextInt();
		if (x == y)
			return true;
		Queue<Integer> isProcessing = new LinkedList<>();
		isProcessing.add(x);
		isProcessed[x] = true;
		int tmpVertex = 0;
		int tmpNode = 0;
		while (!isProcessing.isEmpty()) {
			tmpNode = isProcessing.poll();
			for (int i = 0; i < nums[tmpNode].vertex.size(); i++) {
				tmpVertex = nums[tmpNode].vertex.get(i);
				if (tmpVertex == y)
					return true;
				if (!isProcessed[tmpVertex]) {
					isProcessing.add(tmpVertex);
					isProcessed[tmpVertex] = true;
				}
			}
		}
		return false;

	}

	static class Node {
		ArrayList<Integer> vertex;

		public Node() {
			this.vertex = new ArrayList<>();
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex.toString() + "]";
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
