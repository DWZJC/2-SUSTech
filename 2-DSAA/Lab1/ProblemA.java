package Lab1;

import java.util.Scanner;

public class ProblemA {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int cases_num = input.nextInt();
		int n = 0;// the number of the people -> the length of the array
		int m = 0;// the height of the demon -> the number we want
		while (cases_num-- > 0) {
			n = input.nextInt();
			m = input.nextInt();
			/* store the array */
			int[] nums = new int[n];
			// 0 1 2
			// 0
			for (int i = 0; i < n; i++) {
				nums[i] = input.nextInt();
			}
			System.out.println(process(nums, m));
		}
		input.close();
	}

	private static String process(int[] nums, int value) {
		int low, high, mid;
		low = 0;
		high = nums.length - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (nums[mid] == value)
				return "Yes";
			if (nums[mid] > value)
				high = mid - 1;
			if (nums[mid] < value)
				low = mid + 1;
		}
		return "NO";
	}

}
