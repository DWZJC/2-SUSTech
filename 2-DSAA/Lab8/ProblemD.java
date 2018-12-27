package Lab8;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class ProblemD {
	static Reader input = new Reader();
	static PrintWriter out = new PrintWriter(System.out);
	static int[][] adjMat;

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0)
			process();
		out.close();
	}

	private static void process() {
		int numOfClique = 0;
		adjMatInitial();
		int n = adjMat.length;
		/* find all the combinations of four nodes in all nodes */
		for (int i = 1; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				for (int k = j + 1; k < n - 1; k++) {
					for (int l = k + 1; l < n; l++) {
//						System.out.printf("%d %d %d %d\n", i, j, k, l);
						if (isClique(i, j, k, l))
							numOfClique++;
					}
				}
			}
		}
		out.println(numOfClique);
	}

	private static boolean isClique(int i, int j, int k, int l) {
		if (adjMat[i][j] == 1)
			if (adjMat[i][k] == 1)
				if (adjMat[i][l] == 1)
					if (adjMat[j][k] == 1)
						if (adjMat[j][l] == 1)
							if (adjMat[k][l] == 1)
								return true;
		return false;
	}

	private static void adjMatInitial() {
		int n = input.nextInt();
		adjMat = new int[n + 1][n + 1];
		int m = input.nextInt();
		int x = 0;
		int y = 0;
		while (m-- > 0) {
			x = input.nextInt();
			y = input.nextInt();
			adjMat[x][y] = 1;
			adjMat[y][x] = 1;
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
