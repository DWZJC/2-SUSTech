package Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class ProblemG {

	static FastReader input = FastReader.from(System.in);
	static Stack<Long> stackMin = new Stack<Long>();// 存放求最小值的栈
	static Stack<Long> stackMax = new Stack<Long>();// 存放求最大值的栈

	public static void main(String[] args) throws Exception {
		int case_num = input.nextInt();
		
		while (case_num-- > 0) {
			stackMin.clear();
			stackMax.clear();
			process();
		}
	}

	private static void process( ) {
		long cur_num = 0;
		int size = input.nextInt();
		while (size-- > 0) {
//			 System.out.println("stackMin = "+stackMin);
//			 System.out.println("stackMax = "+stackMax);
			String str = input.next();
			switch (str) {
			case "push":
				cur_num = input.nextLong();
				push(cur_num);
				break;
			case "pop":
				pop();
				printResult();
				break;
			}
		}
	}

	private static void printResult() {
		if (stackMin.isEmpty() || stackMax.isEmpty())
			System.out.println("0");
		else
			System.out.println(stackMax.peek() - stackMin.peek());
	}

	public static void push(long cur_num) {
		/* 处理最小 */
		if (stackMin.isEmpty() || cur_num <= stackMin.peek())
			stackMin.push(cur_num);
		else if (cur_num > stackMin.peek())
			stackMin.push(stackMin.peek());
		/* 处理最大 */
		if (stackMax.isEmpty() || cur_num >= stackMax.peek())
			stackMax.push(cur_num);
		else if (cur_num < stackMax.peek())
			stackMax.push(stackMax.peek());
	}

	public static void pop() {
		if (!stackMin.isEmpty() && !stackMax.isEmpty()) {
			stackMin.pop();
			stackMax.pop();
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
