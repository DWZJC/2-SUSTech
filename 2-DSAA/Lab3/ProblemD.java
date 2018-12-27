package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class ProblemD {
	static FastReader input = FastReader.from(System.in);

	/* 合并同类项 */
	public static void main(String[] args) {
		int case_num = input.nextInt();
		while (case_num-- > 0) {
			process();
		}
		input.close();
	}

	private static void process() {
		Polynomial PolynomialA = new Polynomial();
		int term_num = 0;
		term_num = input.nextInt();
		for (int i = 0; i < term_num; i++) {
			PolynomialA.addHead(input.nextInt(), input.nextInt());
		}
		PolynomialA.bubbleSort();
		PolynomialA.mergeSimilarItem();
		PolynomialA.differentiate();
		PolynomialA.displayf();
		System.out.println();
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

		private Term differentiate() {
			Term head = this.head;
			int coefficient = 0;
			int exponent = 0;
			while (head != null) {
				coefficient = head.coefficient;
				exponent = head.exponent;
				coefficient *= exponent;
				exponent--;
				head.coefficient = coefficient;
				head.exponent = exponent;
				head = head.next;

			}
			return head;
		}

		// 删除下一个节点，并返回下一个节点的值
		private Term deleteNext(Term current) {
			Term nextTerm = current.next;
			if (nextTerm == null)
				return null;
			current.next = nextTerm.next;
			return nextTerm;
		}

		// 合并同类项[需要先按照指数排序]
		private void mergeSimilarItem() {

			if (size > 0) {
				Term node = head;
				while (node.next != null) {
					// 合并同类项
					if (node.exponent == node.next.exponent) {
						node.coefficient += node.next.coefficient;
						deleteNext(node);
						this.size--;
						continue;
					}
					node = node.next;
				}
			} else {// 如果链表一个节点都没有，直接打印[]
				System.out.println("[]");
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
