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
import java.util.Collections;
import java.util.StringTokenizer;

public class ProblemE {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static Node[] courses;
	static int[] indegree;
	static ArrayList<Integer> list;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int n = input.nextInt();
		initial(n);
		if (list.isEmpty()) {
			out.println("impossible");
			return;
		}
		int num = 0, course = 0;
		while (num != n) {
			Collections.sort(list);
			course = list.get(0);
			num++;
			if (num != n)
				out.print(course + 1 + " ");
			else
				out.println(course + 1);

			list.remove(0);
			for (int i = 0; i < courses[course].next.size(); i++)
				if (--indegree[courses[course].next.get(i)] == 0)
					list.add(courses[course].next.get(i));
		}
//		out.println();
	}

	private static void initial(int n) {
		courses = new Node[n];
		for (int i = 0; i < n; i++)
			courses[i] = new Node(new ArrayList<Integer>(), new ArrayList<Integer>());
		indegree = new int[n];
		int m = input.nextInt();
		int pre, next;
		for (int i = 0; i < m; i++) {
			pre = input.nextInt() - 1;
			next = input.nextInt() - 1;
			indegree[next]++;
			courses[next].pre.add(pre);
			courses[pre].next.add(next);
		}
		list = new ArrayList<Integer>();
		for (int i = 0; i < indegree.length; i++)
			if (indegree[i] == 0)
				list.add(i);
	}

	static class Node {
		ArrayList<Integer> pre;
		ArrayList<Integer> next;

		public Node(ArrayList<Integer> pre, ArrayList<Integer> next) {
			this.pre = pre;
			this.next = next;
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
