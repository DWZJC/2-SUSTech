package Bonus_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemC {
	static long sum = 0;
	static int[] array;
	static int[] tmpArray;
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) throws Exception {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			sum = 0;
			int size = input.nextInt();
			array = new int[size];
			tmpArray = new int[size];// 避免递归爆栈，提前开辟内存
			for (int i = 0; i < size; i++) {
				array[i] = input.nextInt();
			}
//			if (case_num == 5) {
//				throw new Exception(Arrays.toString(array));
//			}
			mergeSort(0, size - 1);
			System.out.println(sum);
		}
	}

	private static void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid); // 左边归并排序，使得左子序列有序
			mergeSort(mid + 1, right); // 右边归并排序，使得右子序列有序
			merge(left, mid, right); // 将两个有序子数组合并操作
		}
	}

	private static void merge(int left, int mid, int right) {
		int i = left;// left pointer of array
		int j = mid + 1;// right pointer of array
		int t = 0; // pointer of tmpArray
		for (int tmpI = left, tmpJ = mid + 1; tmpI <= mid; tmpI++) {
			while (tmpJ <= right && array[tmpI] > array[tmpJ])
				tmpJ++;
			sum += tmpJ - (mid + 1);
		}
		/* 存入 tmpArray 数组 */
		while (i <= mid && j <= right) {
			if (array[i] > array[j])
				tmpArray[t++] = array[j++];
			else
				tmpArray[t++] = array[i++];
		}
		/* 剩余无需排序的元素填入tmpArray */
		while (i <= mid)
			tmpArray[t++] = array[i++];
		while (j <= right)
			tmpArray[t++] = array[j++];
		/* 将数据放回原数组 */
		t = 0;
		while (left <= right) {
			array[left++] = tmpArray[t++];
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

		public long nextLong() {
			return Long.parseLong(next());
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
