package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import java.util.Map.Entry;

public class ProblemG {
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			process();
			System.out.println();
		}
	}

	private static void process() {
		int size = 0;
		int[] nums;
		Data_Set data;
		size = input.nextInt();
		nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = input.nextInt();
		}
		data = new Data_Set();
		data.process(nums);
	}

	// 链表的每个节点类
	private static class Data_Set {
		HashMap<Integer, Day> rawData = new HashMap<>();
		List<Entry<Integer, Day>> list_Data;
		private Day mid_0;

		public class Day {
			private Integer turnovers = 0;
			private Integer day = 0;
			private Day next;// 每个节点指向下一个节点的连接
			private Day prior;// 每个节点指向上一个节点的连接

			public Day(int turnovers, int day) {
				this.turnovers = turnovers;
				this.day = day;
				this.next = null;
				this.prior = null;
			}
		}

		private void process(int[] data) {
			initialize(data);
			/* 拿到最开始的中位数指针 */
			int size = rawData.size();
			Day mid = mid_0;
			int left = 0;
			int right = 0;

			int[] result = new int[(size + 1) / 2];
			result[size / 2] = mid.turnovers;
			while (size > 1) {
				Day d1 = rawData.get(size - 1);
				Day d2 = rawData.get(size - 2);
				left = 0;
				right = 0;
				/* 判断d1，d2与mid在升序链表中的位置关系 */
				if (d1.turnovers < mid.turnovers || (d1.turnovers == mid.turnovers && d1.day < mid.day))
					left++;
				if (d1.turnovers > mid.turnovers || (d1.turnovers == mid.turnovers && d1.day > mid.day))
					right++;
				if (d2.turnovers < mid.turnovers || (d2.turnovers == mid.turnovers && d2.day < mid.day))
					left++;
				if (d2.turnovers > mid.turnovers || (d2.turnovers == mid.turnovers && d2.day > mid.day))
					right++;
				/* 通过位置关系决定mid移动方向 */
				if (left == 0)
					mid = mid.prior;
				if (right == 0)
					mid = mid.next;
				deleteTem(d1);
				deleteTem(d2);
				size -= 2;
				result[size / 2] = mid.turnovers;
			}
			for (int i : result) {
				System.out.print(i + " ");
			}

		}

		private void initialize(int[] data) {
			int[] nums = data;
			/* 保存原始数据 */
			for (int i = 0; i < nums.length; i++) {
				Day newNode = new Day(nums[i], i);
				rawData.put(i, newNode);
			}
			/* rawData的key-value可以获得按天数索引的数据 */
			/* 所有value的指针链接完成，按照升序排列的双向链表 */
			initializePointer(rawData);
		}

		private void initializePointer(HashMap<Integer, Day> rawData) {
			int size = rawData.size();
			/* 排序 */
			list_Data = new ArrayList<Map.Entry<Integer, Day>>(rawData.entrySet());
			Collections.sort(list_Data, new Comparator<Map.Entry<Integer, Day>>() {
				public int compare(Map.Entry<Integer, Day> o1, Map.Entry<Integer, Day> o2) {
					// o1 to o2升序 o2 to o1降序
					return o1.getValue().turnovers.compareTo(o2.getValue().turnovers);
				}
			});
			/* 排序完成 */
			mid_0 = list_Data.get(rawData.size() / 2).getValue();
			list_Data.get(0).getValue().prior = list_Data.get(size - 1).getValue().next = null;
			list_Data.get(0).getValue().next = list_Data.get(1).getValue();
			list_Data.get(size - 1).getValue().prior = list_Data.get(size - 2).getValue();
			for (int i = 1; i < size - 1; i++) {
				list_Data.get(i).getValue().prior = list_Data.get(i - 1).getValue();
				list_Data.get(i).getValue().next = list_Data.get(i + 1).getValue();
			}
		}

		public void deleteTem(Day tem) {
			// 头节点
			if (tem.prior == null) {
				tem.next.prior = null;
				return;
			}

			// 尾节点
			if (tem.next == null) {
				tem.prior.next = null;
				return;
			}
			// 中间节点
			tem.next.prior = tem.prior;
			tem.prior.next = tem.next;
			return;
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
