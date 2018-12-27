package Bonus_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class ProblemD {
//	public static void main(String[] args) {
//		int n = 100;
//		System.out.println("2 " + n);
//		for (int i = 0; i < n; i++) {
//			System.out.print((int) (Math.random() * 1000) + " ");
//		}
//		System.out.println("\n" + n);
//		for (int i = 0; i < n; i++) {
//			System.out.print((int) (Math.random() * 1000) + " ");
//		}
//	}
	static FastReader input = FastReader.from(System.in);
	static int size;
	static Stack<Integer> nums = new Stack<>();
	static int num;
	static int[] data;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			System.out.println(process() ? "Y" : "N");
		}
	}

	private static boolean process() {
		size = input.nextInt();

		data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = input.nextInt();
		}
		if (size < 3)
			return true;
		nums.clear();

		for (int i = 0; i < size; i++) {
			num = data[i];
			if (!nums.empty()) {
				if (num < nums.peek()) {
					nums.push(num);
//					System.out.println(nums);
					if (nums.size() == 3) {
						return false;
					}
				} else {
					nums.clear();
				}
			} else {
				nums.push(num);
			}
		}
		return true;
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
