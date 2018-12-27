package Lab1;

import java.util.Scanner;

public class ProblemF {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		int cases_num = input.nextInt();
		double result;
		result = process();
		System.out.printf("Case #1: %.0f",  result);
		if(cases_num>1) {
			for (int i = 2; i <= cases_num; i++) {
				result = process();
				System.out.printf("\nCase #%d: %.0f",i,  result);
			}
		}

		input.close();
	}

	private static double process() {
		int num = input.nextInt();
		double[][] kids = new double[num][2];
		for(int i = 0; i <num; i++) {
			kids[i][0] = input.nextDouble();
			kids[i][1] = input.nextDouble();
		}

		// 查找总和最小
		double left = kids[0][0];
		double right = kids[num - 1][0];
		double step = 0.01;
		double mid1_value = 0;
		double mid2_value = 0;
		double mid1;
		double mid2;
		do {
			mid1 = (left + right) / 2;
			mid2 = (mid1 + right) / 2;
			mid1_value = 0;
			mid2_value = 0;
			for (int i = 0; i < num; i++) {
				mid1_value += Math.pow(Math.abs(mid1 - kids[i][0]), 3) * kids[i][1];
				mid2_value += Math.pow(Math.abs(mid2 - kids[i][0]), 3) * kids[i][1];
			}
			if (mid1_value < mid2_value) {
				right = mid2;
			} else {
				left = mid1;
			}
		} while (left+step<right);
		
		return mid1_value;
	}

}

