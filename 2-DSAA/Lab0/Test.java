package Lab0;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		 Scanner input = new Scanner(System.in);
		 int test_num = input.nextInt();
		 String str = input.nextLine();
		 while (test_num != 0) {
		 test_num--;
		 str = input.nextLine();
		 char[] c = str.toCharArray();
		 int max_pairs = process(c);
		 System.out.println(max_pairs);
		 }
		 input.close();
	}

	private static int process(char[] c) {
		int[][] pairMatrix = new int[20][20];
		initialization(c, pairMatrix);
		int max_pairs = 0;
		int cases = 1 << 19;
		for (int i = 0; i < cases; i += 2) {
			int pair_num = 0;
			for (int m = 0; m < 19; m++) {
				for (int n = 0; n < 19; n++) {
					int x = (i & (1 << m));//x =1 表示前字母大写 且 满足该方案
					int y = (i & (1 << n));//y =1 表示后字母大写 且 满足该方案
					/* 该方案内的大小写组合 */
					if ((x == 0 && y != 0) || (x != 0 && y == 0)) {
						pair_num += pairMatrix[m + 1][n + 1];
					}
				}
			}
			max_pairs = max_pairs >= pair_num ? max_pairs : pair_num;
		}
		
		return max_pairs;
	}

	private static void initialization(char[] c, int[][] pairMatrix) {
		/* 按照字母顺序分别对应字母表种除a,e,i,o,u,w,y的19个字母 */
		int[] alphabet = { 1, 2, 3, 5, 6, 7, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 21, 23, 25 };
		/* 更新二维数组pairMatrix，将字母对出现的次数保存在相应位置 */
		for (int i = 0; i < c.length-1; i++) {
			/* 逐个字符处理每一个字符对，将每个字符对 对应的字母次序分别保存为m、n */
			int c1 = c[i] - 'a';
			int c2 = c[i + 1] - 'a';
			int m = 0;
			int n = 0;

			for (int j = 1; j < 20; j++) {
				if (c1 == alphabet[j-1]) 
					m = j;
				if (c2 == alphabet[j-1])
					n = j;
			}
			pairMatrix[m][n]++;
		}
	}
}
