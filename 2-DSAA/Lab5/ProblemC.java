package Lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemC {
	static int[] move;
	static int result;
	static FastReader input = FastReader.from(System.in);
	static int case_num;
	public static void main(String[] args){
		case_num = input.nextInt();
		while(case_num-->0) {
			System.out.println(process());
		}
	}

	private static int process(){
		// TODO Auto-generated method stub
		int sizeS = input.nextInt();
		String strS = input.next();
		int sizeP = input.nextInt();
		String strP = input.next();
		if(sizeP==0||sizeS==0) 
			return 0;
		
		char[] cS = strS.toCharArray();
		char[] cP = strP.toCharArray();
		move = initialMove(cP);
//		System.out.println(Arrays.toString(move));
		return computeByKMP(cS, sizeS, cP, sizeP);
	}

	public static int computeByKMP(char[] cS, int sizeS, char[] cP, int sizeP) {
		int i = 0; // 遍历test
		int j = 0; // 遍历pattern
		result = 0;
		/* 初始化跳转数组 */
		move = initialMove(cP);
		while(i<sizeS) {
			j=0;
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
				result++;
				i = i-j+1;
			}else {
				break;
			}
			/* 继续查找剩余字符串 */
		}
		// 匹配失败
		return result;
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
