package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemB {
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			int size1 = input.nextInt();
			String str1 = input.next();
			int size2 = input.nextInt();
			String str2 = input.next();
			System.out.println(process(str1, size1, str2, size2) ? "YES" : "NO");
		}

	}

	private static boolean process(String str1, int size1, String str2, int size2) {
		int j = 0; // 正序遍历
		int size = size2 - 1;
		int k = size1 - 1; // 逆序遍历
		while (true) {
			/* 正序遍历至* */
			char c;
			for (; j <= size; j++) {
				c = str1.charAt(j);
				if (c == '*')
					break;
				if (str2.charAt(j) != c)
					return false;
			}
			if (j == k + 1)
				return true;
			/* 倒序遍历至* */
			for (; k >= 0 && size >= 0; k--, size--) {
				c = str1.charAt(k);
				if (c == '*')
					break;
				if (str2.charAt(size) != c)
					return false;
			}
			if (k < j)
				return false;
			return true;
		}

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
