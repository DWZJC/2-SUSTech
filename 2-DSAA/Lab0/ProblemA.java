package Lab0;
import java.util.Scanner;

public class ProblemA {
	static String[] words1= { "SweepLine", "DynamicProgramming","dijkstra"};
	static String[] words2= { "We", "can", "solve", "this", "problem", "by", "sweepline", "algorithm"};

	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		int test_num = input.nextInt();
		
		while(input.hasNextLine()) {
			initialization();
			if(processor())
				System.out.println("Appeared");
			else
				System.out.println("Not appeared");
		}
	}
	
	private static void initialization() {
		int size = 0;
		
		size = input.nextInt();
		words1 = new String[size];
		int i = 0;
		for(i = 0; i< size; i++) {
			words1[i] = input.next();
		}
		
		size = input.nextInt();
		words2 = new String[size];
		String sentence = "";
			String error = input.nextLine();
			sentence = input.nextLine();
		words2 = sentence.split(" ");
	}

	private static boolean processor() {
		for(int i = 0; i < words1.length;i++) {
			for(int j = 0; j< words2.length;j++) {
				if(words1[i].equalsIgnoreCase(words2[j])){
					return true;
				}
			}
		}
		return false;
	}
}
