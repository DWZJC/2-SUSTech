package Bonus_Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in Actual solution is at the top Author: Wavator
 */
public class test {
	public static void main(String[] args) {
		InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
		OutputStream outputStream = System.out;
		Task.InputReader in = new Task.InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(in, out);
		out.close();
	}

	static long count = 0;

	static class Task {

		public void solve(InputReader in, PrintWriter out) {
			int a = in.nextInt();
			for (int i = 0; i < a; i++) {
				count = 0;
				int number = in.nextInt();
				int arr[] = new int[number];
				for (int h = 0; h < number; h++) {
					arr[h] = in.nextInt();
				}
				// int []arr = {4,1,2,3};
				sort(arr);
				// System.out.println(Arrays.toString(arr));
				out.println(count);
			}
		}

		public static void sort(int[] arr) {
			int[] temp = new int[arr.length];// 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
			sort(arr, 0, arr.length - 1, temp);

		}

		private static void sort(int[] arr, int left, int right, int[] temp) {
			if (left < right) {
				int mid = (left + right) / 2;
				sort(arr, left, mid, temp);// 左边归并排序，使得左子序列有序
				sort(arr, mid + 1, right, temp);// 右边归并排序，使得右子序列有序
				// System.out.println(Arrays.toString(arr));
				merge(arr, left, mid, right, temp);// 将两个有序子数组合并操作
			}
		}

		private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
			int i = left;// 左序列指针
			int j = mid + 1;// 右序列指针
			int t = 0;// 临时数组指针

//                for(int y=mid+1;y<=right;y++){
//                    for(int x=left;x<=mid;x++){
//                    if(arr[x]>arr[y]){
//                    count=count+mid-x+1;
//                    break;
//                      //  count++;
//                    }
//                }
//            }
			for (int q = left, w = mid + 1; q <= mid; q++) {
				while (w <= right && arr[q] > arr[w]) {
					w++;
				}
				count += w - (mid + 1);
			}

			while (i <= mid && j <= right) {
				if (arr[i] <= arr[j]) {
					temp[t++] = arr[i++];
				} else {
					temp[t++] = arr[j++];
				}
			}
			while (i <= mid) {// 将左边剩余元素填充进temp中
				temp[t++] = arr[i++];
			}
			while (j <= right) {// 将右序列剩余元素填充进temp中
				temp[t++] = arr[j++];
			}
			t = 0;
			// 将temp中的元素全部拷贝到原数组中
			while (left <= right) {
				arr[left++] = temp[t++];
			}
		}

		static class InputReader {
			public BufferedReader reader;
			public StringTokenizer tokenizer;

			public InputReader(InputStream stream) {
				reader = new BufferedReader(new InputStreamReader(stream), 32768);
				tokenizer = null;
			}

			public String next() {
				while (tokenizer == null || !tokenizer.hasMoreTokens()) {
					try {
						tokenizer = new StringTokenizer(reader.readLine());
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				return tokenizer.nextToken();
			}

			public int nextInt() {
				return Integer.parseInt(next());
			}

			public long nextLong() {
				return Long.parseLong(next());
			}

			public double nextDouble() {
				return Double.parseDouble(next());
			}

			public char[] nextCharArray() {
				return next().toCharArray();
			}

			// public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
			public boolean hasNext() {
				try {
					String string = reader.readLine();
					if (string == null) {
						return false;
					}
					tokenizer = new StringTokenizer(string);
					return tokenizer.hasMoreTokens();
				} catch (IOException e) {
					return false;
				}
			}

			public BigInteger nextBigInteger() {
				return new BigInteger(next());
			}

			public BigDecimal nextBigDecinal() {
				return new BigDecimal(next());
			}
		}
	}
}
