package Lab1;

import java.util.Scanner;

public class ProblemB {
	public static void main(String[] args) {
		double y;
		Scanner input = new Scanner(System.in);
		double result;
		String str = "";
		int cases_num = input.nextInt();
		for (int i = 0; i < cases_num; i++) {
			y = input.nextDouble();
			result = process(y); // The final result of x
			/* f(x) = 5x^7+ 6x^6 + 3x^3 +4x^2 -2ax */
			double minimum = 5 * Math.pow(result, 7) + 6 * Math.pow(result, 6) + 3 * Math.pow(result, 3)
					+ 4 * Math.pow(result, 2) - 2 * y * result;
			str += String.format("Case %d: %.4f", i + 1, minimum);
			if (i != cases_num - 1) {
				str += String.format("\n");
			}
		}
		input.close();
		System.out.printf(str);
	}

	public static double process(double a) {
		double e = 1e-15;// 精度
		double x = 0;
		double f1;
		double f2;
		double temp;
		do{
			f1 = 35 * Math.pow(x, 6) + 36 * Math.pow(x, 5) + 9 * Math.pow(x, 2) + 8 * x - 2 * a;
			f2 = 210 * Math.pow(x, 5) + 180 * Math.pow(x, 4) + 18 * x + 8;
			temp  = x;
			x += - f1/f2;
		}while (Math.abs(x - temp) > e);
		return x;
	}
}
