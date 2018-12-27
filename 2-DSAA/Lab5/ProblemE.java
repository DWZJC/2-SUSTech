package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemE {
	static FastReader input = FastReader.from(System.in);
	static int[] move;
	static int sLen;
	static int tLen;
	static char[] cT;
	static char[] cS;
	static String strS;
	static String strT;
	static int case_num;

	public static void main(String[] args) {
		case_num = input.nextInt();
		while (case_num-- > 0) {
			process();
		}

	}

	private static void process() {
		sLen = input.nextInt();
		tLen = input.nextInt();
		strS = input.next();
		strT = input.next();
		cT = strT.toCharArray();
		cS = strS.toCharArray();

		initialMove(cS);
		int n = computeBySunday(cS, sLen, cT, tLen);
		System.out.print(n + " ");
		System.out.println(strS.substring(0, n));
	}

	private static int computeBySunday(char[] cS, int sLen, char[] cT, int tLen) {

		int i = 0;
		int j = 0;
		int step = 0;
		while (i < tLen) {
			while (j < sLen && i + j < tLen && cT[i + j] == cS[j])
				j++;
			if (i + sLen >= tLen) {
				if (j < sLen && i + j < tLen && cT[i + j] != cS[j]) {
					i++;
					continue;
				} else
					return j;
			}

			step = move[(int) cT[i + sLen] - 97];
			if (i + step < tLen)
				i += move[(int) cT[i + sLen] - 97];
			else
				i++;
		}
		return j;
	}

	public static int[] initialMove(char[] cS) {
		int sLen = cS.length;
		move = new int[26];
		for (int i = 0; i < 26; ++i)
			move[i] = sLen + 1;
		for (int i = 0; i < sLen; ++i)
			move[(int) cS[i] - 97] = sLen - i;
		// System.out.println(Arrays.toString(move));
		return move;
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
