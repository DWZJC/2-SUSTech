package Lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProblemF {
	static InputStream inputStream = System.in;
	static OutputStream outputStream = System.out;
	static Reader input = new Reader();
	static PrintWriter out = new PrintWriter(outputStream);
	static Vertex[] nodes;
	static Queue<Integer> zeroChild;
	static Queue<Integer> zeroParent;

	public static void main(String[] args) throws IOException {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		initial();
		calculate();
	}

	private static void calculate() {
		int node;
		while (zeroParent.isEmpty() == false) {
			node = zeroParent.poll();
			nodes[node].weight.add(0);
			nodes[node].parent.add(0);
			nodes[0].numOfchild++;
			nodes[node].numOfParent++;
		}
		Vertex tmpNode;
		while (!zeroChild.isEmpty()) {
			tmpNode = nodes[zeroChild.poll()];
			for (int i = 0; i < tmpNode.parent.size(); i++) {
				if (nodes[tmpNode.parent.get(i)].distance < tmpNode.distance + tmpNode.weight.get(i))
					nodes[tmpNode.parent.get(i)].distance = tmpNode.weight.get(i) + tmpNode.distance;
				nodes[tmpNode.parent.get(i)].numOfchild--;
				if (nodes[tmpNode.parent.get(i)].numOfchild == 0)
					zeroChild.add(nodes[tmpNode.parent.get(i)].value);
			}
		}
		out.println(nodes[0].distance);
		return;
	}

	private static void initial() {
		int n = input.nextInt();
		int m = input.nextInt();
		nodes = new Vertex[n + 1];
		for (int i = 0; i < n + 1; i++)
			nodes[i] = new Vertex(i, 0, 0, 0, new ArrayList<Integer>(), new ArrayList<Integer>());

		for (int i = 0; i < m; i++) {
			int x = input.nextInt();
			int y = input.nextInt();
			int z = input.nextInt();
			nodes[x].numOfchild++;
			nodes[y].numOfParent++;
			nodes[y].parent.add(x);
			nodes[y].weight.add(z);
		}
		zeroChild = new LinkedList<>();
		zeroParent = new LinkedList<>();
		for (int i = 1; i < n + 1; i++) {
			if (nodes[i].numOfParent == 0)
				zeroParent.add(i);
			if (nodes[i].numOfchild == 0)
				zeroChild.add(i);

		}

	}

	static class Vertex {
		int value;
		long distance;
		int numOfParent;
		int numOfchild;
		ArrayList<Integer> weight;
		ArrayList<Integer> parent;

		public Vertex(int value, long distance, int numOfParent, int numOfchild, ArrayList<Integer> weight,
				ArrayList<Integer> parent) {
			this.value = value;
			this.distance = distance;
			this.numOfParent = numOfParent;
			this.numOfchild = numOfchild;
			this.weight = weight;
			this.parent = parent;
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
