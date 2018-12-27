package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ProblemF {
	static int case_num;
	static int[] move;
	static FastReader input = FastReader.from(System.in);
	static String[] str;
	static ArrayList<String> L = new ArrayList<>();

	public static void main(String[] args) {
		case_num = input.nextInt();
		while (case_num-- > 0) {
			process();
		}
	}

	private static void process() {
		int index = 0;
		int size = 200;
		int temL = 0;
		int temI = 0;
		L.clear();
		int num = input.nextInt();
		str = new String[num];
		/* 读取数据 */
		for (int i = 0; i < num; i++) {
			str[i] = input.next();
			temL = str[i].length();
			if (temL < size) {
				size = temL;
				index = temI;
			}
			temI++;
		}
		String str0 = str[index];
		char[] cP;
		int sizeP;
		char[] cS;
		int sizeS;
		String temSubStr;
		boolean result = false;
		boolean finish = false;
		/* 双重循环得到从长到短的所有子串 */
		for (int length = size; length > 0; length--) {

			/* 某个length的所有子串 */
			for (int i = 0; i <= size - length; i++) {
				result = false;
				temSubStr = str0.substring(i, i + length);
				System.out.println(temSubStr);
				cP = temSubStr.toCharArray();
				sizeP = temSubStr.length();
				/* 遍历整个字符串数组 */
				for (int j = 0; j < num; j++) {
					if (j == index)
						continue;
					cS = str[j].toCharArray();
					sizeS = cS.length;
					if (!computeByKMP(cS, sizeS, cP, sizeP))/* 匹配失败 */ {
						result = true;
						break;
					}
				}
				if (result) {
					continue;
				} else/* 匹配成功 */ {
					L.add(temSubStr);
					finish = true;
				}
			}
			/* 当前长度字串存在匹配成功且遍历完毕 */
			if (finish) {
				break;
			}
		}
		if (L.isEmpty()) {
			System.out.println("Hong");
		} else {
			Collections.sort(L);
			System.out.println(L.get(0));
		}
	}

	public static boolean computeByKMP(char[] cS, int sizeS, char[] cP, int sizeP) {
		int i = 0; // 遍历test
		int j = 0; // 遍历pattern
		/* 初始化跳转数组 */
		move = initialMove(cP);
		while (i < sizeS) {
			j = 0;
			while (i < sizeS && j < sizeP) {
				if (j == -1 || cS[i] == cP[j]) { // 当j为-1时，右移动i，同时j归0
					i++;
					j++;
				} else {
					j = move[j]; // j回到指定位置
				}
			}
			/* 匹配完成 */
			if (j == sizeP) {
				return true;
			} else {
				break;
			}
			/* 继续查找剩余字符串 */
		}
		// 匹配失败
		return false;
	}

	public static int[] initialMove(char[] cP) {
		int[] move = new int[cP.length];
		/* 若第一个字符就不匹配，则i直接右移动 */
		move[0] = -1;
		int j = 0;
		int k = -1;
		while (j < cP.length - 1) {
			if (k == -1 || cP[j] == cP[k]) {
				if (cP[++j] == cP[++k]) { // 当两个字符相等时要跳过
					move[j] = move[k];
				} else {
					move[j] = k;
				}
			} else {
				k = move[k];
			}
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
