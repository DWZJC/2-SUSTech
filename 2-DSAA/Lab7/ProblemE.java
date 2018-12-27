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

public class ProblemE {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static InputReader in = new InputReader(inputStream);
	static PrintWriter out = new PrintWriter(outputStream);
	static Queue<Integer> priorityQueue;

	public static void main(String[] args) {
		int case_num = in.nextInt();
		while (case_num-- > 0) {
			process(in, out);
		}
		out.close();
	}

	private static void process(InputReader in, PrintWriter out) {
		int length = in.nextInt();
		int ini_size = in.nextInt();
		priorityQueue = new PriorityQueue<Integer>(ini_size, new Comparator<Integer>() {
			public int compare(Integer I1, Integer I2) {
				return I1 - I2;
			}
		});

		ini_size++; /* 先放入k+1个元素进入优先队列 */
		length -= ini_size;/* 之后需要读取的数据个数 */
		while (ini_size-- > 0) {
			priorityQueue.offer(in.nextInt());
		}
		while (length-- > 0) {
			/* 输出当前优先队列最小值，即为该冒泡区间可得到的最小数 */
			out.print(priorityQueue.poll() + " ");
			priorityQueue.offer(in.nextInt());
		}
		while (!priorityQueue.isEmpty()) {
			out.print(priorityQueue.poll() + " ");
		}
		out.println();
	}

	static class Node {
		int value;
		Node leftChild;
		Node rightChild;

		Node(int value) {
			this.value = value;
		}

		public void display() {
			System.out.print(this.value + "\t");
		}

		@Override
		public String toString() {
			return String.valueOf(value);
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
 * 优先队列，对位置为i，最远可以选到i+k, 可以有多少个元素冒泡到当前元素O(k+(n-k)logk)
 */