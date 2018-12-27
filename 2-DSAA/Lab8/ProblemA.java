package Lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ProblemA {
	static InputReader input = new InputReader(System.in);
	static PrintWriter out = new PrintWriter(System.out);
	static int[][] matrix;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int n = input.nextInt();
		matrix = new int[n][n];
		int m = input.nextInt();
		int x, y;
		while (m-- > 0) {
			x = input.nextInt();
			y = input.nextInt();
			matrix[x - 1][y - 1]++;
		}
		print();
	}

	private static void print() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				out.print(matrix[i][j] + " ");
			}
			out.println();
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
