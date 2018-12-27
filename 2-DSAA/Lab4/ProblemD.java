package Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemD {

	static FastReader input = FastReader.from(System.in);

	/*
	 * 1 5 6 .....# S....# .#.... .#.... ...E.. 333300012
	 */
	public static void main(String[] args) {
		int case_num = input.nextInt();
		Map map;
		String[] strs;
		int[] command;
		int n;
		int m;
		int i = -1;
		/* 初始化24种前进方案 */
		Map.planInitial();
		while (case_num-- > 0) {
			i = -1;
			n = input.nextInt();
			m = input.nextInt();
			map = new Map(n, m);
			strs = new String[n + 1];
			while (++i <= n)
				strs[i] = input.next();
			command = new int[strs[n].length()];
			 i = -1;
			 while (++i < command.length)
				command[i] = (int) strs[n].charAt(i) - 48;
			System.out.println(map.process(strs, command));
		}
	}

	private static class Map {
		static ArrayList<int[]> allSorts = new ArrayList<int[]>();
		int[][] map;
		private int x_Max = 0; // number of columns -> length of string
		private int y_Max = 0; // number of lines
		private int x_Start = 0;
		private int y_Start = 0;
		private int x_End = 0;
		private int y_End = 0;
		private static int[][] allPlan = new int[24][4];
		private int sum = 0;

		private int process(String[] strs, int[] command) {
			/* 保存地图到二维数组，可行走路线为0，障碍物为1，起点终点为0，坐标单独保存 */
			mapInitial(strs);
			/* 初始化起点坐标 */
			Move position = new Move(x_Start, y_Start);
			/* 开始移动 */
			for (int plan = 0; plan < 24; plan++) {
				position.x_Cur = this.x_Start;
				position.y_Cur = this.y_Start;
//				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
				for (int i : command) {
					position.moveAsPlan(plan, i);
//					System.out.println("("+ position.x_Cur + ","+ position.y_Cur+")");
					if (position.y_Cur < 0 || position.y_Cur >= y_Max || position.x_Cur < 0 || position.x_Cur >= x_Max
							|| map[position.y_Cur][position.x_Cur] == 1)
						break;
					if (position.y_Cur == y_End && position.x_Cur == x_End) {
						sum++;
						break;
					}
				}
			}
			return sum;
		}

		/* initialize the size of the map */
		public Map(int n, int m) {
			this.y_Max = n;
			this.x_Max = m;
			this.map = new int[n][m];
		}

		private static void permutation(int[] nums, int start, int end) {
			if (start == end) { // 当只要求对数组中一个数字进行全排列时，只要就按该数组输出即可
				int[] newNums = new int[nums.length]; // 为新的排列创建一个数组容器
				for (int i = 0; i <= end; i++) {
					newNums[i] = nums[i];
				}
				allSorts.add(newNums); // 将新的排列组合存放起来
			} else {
				for (int i = start; i <= end; i++) {
					int temp = nums[start]; // 交换数组第一个元素与后续的元素
					nums[start] = nums[i];
					nums[i] = temp;
					permutation(nums, start + 1, end); // 后续元素递归全排列
					nums[i] = nums[start]; // 将交换后的数组还原
					nums[start] = temp;
				}
			}
		}

		private void mapInitial(String[] strs) {
			for (int i = 0; i < y_Max; i++) {
				char[] c = strs[i].toCharArray();
				for (int j = 0; j < x_Max; j++) {
					switch (c[j]) {
					case '.':
						map[i][j] = 0;
						break;
					case '#':
						map[i][j] = 1;
						break;
					case 'S':
						this.x_Start = j;
						this.y_Start = i;
						map[i][j] = 0;
						break;
					case 'E':
						this.x_End = j;
						this.y_End = i;
						map[i][j] = 0;
						break;
					}
				}
			}
			/* print the map */
//			 for (int[] i : map) {
//			 System.out.println(Arrays.toString(i));
//			 }
//			 System.out.println("mapInitial");
		}

		public static void planInitial() {
			int[] direction = { 0, 1, 2, 3 };
			allSorts.clear();
			permutation(direction, 0, 3);
			allSorts.toArray(allPlan);
			// for (int[] i : allPlan)
			// System.out.println(Arrays.toString(i));
		}

		private class Move {
			int x_Cur = 0;
			int y_Cur = 0;

			public Move(int x_Start, int y_Start) {
				this.x_Cur = x_Start;
				this.y_Cur = y_Start;
			}

			private int[] moveAsPlan(int planNum, int direction) { // direction is the input
				int[] plan = allPlan[planNum];
				switch (plan[direction]) {
				case 0:
					// System.out.println("goUp");
					this.y_Cur--;// UP
					break;
				case 1:
					// System.out.println("goDown");
					this.y_Cur++;// DOWN
					break;
				case 2:
					// System.out.println("goLeft");
					this.x_Cur--;// LEFT
					break;
				case 3:
					// System.out.println("goRight");
					this.x_Cur++;// RIGHT
					break;
				}
				return plan;
			}
		}

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

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}

}
