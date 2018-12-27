package Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemC {
	static Queue<Long> queue = new LinkedList<>();
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			System.out.println(process());
			queue.clear();
		}
	}

	private static long process() {
		long sum = 0;
		int n = (int) input.nextLong();// the number of integers
		long m = input.nextLong();// {A[i], A[j], A[k]} <= m
		long[] nums = new long[n];
		for (int i = 0; i < n; i++) {
			nums[i] = input.nextLong();
		}

		int index_cur = 0;
		int num = 0; // 满足要求的元素个数
		Long min;
		long max;
		int peek = 0;
		while (index_cur < n) {
			if (queue.isEmpty()) {
				queue.add(nums[index_cur++]);
				num++;
			}
			min = queue.poll();
			peek++;
			num--;
			max = min + m;
			// /* 添加元素，使得差值满足要求，计算元素个数 */
			while (index_cur < n) {// 还能继续输入
				/* 不满足大小关系，跳出对列增加 */
				if (nums[index_cur] > max)
					break;
				queue.add(nums[index_cur++]);
				num++;
			}
			sum += compute(num);
		}
		num--;
		for(int i = num; num>=2;num--) {
			sum += compute(num);
		}
		
		return sum;
	}

	private static long compute(int n) {
		return n * (n - 1) / 2;
	}

	private static final class FastReader {
		private int input_num = 0;

		private final BufferedReader bufferedReader;
		/* legacy class preferred over String#split and Scanner for performance */
		private StringTokenizer tokenizer;

		private FastReader(final BufferedReader bufferedReader) {
			this.input_num = 0;
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
			try {
				int c = bufferedReader.read();
				while (c <= 32) {
					c = bufferedReader.read();
				}
				boolean negative = false;
				if (c == '-') {
					negative = true;
					c = bufferedReader.read();
				}
				long x = 0;
				while (c > 32) {
					x = x * 10 + c - '0';
					c = bufferedReader.read();
				}
				return negative ? -x : x;
			} catch (IOException e) {
				return -1;
			}
		}

		public int nextInt() {
			this.input_num++;
			return Integer.parseInt(next());
		}
	}

}
