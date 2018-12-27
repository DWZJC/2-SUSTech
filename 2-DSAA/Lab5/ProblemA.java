package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemA {
	/*
	 * skrskr nikanzhegemian tayouchangyoukuan zhe zhe zhe zhe zhe zhe jiuxiangzhegewan tayoudayouyuan skrskr
	 * nikanzhegemia tayouchangyoukua jiuxiangzhege tayoudayouy skrskr
	 */
	static FastReader input = FastReader.from(System.in);
	static int[] alphabet; // 存储某个尾字母连续出现的最大次数【首先是local variable，切换下一个字母再与该数组比较并存储】

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while(case_num-->0) {
			System.out.println(process());
		}
	}

	public static int process() {
		alphabet = new int[26];
		char c, temC;
		String s;
		int num_lines = input.nextInt();
		int nums = 1;
		s = input.next();
		c = getLastCharater(s);
		while (--num_lines > 0) {
			s = input.next();
			temC = getLastCharater(s);
			if (temC == c) {
				nums++;
			} else {
				/* 更新最大连续出现次数 */
				int index = (int) c - 97;
				if (alphabet[index] < nums)
					alphabet[index] = nums;
				/* 更新c 与 nums *//* 若最后 */
				c = temC;
				nums = 1;
			}
		}
		/* 处理最后一个字母次数写入 */
		int index = (int) c - 97;
		if (alphabet[index] < nums)
			alphabet[index] = nums;
		/* 更新c 与 nums *//* 若最后 */
		nums = 1;
		Arrays.sort(alphabet);
		return alphabet[25];
	}

	public static char getLastCharater(String s) {
		int size = s.length();
		if (size < 1)
			return 0;
		return s.charAt(size - 1);
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
