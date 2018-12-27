package Lab1;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ProblemG {
	static int n = 0;
	static int k = 0;
	static int[] s; // credit
	static int[] c; // score
	static double[] y; // s*c - grade*s
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		while(input.canReadMore()) {
			process();
		}
		
	}

	private static void process() {
		n = input.nextInt();
		k = input.nextInt();
		/* get the credit */
		s = new int[n];
		for (int i = 0; i < n; i++) {
			s[i] = input.nextInt();
		}

		/* get the score */
		c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = input.nextInt();
		}
		/*
		 * System.out.println(); for(int i: s) System.out.printf("s = %d, ", i);
		 * System.out.println(); for(int i: c) System.out.printf("c = %d, ", i);
		 * System.out.println();
		 * compute */
		double min = 0;
		double max = 1000; // minimum value of score
		double eps = 0.0001;// The answer should be round to 3 decimal places.
		double mid = 0;
		while (max - min > eps) {
			mid = (max + min) / 2;
			if (could(mid)) {
				min = mid;
			} else {
				max = mid;
			}
//			System.out.println("min = "+min+", mid = "+mid+", max = "+max);
		}
		System.out.printf("%.3f\n", min);
	}

	private static boolean could(double grade) {
		y = new double[n];
		for (int i = 0; i < n; i++) {
			y[i] = s[i]*c[i] - grade * s[i];
		}
		/* ascending order */
		Arrays.sort(y);
		
		/* calculate the sum of the n - k largest numbers */
		double sum = 0;
		int num = n - k;
		while (num-- > 0) {
			sum += y[n - num-1];
		}

		return sum >= 0;
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

		public boolean canReadMore() {
			return tokenize();
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

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public BigDecimal nextBigDecimal() {
			return new BigDecimal(next());
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public void close() {
			try {
				bufferedReader.close();
			} catch (IOException unexpected) {
				throw new RuntimeException(unexpected);
			}
		}
	}

}