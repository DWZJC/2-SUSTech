package Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ProblemD {
	static FastReader input = FastReader.from(System.in);
	static int maxDistance = 0;
	static ArrayList<Integer>[] edge;

	public static void main(String[] args) {
		int case_num = 1;
		int size = 0;
		int p = 0;
		int c = 0;
		case_num = input.nextInt();
		while (case_num-- > 0) {

			maxDistance = 0;
			size = input.nextInt();
			edge = new ArrayList[size + 1];
			for (int i = 0; i < size + 1; i++) {
				ArrayList<Integer> list = new ArrayList<>();
				edge[i] = list;
			}
			while (--size > 0) {
				p = input.nextInt();
				c = input.nextInt();
				AddEdge(p, c);
			}
			LastOrder(0, 1);
			System.out.println(maxDistance);
		}
	}

	public static void AddEdge(int p, int c) // creat connection between node p and node c
	{
		edge[p].add(c);
		edge[c].add(p);
	}

	public static int LastOrder(int pre, int cur) {
		int first = 0, second = 0; /* Represent the first and the second largest depth. */

		int size = edge[cur].size();
		for (int i = 0; i < size; ++i) { /* using ++i to skip the first element, which always repeats the 'pre' */
			if (edge[cur].get(i) == pre)
				/* We only process the edge which is not repeated. */
				continue;
			/*
			 * Find and compare the values of temp, first and second, then reassign first
			 * and second.
			 */
			int temp = LastOrder(cur, edge[cur].get(i));
			if (temp > first) {
				second = first;
				first = temp;
			} else if (temp > second)
				second = temp;
		}
		/*
		 * In a subtree with any node as the root node, the distance between the
		 * furthest two nodes is first+second.
		 */
		maxDistance = Math.max(maxDistance, first + second);
		/* Return the max distance of a subtree with this node as the root node. */
		return first + 1;
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
