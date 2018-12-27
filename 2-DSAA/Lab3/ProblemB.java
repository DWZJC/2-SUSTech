package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
/*
1
2
2 2
2 3
2
-1 2
-2 3
8
*/

public class ProblemB {
	static FastReader input = FastReader.from(System.in);

	public static void main(String[] args) {
		int case_num = input.nextInt();
		process();
		while (--case_num > 0) {
			System.out.println();
			process();
		}
		input.close();
	}

	private static void process() {
		Polynomial PolynomialA = new Polynomial();
		Polynomial PolynomialB = new Polynomial();
		int term_num = 0;
		term_num = input.nextInt();
		for (int i = 0; i < term_num; i++) {
			PolynomialA.addHead(input.nextInt(), input.nextInt());
		}
		term_num = input.nextInt();
		for (int i = 0; i < term_num; i++) {
			PolynomialB.addHead(input.nextInt(), input.nextInt());
		}
		Polynomial polynomial = new Polynomial();
		polynomial = polynomial.process(PolynomialA, PolynomialB);
		polynomial.bubbleSort();
		polynomial.displayf();
	}

	private static class Polynomial {
		private int size; // 链表节点的个数
		private Term head; // 头节点

		private Polynomial() {
			size = 0;
			head = null;
		}
		// 链表的每个节点类
		private class Term {
			private int coefficient;
			private int exponent;
			private Term next = null;// 每个节点指向下一个节点的连接

			public Term(int coefficient, int exponent) {
				this.coefficient = coefficient;
				this.exponent = exponent;
			}
		}

		// 在链表头添加元素
		public int[] addHead(int coefficient, int exponent) {
			int[] data = { coefficient, exponent };
			Term newHead = new Term(coefficient, exponent);
			if (size == 0) {
				head = newHead;
			} else {
				newHead.next = head;
				head = newHead;
			}
			size++;
			return data;
		}

		private Polynomial process(Polynomial PolynomialA, Polynomial PolynomialB) {
			// 排序
			PolynomialA.bubbleSort();
			PolynomialB.bubbleSort();
			Polynomial Polynomial = new Polynomial();
			Term headA = PolynomialA.head;
			Term headB = PolynomialB.head;

			while (headA != null && headB != null) {
				if (headA.exponent < headB.exponent) {
					Polynomial.addHead(headA.coefficient, headA.exponent);
					headA = headA.next;
				} else if (headA.exponent > headB.exponent) {
					Polynomial.addHead(headB.coefficient, headB.exponent);
					headB = headB.next;
				} else if (headA.exponent == headB.exponent) {
					Polynomial.addHead(headA.coefficient + headB.coefficient, headA.exponent);
					headA = headA.next;
					headB = headB.next;
				}
				// head = head.next;
			}
			while (headA != null) {
				Polynomial.addHead(headA.coefficient, headA.exponent);
				headA = headA.next;
			}
			while (headB != null) {
				Polynomial.addHead(headB.coefficient, headB.exponent);
				headB = headB.next;
			}
			return Polynomial;
		}

		// 冒泡排序
		public Term bubbleSort() {
			Term head = this.head;
			if (head == null || head.next == null) // 链表为空或者仅有单个结点
				return head;
			Term cur = null, tail = null;
			cur = head;

			int tmpCoefficient;
			int tmpExponent;
			while (cur.next != tail) {
				while (cur.next != tail) {
					if (cur.exponent > cur.next.exponent) {
						tmpCoefficient = cur.coefficient;
						tmpExponent = cur.exponent;
						cur.coefficient = cur.next.coefficient;
						cur.exponent = cur.next.exponent;
						cur.next.coefficient = tmpCoefficient;
						cur.next.exponent = tmpExponent;
					}
					cur = cur.next;
				}
				tail = cur; // 下一次遍历的尾结点是当前结点
				cur = head; // 遍历起始结点重置为头结点
			}
			return head;
		}

		// 显示节点信息
		public void display() {
			if (size > 0) {
				Term node = head;
				int tempSize = size;
				if (tempSize == 1) {// 当前链表只有一个节点
					System.out.println("[" + node.coefficient + "," + node.exponent + "]");
					return;
				}
				while (tempSize > 0) {
					if (node.equals(head)) {
						System.out.print("[" + node.coefficient + "," + node.exponent + "->");
					} else if (node.next == null) {
						System.out.print(node.coefficient + "," + node.exponent + "]");
					} else {
						System.out.print(node.coefficient + "," + node.exponent + "->");
					}
					node = node.next;
					tempSize--;
				}
				System.out.println();
			} else {// 如果链表一个节点都没有，直接打印[]
				System.out.println("[]");
			}

		}

		// 格式化输出
		public void displayf() {
			int coefficient = 0;
			int exponent = 0;
			if (size > 0) {
				Term node = head;
				int tempSize = size;
				boolean isUsed = false;

				while (tempSize > 0) {
					coefficient = node.coefficient;
					exponent = node.exponent;
					if (isUsed && coefficient > 0)
						System.out.print("+");
					if (coefficient == 1) {
						if (exponent == 1)
							System.out.print("x");
						else if (exponent == 0)
							System.out.print("1");
						else
							System.out.print("x^" + exponent);
						isUsed = true;
						node = node.next;
						tempSize--;
						continue;
					}
					if (coefficient == 0) {
						node = node.next;
						tempSize--;
						continue;
					}
					if (coefficient == -1) {
						if (exponent == 1)
							System.out.print("-x");
						else if (exponent == 0)
							System.out.print("-1");
						else
							System.out.print("-x^" + exponent);
						isUsed = true;
						node = node.next;
						tempSize--;
						continue;
					}
					/* 其他情况 */
					if (exponent == 1)
						System.out.print(coefficient + "x");
					else if (exponent == 0)
						System.out.print(coefficient);
					else
						System.out.print(coefficient + "x^" + exponent);
					isUsed = true;
					node = node.next;
					tempSize--;
				}
				if (!isUsed) {
					System.out.print("0");
				}
			} else {// 如果链表一个节点都没有，直接打印[]
				System.out.println("[]");
			}

		}

	}

	private static final class FastReader {

		private final BufferedReader bufferedReader;
		/* legacy class preferred over String#split and Scanner for performance */
		private StringTokenizer tokenizer;

		private FastReader(final BufferedReader bufferedReader) {
			this.bufferedReader = bufferedReader;
			this.tokenizer = null;
		}

		public static final FastReader from(final InputStream inputStream) {
			return new FastReader(new BufferedReader(new InputStreamReader(inputStream)));
		}

		public String next() {
			return tokenize() ? tokenizer.nextToken() : null;
		}

		public boolean canReadMore() {
			return tokenize();
		}

		private boolean tokenize() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				// read a line, see if end of stream has been reached
				String line = null;
				try {
					if ((line = bufferedReader.readLine()) == null)
						return false;
				} catch (IOException unexpected) {
					throw new RuntimeException(unexpected);
				}
				tokenizer = new StringTokenizer(line);
			}
			return true;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public BigDecimal nextBigDecimal() {
			return new BigDecimal(next());
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public void close() {
			try {
				bufferedReader.close();
			} catch (IOException unexpected) {
				throw new RuntimeException(unexpected);
			}
		}
	}
}
