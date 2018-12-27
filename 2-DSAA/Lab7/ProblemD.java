package Lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProblemD {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader input = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static Queue<Integer> priorityQueue;
	static int length;
	static int k;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			process();
		}
		out.close();
	}

	private static void process() {
		length = input.nextInt(); /* the number of the data */
		k = input.nextInt();
		if (k <= 5) {
			/* 建立容量为length-k+1的 小顶堆->优先队列 */
			priorityQueue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
				public int compare(Integer I1, Integer I2) {
					return I1 - I2;
				}
			});
			solve();
			out.println(priorityQueue.poll());
			priorityQueue.clear();
			return;
		} else {
			k = length - k + 2;
			/* 建立容量为k的 大顶堆->优先队列,所需元素为堆的第二层的较大者 */
			priorityQueue = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
				public int compare(Integer I1, Integer I2) {
					return I2 - I1;
				}
			});
			solve();
			priorityQueue.poll();
			int l = priorityQueue.poll();
			int r = priorityQueue.poll();
			out.println(l > r ? l : r);
			priorityQueue.clear();
		}

	}

	private static void solve() {
		length -= k;/* 之后需要读取的数据个数 */
		while (k-- > 0) {
			priorityQueue.offer(input.nextInt());
		}
		while (length-- > 0) {
			priorityQueue.poll();
			priorityQueue.offer(input.nextInt());
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
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

		public char[] nextCharArray() {
			return next().toCharArray();
		}

		// public boolean hasNext() {
		// try {
		// return reader.ready();
		// } catch(IOException e) {
		// throw new RuntimeException(e);
		// }
		// }
		public boolean hasNext() {
			try {
				String string = reader.readLine();
				if (string == null) {
					return false;
				}
				tokenizer = new StringTokenizer(string);
				return tokenizer.hasMoreTokens();
			} catch (IOException e) {
				return false;
			}
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public BigDecimal nextBigDecinal() {
			return new BigDecimal(next());
		}
	}
}
/*
 * 建立容量为k的大根堆或小根堆
 */
