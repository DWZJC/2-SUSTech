package Lab0;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemG {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a = 0;
		int b = 0;
		int c = 0;
		int m = 0;
		int n = 0;

		
		 int case_num = input.nextInt();
		 while(case_num-- != 0) {
		 //a,, b, c 对应立方体的长宽高
		 a = input.nextInt();
		 b = input.nextInt();
		 c = input.nextInt();
		 // m, n对应展开图的长宽
		 m = input.nextInt();
		 n = input.nextInt();
		 System.out.println(judge(a, b, c, m, n)?"Yes":"No");
		 }
	}
	
	public static boolean judge(int a, int b, int c, int m, int n) {
		if(generate(a, b, c, m, n))
			return true;
		if(generate(a, c, b, m, n))
			return true;
		if(generate(b, c, a, m, n))
			return true;
		if(generate(b, a, c, m, n))
			return true;
		if(generate(c, b, a, m, n))
			return true;
		if(generate(c, a, b, m, n))
			return true;
		return false;
		
	}
	
	
	public static boolean generate(int a, int b, int c, int m0, int n0) {
		int[] sort1 = {m0, n0};
		Arrays.sort(sort1);
		int m = sort1[1];
		int n = sort1[0];
		if(m >= max(2*a+2*c, b+2*c)&& n >= min(2*a+2*c, b+2*c)) {
			return true;
		}
		if(m >= max(3*a+b+c, b+c)&& n >= min(3*a+b+c, b+c)){
			return true;
		}
		
		return false;
	}
	public static int max(int a, int b) {
        return (a > b) ? a : b;
    }
	
	public static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}
