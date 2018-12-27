package Lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class ProblemG {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static Reader input = new Reader();
	static PrintWriter out = new PrintWriter(outputStream);
	static ArrayList<Integer> tree[];
	static int root;
	static int n, m;
	static boolean[] isNotRoot;
	static int[][] time;
	static int case_num;

	public static void main(String[] args) {
		case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		initial();
		query();
	}

	private static void query() {
		int x, y;
		while (m-- > 0) {
			x = input.nextInt() - 1;
			y = input.nextInt() - 1;
			if (time[x][1] <= time[y][1] && time[y][0] <= time[x][0])
				out.println("Yes");
			else
				out.println("No");
		}
	}

	private static void initial() {
		n = input.nextInt();
		m = input.nextInt();
		tree = new ArrayList[n];
		for (int i = 0; i < tree.length; i++)
			tree[i] = new ArrayList<>();
		isNotRoot = new boolean[n];
		int x, y;
		for (int i = 0; i < n - 1; i++) {
			x = input.nextInt() - 1;
			y = input.nextInt() - 1;
			tree[y].add(x);
			isNotRoot[x] = true;
		}

		root = -1;
		for (int i = 0; i < n; i++)
			if (!isNotRoot[i]) {
				root = i;
				break;
			}

		time = new int[n][2];
		Stack<Integer> stack = new Stack<>();
		boolean[] gonnaOut = new boolean[n];
		stack.push(root);
		int node, times = 0;
		while (stack.isEmpty() == false) {
			node = stack.peek();
			if (!gonnaOut[node]) {
				time[node][0] = ++times;
				gonnaOut[node] = true;
				for (int i = 0; i < tree[node].size(); i++)
					stack.push(tree[node].get(i));
			} else {
				time[node][1] = ++times;
				stack.pop();
			}
		}
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) {
			try {
				din = new DataInputStream(new FileInputStream(file_name));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() {
			try {
				bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() {
			if (din == null)
				return;
			try {
				din.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
