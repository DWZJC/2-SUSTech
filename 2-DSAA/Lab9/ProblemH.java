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

public class ProblemH {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static ArrayList<Node> citys[];
	static int[] mid;
	static long[][] dij;
	static double[] dist;
	static boolean[] hasvisited;
	static Hole start, end;
	static Hole[] holes;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int n = input.nextInt() + 2;
		initial(n);
		ArrayList<Node> holesDistance[] = new ArrayList[n];
		Node pNode;
		for (int i = 0; i < n; i++) {
			holesDistance[i] = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				pNode = new Node(j, getDistance(holes[i], holes[j]));
				holesDistance[i].add(pNode);
			}
		}
		dist = dijkstra(n - 2, dist, holesDistance);
		long result = 0;
		result = Math.round(100 * dist[n - 1]);
		out.println(result);
	}

	private static double getDistance(Hole hole_i, Hole hole_j) {
		double distance = Math.sqrt((hole_i.x - hole_j.x) * (hole_i.x - hole_j.x)
				+ (hole_i.y - hole_j.y) * (hole_i.y - hole_j.y) + (hole_i.z - hole_j.z) * (hole_i.z - hole_j.z))
				- hole_i.r - hole_j.r;
		return distance < 0 ? 0 : distance;
	}

	private static void initial(int n) {
		holes = new Hole[n];
		for (int i = 0; i < holes.length - 2; i++)
			holes[i] = new Hole(input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt());
		start = new Hole(input.nextInt(), input.nextInt(), input.nextInt(), 0);
		end = new Hole(input.nextInt(), input.nextInt(), input.nextInt(), 0);
		holes[n - 2] = start;
		holes[n - 1] = end;
		dist = new double[n];
		Arrays.fill(dist, Double.MAX_VALUE);
		hasvisited = new boolean[n];
	}

	public static double[] dijkstra(int start, double[] dist, ArrayList<Node>[] holesDistance) {
//		System.out.println("city.size = " + citys[start].size());

		int n = holesDistance.length;
		dist[start] = 0;
		int v;
		double distance;
		for (int i = 0; i < holesDistance[start].size(); i++) {
			v = holesDistance[start].get(i).next;
			distance = holesDistance[start].get(i).distance;
			if (dist[v] > distance)
				dist[v] = distance;
		}
		Arrays.fill(hasvisited, false);
		hasvisited[start] = true;
		/* dijkstra initialized */
		int next;
		double nextDistance;
		for (int i = 0; i < n - 1; i++) {
			next = Integer.MAX_VALUE;
			nextDistance = Long.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (hasvisited[j] == false && dist[j] < nextDistance) {
					nextDistance = dist[j];
					next = j;
				}
			}
//			System.out.println("next = " + next);
//			System.out.println("next = " + next);
			hasvisited[next] = true;
			for (int j = 0; j < holesDistance[next].size(); j++) {
				v = holesDistance[next].get(j).next;
				distance = holesDistance[next].get(j).distance;
				if (hasvisited[v] == false && dist[v] > dist[next] + distance)
					dist[v] = dist[next] + distance;
			}
		}
		return dist;
	}

	static class Hole {
		int x;
		int y;
		int z;
		int r;

		public Hole(int x, int y, int z, int r) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.r = r;
		}

	}

	static class Node {
		int next;
		double distance;

		public Node(int next, double distance) {
			this.next = next;
			this.distance = distance;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + next + ", " + distance + " ]";
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
