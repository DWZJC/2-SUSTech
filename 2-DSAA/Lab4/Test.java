package Lab4;

public class Test {
	
	public static void main(String[] args) {
		
		printSeries1(10);
	}

	private static void printSeries(int n) {
		if(n==0) {
			System.out.println(n);
			return;
		}else {
			System.out.println(n);
			printSeries(n-1);
		}
	}
	
	private static void printSeries1(int n) {
		String str = String.format("%d", n);
		if(n==0) {
			System.out.println(n);
			return;
		}else {
			
			printSeries(n-1);
			System.out.println(str);
		}
		
	}
	
	
	
}
