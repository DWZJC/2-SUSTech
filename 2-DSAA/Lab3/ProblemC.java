package Lab3;

import java.util.LinkedList;
import java.util.Scanner;

public class ProblemC {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		String str = "";
		int maxBookAvalible;
		Custom cus;
		while (true) {
			maxBookAvalible = input.nextInt();
			if (maxBookAvalible == 0)
				System.exit(0);
			str = input.nextLine();
			if (str.length() == 0) {
				System.out.println("-0");
				continue;
			}
			cus = new Custom(maxBookAvalible, str);
			int num = cus.process();
			System.out.println(num);
		}

	}

	private static class Custom {
		private int maxBookAvalible;
		private char[] customerSequence;
		private int notFindBook;

		public Custom(int maxBookAvalible, String str) {
			this.maxBookAvalible = maxBookAvalible;
			this.customerSequence = str.toCharArray();
			this.notFindBook = 0;
		}

		private int process() {
			LinkedList<Character> custom = new LinkedList<Character>();
			LinkedList<Character> currentCustomerWithBook = new LinkedList<Character>();

			for (int i = 1; i < customerSequence.length; i++) {
				if (customerSequence[i] != ' ') {
					custom.add(customerSequence[i]);
				}
			}
			while (custom.size() > 0) {
				int i = 0;
				/*
				 * System.out.println("\ncustom.size = " + custom.size());
				 * System.out.print("currentCustomerWithBook[ "); for (i = 0; i <
				 * currentCustomerWithBook.size(); i++) {
				 * System.out.print(currentCustomerWithBook.get(i) + " "); }
				 * System.out.println("]"); System.out.print("custom[ "); for (i = 0; i <
				 * custom.size(); i++) { System.out.print(custom.get(i) + " "); }
				 * System.out.println("]" + this.notFindBook);
				 */
				/* 此人可尝试还书 */
				if (currentCustomerWithBook.size() > 0) {
					boolean hasReturned = false; // 判断还书状态，否则写在for循环里的continue需要换为goto跳回while开始
					for (i = 0; i < currentCustomerWithBook.size(); i++) {
						/* 判断能否成功还书 */
						if (custom.size() > 0 && currentCustomerWithBook.get(i) == custom.get(0)) {
							currentCustomerWithBook.remove(i);
							custom.remove(0);
							hasReturned = true;
							break;
						}
					}
					if (hasReturned)
						continue;
				}
				/* 此人没能成功还书，尝试借书 */
				/* 此人没能成功借书 */
				if (currentCustomerWithBook.size() == maxBookAvalible) {
					this.notFindBook++;
					char temC = custom.get(0);
					/* 该顾客没有借到书 */
					custom.remove(0);
					/* 没能成功借书的人后面不会再出现 */

					for (i = 0; i < custom.size(); i++) {
						if (temC == custom.get(i)) {
							break;
						}
					}
					custom.remove(i);
					continue;
				} else/* 此人成功借书 */ {
					currentCustomerWithBook.add(custom.get(0));
					custom.remove(0);
					continue;
				}
			}
			return this.notFindBook;
		}
	}

}
