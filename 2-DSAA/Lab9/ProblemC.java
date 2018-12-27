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
import java.util.HashSet;
import java.util.StringTokenizer;

public class ProblemC {
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
		int[] V = new int[N];
		for (int i = 0; i < N; i++)
			V[i] = i + 1;
		Edge[] E = new Edge[M];
		for (int i = 0; i < M; i++)
			E[i] = new Edge(input.nextInt(), input.nextInt(), input.nextInt());
		Arrays.sort(E);// 将边按照权重w升序排序
		/* initialized */
		int startEdgeNum = 0;
		int edges = 0;
		int result = 0;
		while (true) {
//			System.out.println("startEdgeNum = " + startEdgeNum);
			edges = KRUSKAL(V, E, startEdgeNum);
			if (edges == N - 1) {
				result = printResult();
				while (E[++startEdgeNum].w == E[startEdgeNum - 1].w)
					;
			} else {
				System.out.println(result);
				break;
			}
		}
	}

	private static int printResult() {
		int result = 0;
		for (int i = 0; i < MSTEdges.size(); i++)
			result += MSTEdges.get(i).w;
		return result;
	}

	public static int KRUSKAL(int[] V, Edge[] E, int startEdgeNum) {
		MSTEdges = new ArrayList<>();

		ArrayList<HashSet<Integer>> sets = new ArrayList<>();
		for (int i = 0; i < V.length; i++) {
			HashSet<Integer> set = new HashSet<Integer>();
			set.add(V[i]);
			sets.add(set);
		}
		HashSet<Integer> set_i = null, set_j = null;
		for (int i = startEdgeNum; i < E.length; i++) {
			int start = E[i].i, end = E[i].j;
			int counti = -1, countj = -1;
			/* find the set that the start and end belongs to */
			for (int j = 0; j < sets.size(); j++) {
				HashSet<Integer> set = sets.get(j);
				if (set.contains(start))
					counti = j;
				if (set.contains(end))
					countj = j;
			}

			if (counti != countj) {
				set_i = null;
				set_j = null;
				MSTEdges.add(E[i]);
				/* 由于需要sets.remove，为了避免影响，先remove(counti)时需要确保i>j */
				if (counti < countj) {
					counti = counti ^ countj;
					countj = counti ^ countj;
					counti = counti ^ countj;
				}

				if (counti >= 0) {
					set_i = sets.get(counti);
					sets.remove(counti);
				}
				if (countj >= 0) {
					set_j = sets.get(countj);
					sets.remove(countj);
				}
				/* merge set_i and set_j */
				set_i.addAll(set_j);
				sets.add(set_i);

			} else {
//				System.out.println("他们在一棵子树中，不能输出start=" + start + "||end=" + end + "||w=" + E[i].w);
			}
		}
		return MSTEdges.size();
	}

	public static class Edge implements Comparable {
		public int i, j, w;

		public Edge(int i, int j, int w) {
			this.i = i;
			this.j = j;
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
			return "start=" + i + "||end=" + j + "||w=" + w;
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
