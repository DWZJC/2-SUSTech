package Lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemD {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static ArrayList<Edge> MSTEdges;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
	}

	private static void process() {
		/* initializing */
		int N = input.nextInt();
		int M = input.nextInt();
		int K = input.nextInt();
		M += K;
		int[] V = new int[N];
		for (int i = 0; i < N; i++)
			V[i] = i + 1;
		Edge[] E = new Edge[M];
		for (int i = 0; i < M; i++)
			E[i] = new Edge(input.nextInt(), input.nextInt(), input.nextInt());
		Arrays.sort(E);// 将边按照权重w升序排序
		int[] parent = new int[N + 1];
		Arrays.fill(parent, -1);
		/* initialized */

		int result = KRUSKAL(V, E, parent);
		System.out.println(result == N - 1 ? printResult() : "-1");
	}

	private static int printResult() {
		int result = 0;
		for (int i = 0; i < MSTEdges.size(); i++)
			result += MSTEdges.get(i).w;
		return result;
	}

	public static int KRUSKAL(int[] V, Edge[] E, int[] parent) {
		MSTEdges = new ArrayList<>();
		int u, v;
		for (int i = 0; i < E.length; i++) {
			u = E[i].u;
			v = E[i].v;
			if (getParent(u, parent) != getParent(v, parent)) {
				MSTEdges.add(E[i]);
				mergeTogether(u, v, parent);
			}
		}
		return MSTEdges.size();
	}

	private static void mergeTogether(int u, int v, int[] parent) {
		int u_parent = getParent(u, parent);
		int v_parent = getParent(v, parent);
		int tmp = parent[u_parent] + parent[v_parent];
		if (parent[u_parent] < parent[v_parent]) {
			parent[v_parent] = u_parent;
			parent[u_parent] = tmp;
		} else {
			parent[u_parent] = v_parent;
			parent[v_parent] = tmp;
		}
	}

	private static int getParent(int vertex, int[] parent) {
		int tmpVertex = vertex;
//		while (parent[tmpVertex] >= 0)
//			tmpVertex = parent[tmpVertex];
		for (tmpVertex = vertex; parent[tmpVertex] >= 0; tmpVertex = parent[tmpVertex]) {
		}
		int tmp = 0;
		while (tmpVertex != vertex) {
			tmp = parent[vertex];
			parent[vertex] = tmpVertex;
			vertex = tmp;
		}
		return tmpVertex;
	}

	public static class Edge implements Comparable {
		public int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		public int compareTo(Object o) {
			Edge to = (Edge) o;
			if (this.w > to.w)
				return 1;
			else if (this.w == to.w)
				return 0;
			else
				return -1;
		}

		public String toString() {
			return "start=" + u + "||end=" + v + "||w=" + w;
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
