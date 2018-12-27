package Lab0;
import java.util.Scanner;

public class ProblemD {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = 0;
		int b = 0;
		int c = 0;
//
//		char[][] image = generate(4, 6, 6);
//		 draw(image);
		
		 int case_num = input.nextInt();
		 while(case_num-- != 0) {
		 a = input.nextInt();
		 b = input.nextInt();
		 c = input.nextInt();
		
		 char[][] image = generate(a, b, c);
		 draw(image);
		 }
	}

	private static char[][] generate(int a, int b, int c) {
		int m = 2 * b + 2 * c + 1; // m rows m = 13
		int n = 2 * b + 2 * a + 1; // n columns n = 17
		char[][] image = new char[m][n];
		// 横向打印
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i % 2 == 0 && j % 2 == 0) {// 所有+,需要被覆盖
					image[i][j] = '+';
				}
				
				if (i % 2 != 0 && j % 2 == 0) {// 所有|,需要被覆盖       [奇数行偶数列]
					
					if(i>2*b) {
						image[i][j] ='|';
					}else if(j> 2*a + 2*b - i) {
						image[i][j] ='|';
					}else {
						image[i][j] ='.';
					}
				}
				if (i % 2 != 0 && j % 2 != 0) {// 其他.，需要被/覆盖
					image[i][j] = j<2*a?'.':'/';
					if(i<2*b&&i!=0) {
						image[i][j] = '/';
					}
					if(j>2*a+2*b+2*c-1-i&&i> 2*b) {//右下角楞
						image[i][j] = '/';
					}
				}
				if (i % 2 == 0 && j % 2 != 0) {// 所有-，需要被覆盖
					image[i][j] = '-';
					if(j>2*a+2*b-i&&i< 2*b + 1) {
						image[i][j] = '.';
					}
					if(i>2*b&&j>2*a) {
						image[i][j] = '.';
					}
					
				}

				if (i < 2 * b && j < 2 * b - i) {// 左上角。
					image[i][j] = '.';
				}
				if (i > 2*c && j > 2*a + 2*b + 2*c - i) {// 右下角.
					image[i][j] = '.';
				}
				
//				image[i][j] = String.valueOf(i).toCharArray()[0];
			}
		}
		return image;
	}

	private static void draw(char[][] image) {
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				System.out.print(image[i][j]);
			}
			System.out.println();
		}
	}
	/*
	 * 13 12 14 12 11 15 12 11 10 16 12 11 10 9
	 */

}
