package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemE {
	static int[] distance; // 分段
	static int L; // high
	static int n; // distance[n]
	static int m; // m blocks
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		while (input.canReadMore()) {
			process();
		}
	}

	private static void process() {
		L = input.nextInt();
		n = input.nextInt();
		m = input.nextInt();
		distance = new int[n + 1];
		int[] location = new int[n];
		int max_distance = 0;
		for (int i = 0; i < n; i++) {
			location[i] = input.nextInt();
		}
		Arrays.sort(location);
		distance[0] = location[0];
		for (int i = 1; i < n; i++) {
			distance[i] = location[i] - location[i - 1];
			
		}
		distance[n] = L - location[n - 1];
		for(int i = 0; i<distance.length;i++) {
			max_distance = max_distance > distance[i] ? max_distance : distance[i];
		}
		
//		System.out.println(Arrays.toString(distance));

		int min = max_distance;
		int max = L; // maximum value of score
		int mid = 0;
		while (max > min) {

			mid = (max + min) / 2;
//			System.out.println("min = " + min + ", mid = " + mid + ", max = " + max);
			if (playersNeeded(mid) > m) {
				min = mid + 1;
			} else {
				max = mid;
			}
			
		}
		mid = (max + min) / 2;
		System.out.println(mid);

	}

	private static int playersNeeded(int max_distance) {
		int num = 1;
		int d = 0;
		for (int i = 0; i < distance.length; i++) {
			d += distance[i];
			if (d > max_distance) {
				d = distance[i];
				num++;
			}
		}
		return num;
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
