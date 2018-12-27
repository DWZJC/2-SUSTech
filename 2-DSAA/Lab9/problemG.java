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

public class problemG {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static ArrayList<Node> citys[];
	static int[] mid;
	static long[][] dij;
	static long[] dist;
	static boolean[] hasvisited;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int n = input.nextInt();
		initial(n);
		for (int i = 0; i < mid.length; i++) {
			Arrays.fill(dij[i], Long.MAX_VALUE);
			dijkstra(mid[i], dij[i], citys);
		}
		dist = dijkstra(0, dist, citys);

//		System.out.println("dij >>>");
//		for (int i = 0; i < dij.length; i++)
//			System.out.println("> " + Arrays.toString(dij[i]));
//		System.out.println("dist>>>");
//		System.out.println("> " + Arrays.toString(dist));

		getResult(mid.length, n);
//		long result = getResult(mid.length, n);
//		out.println(result);
	}

	private static long getResult(int k, int n) {
		long result = Long.MAX_VALUE;
		long tmp;
		int a, b, c, d, e;
		switch (k) {
		case 1:
			for (a = 0; a < k; a++) {
				tmp = 0;
				tmp += dist[mid[a]];
				tmp += dij[a][n - 1];
				result = result > tmp ? tmp : result;
			}
			break;
		case 2:
			for (a = 0; a < k; a++) {
				for (b = 0; b < k; b++) {
					if (a == b)
						continue;
					tmp = 0;
					tmp += dist[mid[a]];
					tmp += dij[a][mid[b]];
					tmp += dij[b][n - 1];
					result = result > tmp ? tmp : result;

				}
			}
			break;
		case 3:
			for (a = 0; a < k; a++) {
				for (b = 0; b < k; b++) {
					if (a == b)
						continue;
					for (c = 0; c < k; c++) {
						if (c == a || c == b)
							continue;
						tmp = 0;
						tmp += dist[mid[a]];
						tmp += dij[a][mid[b]];
						tmp += dij[b][mid[c]];
						tmp += dij[c][n - 1];
						result = result > tmp ? tmp : result;
					}
				}
			}
			break;

		case 4:
			for (a = 0; a < k; a++) {
				for (b = 0; b < k; b++) {
					if (a == b)
						continue;
					for (c = 0; c < k; c++) {
						if (c == a || c == b)
							continue;
						for (d = 0; d < k; d++) {
							if (d == a || d == b || d == c)
								continue;
//							System.out.println(a + " " + b + " " + c + "" + d);
							tmp = 0;
							tmp += dist[mid[a]];
							tmp += dij[a][mid[b]];
							tmp += dij[b][mid[c]];
							tmp += dij[c][mid[d]];
							tmp += dij[d][n - 1];
							result = result > tmp ? tmp : result;
						}
					}
				}
			}
			break;

		case 5:
			for (a = 0; a < k; a++) {
				for (b = 0; b < k; b++) {
					if (a == b)
						continue;
					for (c = 0; c < k; c++) {
						if (c == a || c == b)
							continue;
						for (d = 0; d < k; d++) {
							if (d == a || d == b || d == c)
								continue;
							for (e = 0; e < k; e++) {
								if (e == a || e == b || e == c || e == d)
									continue;
								tmp = 0;
								tmp += dist[mid[a]];
								tmp += dij[a][mid[b]];
								tmp += dij[b][mid[c]];
								tmp += dij[c][mid[d]];
								tmp += dij[d][mid[e]];
								tmp += dij[e][n - 1];
								result = result > tmp ? tmp : result;
							}
						}
					}
				}
			}
			break;

		}
		out.println(result);
		return result;
	}

	private static void initial(int n) {
		hasvisited = new boolean[n];
		dist = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		int m = input.nextInt();
		int k = input.nextInt();
		dij = new long[k][n];
		mid = new int[k];
		citys = new ArrayList[n];
		for (int i = 0; i < citys.length; i++)
			citys[i] = new ArrayList<>();
		int u, v, distance;
		while (m-- > 0) {
			u = input.nextInt() - 1;
			v = input.nextInt() - 1;
			distance = input.nextInt();
			citys[u].add(new Node(v, distance));
			citys[v].add(new Node(u, distance));
		}
		for (int i = 0; i < k; i++)
			mid[i] = input.nextInt() - 1;
	}

	public static long[] dijkstra(int start, long[] dist, ArrayList<Node>[] citys) {
//		System.out.println("city.size = " + citys[start].size());
		int n = citys.length;
		dist[start] = 0;
		int v, distance;
		for (int i = 0; i < citys[start].size(); i++) {

			v = citys[start].get(i).next;
			distance = citys[start].get(i).distance;
			if (dist[v] > distance)
				dist[v] = distance;
		}

		Arrays.fill(hasvisited, false);
		hasvisited[start] = true;
		/* dijkstra initialized */

		int next;
		long nextDistance;
		for (int i = 0; i < n - 1; i++) {
			next = Integer.MAX_VALUE;
			nextDistance = Long.MAX_VALUE;
			for (int j = 0; j < n; j++) {
//				System.out.println(Arrays.toString(dist));
				if (hasvisited[j] == false && dist[j] < nextDistance) {
					nextDistance = dist[j];
					next = j;
				}
			}
//			System.out.println("next = " + next);
//			System.out.println("next = " + next);
			hasvisited[next] = true;
			for (int j = 0; j < citys[next].size(); j++) {
				v = citys[next].get(j).next;
				distance = citys[next].get(j).distance;
				if (hasvisited[v] == false && dist[v] > dist[next] + distance)
					dist[v] = dist[next] + distance;
			}
		}
		return dist;
	}

	static class Node {
		int next;
		int distance;

		public Node(int next, int distance) {
			this.next = next;
			this.distance = distance;
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
