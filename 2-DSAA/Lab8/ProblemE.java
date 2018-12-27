package Lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemE {
	static InputReader input = new InputReader(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	static Vertex[][] vertexMat;
	static int n;
	static int m;
	static Queue<Vertex> queue;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		matrixInitial();
//		printMat();
		findGroup();
	}

	private static void findGroup() {
		queue = new LinkedList<>();
		int numOfConnectedGroup = 0;
		@SuppressWarnings("unused")
		Vertex tmpVertex;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (vertexMat[i][j].isVisited)
					continue;
				else {
//					System.out.println(">>> i = " + i + " j = " + j);
					queue.offer(vertexMat[i][j]);
					vertexMat[i][j].isVisited = true;
				}
				numOfConnectedGroup++;
				while (!queue.isEmpty()) {
					tmpVertex = queue.poll();
					tryToVisit(tmpVertex.i, tmpVertex.j);
				}
//				printMat();
			}
		}
		out.println(numOfConnectedGroup);
	}

	private static void printMat() {
		for (int i = 0; i < n; i++) {
			System.out.print("[ ");
			for (int j = 0; j < m; j++) {
				System.out.print(vertexMat[i][j].isVisited + "");
			}
			System.out.println("]");
		}
		System.out.println();
	}

	private static void tryToVisit(int i, int j) {
//		System.out.println("i = " + i + " j = " + j);
		int key = vertexMat[i][j].key;

		Vertex tmpVertex;
		/* Left */
		if (j == 0)
			tmpVertex = vertexMat[i][m - 1];
		else
			tmpVertex = vertexMat[i][j - 1];
		if (!tmpVertex.isVisited && tmpVertex.key == key) {
			tmpVertex.isVisited = true;
			queue.offer(tmpVertex);
//			System.out.println("Left");
		}

		/* Right */
		if (j == m - 1)
			tmpVertex = vertexMat[i][0];
		else
			tmpVertex = vertexMat[i][j + 1];
		if (!tmpVertex.isVisited && tmpVertex.key == key) {
			tmpVertex.isVisited = true;
			queue.offer(tmpVertex);
//			System.out.println("Right");
		}
		/* Up */
		if (i > 0) {
			tmpVertex = vertexMat[i - 1][j];
			if (!tmpVertex.isVisited && tmpVertex.key == key) {
				tmpVertex.isVisited = true;
				queue.offer(tmpVertex);
//				System.out.println("Up");
			}
		}
		/* Down */
		if (i < n - 1) {
			tmpVertex = vertexMat[i + 1][j];
			if (!tmpVertex.isVisited && tmpVertex.key == key) {
				tmpVertex.isVisited = true;
				queue.offer(tmpVertex);
//				System.out.println("Up");
			}
		}
	}

	private static void matrixInitial() {
		n = input.nextInt();
		m = input.nextInt();
		vertexMat = new Vertex[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				vertexMat[i][j] = new Vertex(input.nextInt(), i, j);
			}

	}

	static class Vertex {
		int key;
		boolean isVisited;
		int i = 0;
		int j = 0;

		public Vertex(int key, int i, int j) {
			this.key = key;
			this.isVisited = false;
			this.i = i;
			this.j = j;
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
