package Lab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class ProblemE {
	static FastReader input = FastReader.from(System.in);
	static Queue<Integer> rawData; // 保存初始数据
	static Stack<Integer> midData; // 数据处理中
	static Queue<Integer> finalData; // 保存结果数据
	static boolean[] outQueue; // 判断数据是否出队，影响下一次可以出队的最小值

	public static void main(String[] args) {
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			/* 清空数组 */
			process();
			printResult(out);
		}
		out.close();

//		 String result_1 = "1 17 40 49 18 5 31 19 2 25 27 26 23 8 12 4 42 33 15 13 24 45 37 41 35 30 36 50 29 43 39 3 38 9 21 10 20 14 22 6 46 48 32 34 7 47 11 28 16 44 ";		// String result_1 = "";
//		 String result_2 = "1 17 40 49 18 5 31 19 2 25 27 26 23 8 12 4 42 33 15 13 24 45 37 41 35 30 36 50 29 43 39 3 38 9 21 10 20 14 22 6 46 48 32 34 7 47 11 28 16 44 ";		 
//		 System.out.println(result_1.equals(result_2));
	}
	
	
	private static void process() {
		int n = input.nextInt();
		int min = 1; // 每次出队需要找到的最小元素

		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = input.nextInt();

		// int[] nums = { 7, 4, 20, 18, 24, 14, 12, 13, 3, 1, 5, 11, 9, 2, 6, 23, 25, 8,
		// 19, 15, 17, 22, 16, 21, 10 };
		//1 3 17 27 31 36 54 68 84 61 92 28 35 85 47 88 9 6 10 94 87 75 37 33 45 80 71 72 89 66 7 64 98 62 23 58 39 46 40 4 22 29 41 70 73 86 69 79 15 93 65 53 25 51 60 81 38 11 59 49 56 8 78 77 82 14 20 32 96 18 50 63 43 44 34 74 12 5 100 13 90 83 16 55 48 97 24 26 76 30 57 52 19 67 42 2 99 95 91 21 
		//1 3 17 27 31 36 54 68 84 61 92 28 35 85 47 88 9 6 10 94 87 75 37 33 45 80 71 72 89 66 7 64 98 62 23 58 39 46 40 4 22 29 41 70 73 86 69 79 15 93 65 53 25 51 60 81 38 11 59 49 56 8 78 77 82 14 20 32 96 18 50 63 43 44 34 74 12 5 100 13 90 83 16 55 48 97 24 26 76 30 57 52 19 67 42 2 99 95 91 21 
		rawData = new LinkedList<Integer>();
		midData = new Stack<Integer>();
		finalData = new LinkedList<Integer>();
		outQueue = new boolean[n + 1];// 长度 n+1, 忽略outQueue[0]
		/* 初始化数据，入队 */
		for (int i : nums) {
			rawData.add(i);
		}
		
		/* 开始操作 */
		int cur_num = 0;
		boolean endLoop = false;

		while (!rawData.isEmpty()) {/* 连续出队，直到遇到最小数 */
			cur_num = rawData.poll();
			outQueue[cur_num] = true;/* 可寻找的对列最小值更新 */
			if (cur_num == min) {
				finalData.add(cur_num);
				while (outQueue[min]) { /* 将min增加到下一个队列中的元素 */
					min++;
					if (min == n) {
						endLoop = true;
						break;
					}
//					print();
					/* 比较midData栈顶是否可以弹出 */
					while (!midData.isEmpty()) {
						if(midData.peek() < min)
							finalData.add(midData.pop());
						else
							break;
					}
				}
				if (endLoop)
					break;
				continue;
			}
//			print();
			midData.push(cur_num);
			
			
		}
	}

	private static void printResult(PrintWriter out) {
		while (!finalData.isEmpty()) {
			out.print(finalData.poll() + " ");
		}
		while (!midData.isEmpty()) {
			out.print(midData.pop() + " ");
		}
		out.println();
	}
	
//	private static void print() {
//		System.out.println("rawData = "+rawData);
//		System.out.println("midData = "+midData);
//		System.out.println("finalData = "+finalData);
//
//	}

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
