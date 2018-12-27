package Lab3;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = " ";
		str = input.nextLine();
		if(str==null) {
			System.out.println("str==null");
		}else if(str == "") {
			System.out.println("str == \"\"");
		}else if(str == " ") {
			System.out.println("str == \" \"");
		}else if(str == "\n") {
			System.out.println("str == \"\\n\"");
		}else if(str.length() == 0) {
			System.out.println("str.length() == 0");
		}else {
			System.out.println("*"+str+"*");
		}
	}
}
