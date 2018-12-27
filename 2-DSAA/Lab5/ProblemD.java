package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemD {
	static int[] move;
	static int[] next;
	static char[] cS;
	static char[] cP;;
	static int sizeS;
	static FastReader input = FastReader.from(System.in);
	static int maxL;
	static String sS;

	public static void main(String[] args) {
		int case_num =input.nextInt();
		while (case_num-- > 0) {
			sizeS = input.nextInt();
			sS = input.next();
			cS = sS.toCharArray();
			maxL = sizeS / 3;
			if(maxL==0) {
				System.out.println("0");
				continue;
			}
			move = initialMove(cS);
			process();
		}
	}

	private static void process() {
		/* 修改next数组，最大到maxL */
		String tmp;
		int num = move[move.length - 1];
		if (num == 0) {
			System.out.println(num);
			return;
		}
		while (num > maxL) {
			tmp = sS.substring(0, num);
			cP = tmp.toCharArray();
			move = initialMove(cP);
			num = move[move.length - 1];
		}

		/* 查找成功 */
		String sP = sS.substring(0, num);
		cP = sP.toCharArray();
		if (computeByKMP(cS, cP)) {
			System.out.println(num);
		} else {
			process();
		}
	}

	public static boolean computeByKMP(char[] cS, char[] cP) {
		int i = 0; // 遍历test
		int j = 0; // 遍历pattern
		int sizeS = cS.length;
		int sizeP = cP.length;
		initialMove(cP);
		/* 只查找中间部分是否仍能匹配 */
//		System.out.println("cS = " + Arrays.toString(cS));
//		System.out.println("cP = " + Arrays.toString(cP));
//		System.out.println("move = " + Arrays.toString(move));
		for (i = sizeP, j = 0; i < sizeS - sizeP && j < sizeP; ++i) {
//			System.out.println("text = "+cS[i]+" pattern = "+cP[j]);
			while (j > 0 && cP[j] != cS[i])
				j = move[j - 1];
			if (cP[j] == cS[i])
				j++;
			if (j == sizeP) {
				return true;
			}
//			j = 0;
		}
		return false;
	}
	/* 以-1开头搭配我的KMP算法的next数组 */
	public static int[] initialNext(char[] cP) {
		next = new int[cP.length];
		/* 若第一个字符就不匹配，则i直接右移动 */
		next[0] = -1;
		int j = 0;
		int k = -1;
		while (j < cP.length - 1) {
			if (k == -1 || cP[j] == cP[k]) {
				if (cP[++j] == cP[++k]) { // 当两个字符相等时要跳过
					next[j] = next[k];
				} else {
					next[j] = k;
				}
			} else {
				k = next[k];
			}
		}
		return next;
	}
/* 以0开头表示最大前后缀的next数组 */
	public static int[] initialMove(char[] cP) {
		int sizeP = cP.length;
		move = new int[sizeP];
		/* 若第一个字符就不匹配，则i直接右移动 */
		int q, k; // k记录所有前缀的对称值
		move[0] = 0; // 首字符的对称值肯定为0
		for (q = 1, k = 0; q < sizeP; ++q) { // 计算每一个位置的对称值
			while (k > 0 && cP[q] != cP[k]) // k总是用来记录上一个前缀的最大对称值
				k = move[k - 1]; // k将循环递减，值得注意的是next[k]<k总是成立
			if (cP[q] == cP[k])
				k++; // 增加k的唯一方法
			move[q] = k; // 获取最终值 }
		}
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
