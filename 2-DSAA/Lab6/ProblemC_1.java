package Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemC_1 {
	static FastReader input = FastReader.from(System.in);
	static Queue<Integer> priorityQueue;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			int ini_size = input.nextInt();
			priorityQueue = new PriorityQueue<Integer>(ini_size, new Comparator<Integer>() {
				public int compare(Integer I1, Integer I2) {
					return I1 - I2;
				}
			});
			if (ini_size == 0)
				continue;
			while (ini_size-- > 0) {
				priorityQueue.add(input.nextInt());
			}
			/* initialization finished */
			process();
		}
	}

	private static void process() {
		int process_num = input.nextInt();
		int process = 0;
		while (process_num-- > 0) {
			process = input.nextInt();
			switch (process) {
			case 1: /* Add x */
				priorityQueue.add(input.nextInt());
				break;
			case 2: /* Delete */
				priorityQueue.poll();
				break;
			case 3: /* Query: print the minimum element */
				System.out.println(priorityQueue.peek());
				break;
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
