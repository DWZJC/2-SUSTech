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

public class problemF {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static ArrayList<Node> citys[];
	static long[] dist_bob;
	static long[] dist_alice;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int n = input.nextInt();
		initial(n);
		int Bob = input.nextInt() - 1;
		int Alice = input.nextInt() - 1;
		dist_bob = dijkstra(Bob, dist_bob, citys);
		dist_alice = dijkstra(Alice, dist_alice, citys);
		getResult(n);
	}

	private static void getResult(int n) {
		long result = Long.MAX_VALUE;
		long tmpResult = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
//			System.out.println("Bob[" + i + "]:" + dist_bob[i] + "    Alice[" + i + "]:" + dist_alice[i]);
			tmpResult = dist_alice[i] >= dist_bob[i] ? dist_alice[i] : dist_bob[i];
			result = result > tmpResult ? tmpResult : result;

		}
//		out.println(result);
		System.out.println(result);
	}

	private static void initial(int n) {

		citys = new ArrayList[n];
		dist_bob = new long[n];
		dist_alice = new long[n];
		for (int i = 0; i < citys.length; i++) {
			citys[i] = new ArrayList<>();
		}
		Arrays.fill(dist_alice, Long.MAX_VALUE);
		Arrays.fill(dist_bob, Long.MAX_VALUE);
		int m = input.nextInt();
		int u, v, distance;

		while (m-- > 0) {
			u = input.nextInt() - 1;
			v = input.nextInt() - 1;
			distance = input.nextInt();
			citys[u].add(new Node(v, distance));
			citys[v].add(new Node(u, distance));
		}

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
		boolean[] hasvisited = new boolean[n];
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
