package Lab4;

import java.util.Scanner;
import java.util.Stack;

public class ProblemB {
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int case_num = input.nextInt();
		int size = 0;
		String str;
		while (case_num-- > 0) {
			size = input.nextInt();
			str = input.next();
			if(size%2==1) {
				System.out.println("NO");
				continue;
			}
			System.out.println(process(str) ? "YES" : "NO");
			stack.clear();
		}
		input.close();
	}

	private static boolean process(String str) {
		char temC;
		/* 初始化栈 */
		for (int i = 0; i < str.length(); i++) {
			temC = str.charAt(i);
			if(!stack.isEmpty()&&ismatch(stack.peek(),temC)) 
				stack.pop();
			else
				stack.push(temC);
		}
		return stack.isEmpty();
	}
	
	private static boolean ismatch(char left, char right) {
		switch(left) {
		case '{':
			if(right == '}')
				return true;
			break;
		case '(':
			if(right == ')')
				return true;
			break;
		case '[':
			if(right == ']')
				return true;
			break;
		}
		return false;
	}

}
