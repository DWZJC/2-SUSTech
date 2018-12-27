package Lab0;
import java.util.Scanner;

public class ProblemE {
	public static int[] temp;
	public static int[] max;

	public static void main(String[] args) {		
		Scanner input = new Scanner(System.in);
		int case_num = input.nextInt();
		
		while(input.hasNextInt()) {
			int size = input.nextInt();
			int[] nums = new int[size];
			for(int i = 0; i< size; i++) {
				nums[i] = input.nextInt();
			}
			temp = new int[nums.length];
			max = new int[nums.length];
			System.out.println(find(nums));
		}		
	}

	public static int find(int[] nums) {
		int i;
		for (i = 0; i < nums.length; i++) {
			if (i == 0) {
				temp[i] = nums[i] - nums[i+1];
				max[i] = nums[i];
			} else {
				temp[i] = maxtwo(temp[i - 1], max[i - 1] - nums[i]);
				max[i] = maxtwo(max[i - 1], nums[i]);
			}
		}
		return temp[i - 1];
	}

	public static int maxtwo(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}
}
