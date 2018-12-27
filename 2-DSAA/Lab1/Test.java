package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Test {
	static FastReader input = FastReader.from(System.in);
	public static void main(String[] args) {

		int cases_num = input.nextInt();
		double result;
		result = process();
		System.out.printf("Case #1: %.0f",  result);
		if(cases_num>1) {
			for (int i = 2; i <= cases_num; i++) {
				result = process();
				System.out.printf("\nCase #%d: %.0f",i,  result);
			}
		}

		input.close();
	}

	private static double process() {
		int num = input.nextInt();
		double[][] kids = new double[num][2];
		for(int i = 0; i <num; i++) {
			kids[i][0] = input.nextDouble();
			kids[i][1] = input.nextDouble();
		}

		// 查找总和最小
		double left = kids[0][0];
		double right = kids[num - 1][0];
		double step = 0.01;
		double mid1_value = 0;
		double mid2_value = 0;
		double mid1;
		double mid2;
		do {
			mid1 = (left + right) / 2;
			mid2 = (mid1 + right) / 2;
			mid1_value = 0;
			mid2_value = 0;
			for (int i = 0; i < num; i++) {
				mid1_value += Math.pow(Math.abs(mid1 - kids[i][0]), 3) * kids[i][1];
				mid2_value += Math.pow(Math.abs(mid2 - kids[i][0]), 3) * kids[i][1];
			}
			if (mid1_value < mid2_value) {
				right = mid2;
			} else {
				left = mid1;
			}
		} while (left+step<right);

		return mid1_value;
	}
	private static final class FastReader {

		private final BufferedReader bufferedReader;
		/* legacy class preferred over String#split and Scanner for performance */
		private StringTokenizer tokenizer;

		private FastReader(final BufferedReader bufferedReader) {
			this.bufferedReader = bufferedReader;
			this.tokenizer = null;
		}

		/**
		 * Returns a {@link UniversityHotel.FastReader} instance that reads input from
		 * {@code inputStream}.
		 *
		 * @param inputStream
		 * @return Returns a {@link UniversityHotel.FastReader} instance that reads
		 *         input from {@code inputStream}.
		 */
		public static final FastReader from(final InputStream inputStream) {
			return new FastReader(new BufferedReader(new InputStreamReader(inputStream)));
		}

		/**
		 * Returns the next word acquired by {@link StringTokenizer}. Moves on to the
		 * next line if the current line has been processed.
		 *
		 * @return Returns the next word acquired by {@link StringTokenizer}, or null if
		 *         end of stream has been reached.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 */
		public String next() {
			return tokenize() ? tokenizer.nextToken() : null;
		}

		/**
		 * Checks to see if there are any more words left in the {@code inputStream}.
		 * Can be used to check if end of stream has been reached, as well. If required,
		 * reads another line from the {@code inputStream}; i.e this operation might
		 * perform an I/O; possibly block if end of stream is not reached but stream is
		 * not yet available to yield a new line.
		 *
		 * @return Returns true if there are more words to read in the
		 *         {@code inputStream} and end of stream has not been reached. False
		 *         otherwise.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 */
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

		/**
		 * Returns the next {@code int} acquired by {@link StringTokenizer} using
		 * {@link Integer#parseInt(String)} on {@link #next()}. Moves on to the next
		 * line if the current line has been processed.
		 *
		 * @return Returns the next {@code int} acquired by {@link StringTokenizer}.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 * @throws NumberFormatException
		 *             If an invalid input is encountered or end of stream has been
		 *             reached.
		 */
		public int nextInt() {
			return Integer.parseInt(next());
		}

		/**
		 * Returns the next {@code long} acquired by {@link StringTokenizer} using
		 * {@link Long#parseLong(String)} on {@link #next()}. Moves on to the next line
		 * if the current line has been processed.
		 *
		 * @return Returns the next {@code long} acquired by {@link StringTokenizer}.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 * @throws NumberFormatException
		 *             If an invalid input is encountered or end of stream has been
		 *             reached.
		 */
		public long nextLong() {
			return Long.parseLong(next());
		}

		/**
		 * Returns the next {@code double} acquired by {@link StringTokenizer} using
		 * {@link Double#parseDouble(String)} on {@link #next()}. Moves on to the next
		 * line if the current line has been processed.
		 *
		 * @return Returns the next {@code double} acquired by {@link StringTokenizer}.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 * @throws NumberFormatException
		 *             If an invalid input is encountered or end of stream has been
		 *             reached.
		 */
		public double nextDouble() {
			return Double.parseDouble(next());
		}

		/**
		 * Returns the next {@link BigDecimal} acquired by {@link StringTokenizer} using
		 * BigDecimal's String constructor on {@link #next()}. Moves on to the next line
		 * if the current line has been processed.
		 *
		 * @return Returns the next {@code BigDecimal} acquired by
		 *         {@link StringTokenizer}.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 * @throws NumberFormatException
		 *             If an invalid input is encountered or end of stream has been
		 *             reached.
		 */
		public BigDecimal nextBigDecimal() {
			return new BigDecimal(next());
		}

		/**
		 * Returns the next {@link BigInteger} acquired by {@link StringTokenizer} using
		 * BigInteger's String constructor on {@link #next()}. Moves on to the next line
		 * if the current line has been processed.
		 *
		 * @return Returns the next {@code BigInteger} acquired by
		 *         {@link StringTokenizer}.
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 * @throws NumberFormatException
		 *             If an invalid input is encountered or end of stream has been
		 *             reached.
		 */
		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		/**
		 * Closes the input stream.
		 *
		 * @throws RuntimeException
		 *             If {@link java.io.BufferedReader#readLine()} throws an
		 *             {@link IOException}.
		 * @see java.io.BufferedReader#close()
		 */
		public void close() {
			try {
				bufferedReader.close();
			} catch (IOException unexpected) {
				throw new RuntimeException(unexpected);
			}
		}
	}

}
