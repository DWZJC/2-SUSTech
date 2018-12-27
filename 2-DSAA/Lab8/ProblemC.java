package Lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProblemC {
	InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static Reader input = new Reader();
	static PrintWriter out = new PrintWriter(outputStream);
	static Node[] nums;

	public static void main(String[] args) throws IOException {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() throws IOException {
		int n = input.nextInt();
		int m = input.nextInt();
		int s = input.nextInt();
		nums = new Node[n + 1];
		for (int i = 1; i < n + 1; i++)
			nums[i] = new Node();
		/* store the info. of the nodes */
		int x, y;
		while (m-- > 0) {
			x = input.nextInt();
			y = input.nextInt();
			nums[x].vertex.add(y);
			nums[y].vertex.add(x);
		}
		find(n, s);

	}

	private static void find(int size, int s) {
		boolean[] isProcessed = new boolean[size + 1];
		Queue<Integer> isProcessing = new LinkedList<>();
		nums[s].distance = 0;
		isProcessing.add(s);
		isProcessed[s] = true;
		int tmpVertex = 0;
		int tmpNode = 0;
		while (!isProcessing.isEmpty()) {
			tmpNode = isProcessing.poll();
			for (int i = 0; i < nums[tmpNode].vertex.size(); i++) {
				tmpVertex = nums[tmpNode].vertex.get(i);
				if (!isProcessed[tmpVertex]) {
					nums[tmpVertex].distance = nums[tmpNode].distance + 1;
					isProcessing.add(tmpVertex);
					isProcessed[tmpVertex] = true;
				}
			}
		}
		for (int i = 1; i < size + 1; i++)
			out.print(nums[i].distance + " ");
		out.println();
	}

	static class Node {
		ArrayList<Integer> vertex;
		int distance;

		public Node() {
			this.vertex = new ArrayList<>();
			this.distance = -1;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex.toString() + "]";
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

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
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

		public long nextLong() throws IOException {
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

		public double nextDouble() throws IOException {
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

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}
