package Bonus_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemB {
	static int size = 7;
	static int k = 0;
	static int[] nums;
	static boolean hasFind = false;
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		int case_num = input.nextInt();

		while (case_num-- > 0) {
			readData();

		}
	}

	private static void readData() {
		size = input.nextInt();
		k = input.nextInt() - 1;
		nums = new int[size];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = input.nextInt();
		}
		hasFind = false;
		process(0, size - 1);
	}

	private static void process(int left, int right) {
		while (!hasFind) {
			int baseValue = nums[left];
			int oldLeft = left;
			int oldRight = right;
			while (left < right) {
				while (left < right && nums[right] >= baseValue)
					right--;
				while (left < right && nums[left] <= baseValue)
					left++;
				if (left < right) {
					nums[left] = nums[left] ^ nums[right];
					nums[right] = nums[left] ^ nums[right];
					nums[left] = nums[left] ^ nums[right];
				}
			}
			if (left == right) {
				nums[oldLeft] = nums[left];
				nums[left] = baseValue;
			}
			if (k == left) {
				hasFind = true;
				System.out.println(nums[left]);
				break;
			}
			if (k < left) {
				right--;
				left = oldLeft;
			} else {
				left++;
				right = oldRight;
			}
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
