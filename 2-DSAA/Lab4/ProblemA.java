package Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 6
lanran
lansfscsran2001
20lan0r1an
lanan
nanla
larann
 */
public class ProblemA {
	static Stack<Character> stack = new Stack<>();
	static FastReader input = FastReader.from(System.in);
	final static char[] LANRAN = "lanran".toCharArray();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int case_num = input.nextInt();
		String str = input.nextLine();
		while (case_num-- > 0) {
			str = input.nextLine();
			System.out.println(process(str) ? "YES" : "NO");
			stack.clear();
		}
		input.close();
	}

	private static boolean process(String str) {

		char temC;
		/* 初始化栈 */
		for (int i = 0; i < str.length(); i++) {
			temC = str.charAt(i);
			stack.push(temC);
		}
		// System.out.println(stack);
		/* 倒序删除查找 */
		int i = LANRAN.length - 1;
		while (!stack.empty()) {
			temC = stack.pop();
			if (temC==LANRAN[i])
				i--;
			if (i == -1)
				return true;
		}
		return false;

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

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}

}
