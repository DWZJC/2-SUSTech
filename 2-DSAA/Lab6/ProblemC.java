package Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemC {
	static FastReader input = FastReader.from(System.in);
	static int[] nums = new int[200001];
	/*
	 * legal index: 1 to 100001, for node with index i, the child-nodes has index
	 * 2*i and 2*i+1
	 */
	static int nums_size = 0;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		int tmp_num = 0;
		int size = 0;
		while (case_num-- > 0) {
			/* For each case, initialize the array first */
			nums_size = 0;
			size = input.nextInt();
			if (size == 0)
				continue;
			for (int i = 0; i < size; i++) {
				tmp_num = input.nextInt();
				insert(tmp_num);
			}
			/* initialization finished */
			process();
		}
	}

	public static void process() {
		int process_num = input.nextInt();
		int process = 0;
		while (process_num-- > 0) {

			process = input.nextInt();
			int tmp_num = 0;
			switch (process) {
			case 1: /* Add x */
				tmp_num = input.nextInt();
				insert(tmp_num);
				break;
			case 2: /* Delete */
				System.out.println(pop());
				break;
			case 3: /* Query: print the minimum element */
				System.out.println(nums[1]);
				break;
			}
		}
	}

	/* delete and return the top of the heap */
	public static int pop() {
		if (nums_size == 0) /* invalid process */
			return -1;
		if (nums_size == 1) {
			nums_size--;
			return nums[1];
		}
		/* The smallest number that we want to delete */
		int smallest = nums[1];
		/* Assign the biggest number the the top of the heap */
		int tmp_num = nums[nums_size--]; /* Get the largest number */
		/* adjust the new heap from the top */
		int i = 2;
		/*
		 * if the num is smaller than its parent-node's, then it need to go up (switch)
		 */
		for (; i + 1 <= nums_size && tmp_num > nums[i]; i *= 2) {
			/* If there are two child-node, get the smaller one */
			if (i + 1 <= nums_size && nums[i] > nums[i + 1])
				i = i + 1;
			/* If the parent-node is larger than the child-node, then switch them */
			nums[i / 2] = nums[i];
		}
		nums[i / 2] = tmp_num;
		printNums();
		return smallest;
	}

	public static void insert(int tmp_num) {
		int location = ++nums_size;
		/*
		 * if the number is smaller than its parent-node's, then it need to go up
		 * (switch)
		 */
		for (; location >= 1 && tmp_num < nums[location / 2]; location = location / 2)
			nums[location] = nums[location / 2];
		/* find the correct location which the tmp_num should be */
		nums[location] = tmp_num;
		// printNums();
	}

	public static void printNums() {
		Arrays.toString(nums);
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
