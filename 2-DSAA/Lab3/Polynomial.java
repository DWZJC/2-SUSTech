package Lab3;

public class Polynomial {

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

	private Polynomial add(Polynomial PolynomialA, Polynomial PolynomialB) {
		// 排序
		PolynomialA.bubbleSort();
		PolynomialB.bubbleSort();
		PolynomialA.display();
		PolynomialB.display();

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
			System.out.println();
		} else {// 如果链表一个节点都没有，直接打印[]
			System.out.println("[]");
		}

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

	// 在链表头删除元素
	public int[] deleteHead() {
		int[] data = { head.coefficient, head.exponent };
		head = head.next;
		size--;
		return data;
	}

	// 查找指定元素，找到了返回节点Term，找不到返回null
	public Term find(int coefficient, int exponent) {
		Term current = head;
		int tempSize = size;
		while (tempSize > 0) {
			if (coefficient == current.coefficient && exponent == current.exponent) {
				return current;
			} else {
				current = current.next;
			}
			tempSize--;
		}
		return null;
	}

	// 删除指定的元素，删除成功返回true
	public boolean delete(int coefficient, int exponent) {
		if (size == 0) {
			return false;
		}
		Term current = head;
		Term previous = head;
		while (current.coefficient != coefficient && current.exponent != exponent) {
			if (current.next == null) {
				return false;
			} else {
				previous = current;
				current = current.next;
			}
		}
		// 如果删除的节点是第一个节点
		if (current == head) {
			head = current.next;
			size--;
		} else {// 删除的节点不是第一个节点
			previous.next = current.next;
			size--;
		}
		return true;
	}

	// 判断链表是否为空
	public boolean isEmpty() {
		return (size == 0);
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
