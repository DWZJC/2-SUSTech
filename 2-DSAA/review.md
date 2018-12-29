## Min-Heap
···java
Queue<Integer> priorityQueue = new PriorityQueue<Integer>(ini_size, new Comparator<Integer>() {
				public int compare(Integer I1, Integer I2) {
					return I1 - I2;
				}
			});
```

## KMP
```java
	public static boolean computeByKMP(char[] cS, int sizeS, char[] cP, int sizeP) {
		int i = 0; // 遍历test
		int j = 0; // 遍历pattern
		/* 初始化跳转数组 */
		move = initialMove(cP);
		while (i < sizeS) {
			j = 0;
			while (i < sizeS && j < sizeP) {
				if (j == -1 || cS[i] == cP[j]) { // 当j为-1时，右移动i，同时j归0
					i++;
					j++;
				} else {
					j = move[j]; // j回到指定位置
				}
			}
			/* 匹配完成 */
			if (j == sizeP) {
				return true;
			} else {
				break;
			}
			/* 继续查找剩余字符串 */
		}
		// 匹配失败
		return false;
	}

	public static int[] initialMove(char[] cP) {
		int[] move = new int[cP.length];
		/* 若第一个字符就不匹配，则i直接右移动 */
		move[0] = -1;
		int j = 0;
		int k = -1;
		while (j < cP.length - 1) {
			if (k == -1 || cP[j] == cP[k]) {
				if (cP[++j] == cP[++k]) { // 当两个字符相等时要跳过
					move[j] = move[k];
				} else {
					move[j] = k;
				}
			} else {
				k = move[k];
			}
		}
		return move;
	}
```
## Krusca
```java
	public static int KRUSKAL(int[] V, Edge[] E, int[] parent) {
		MSTEdges = new ArrayList<>();
		int u, v;
		for (int i = 0; i < E.length; i++) {
			u = E[i].u;
			v = E[i].v;
			if (getParent(u, parent) != getParent(v, parent)) {
				MSTEdges.add(E[i]);
				mergeTogether(u, v, parent);
			}
		}
		return MSTEdges.size();
	}

	private static void mergeTogether(int u, int v, int[] parent) {
		int u_parent = getParent(u, parent);
		int v_parent = getParent(v, parent);
		int tmp = parent[u_parent] + parent[v_parent];
		if (parent[u_parent] < parent[v_parent]) {
			parent[v_parent] = u_parent;
			parent[u_parent] = tmp;
		} else {
			parent[u_parent] = v_parent;
			parent[v_parent] = tmp;
		}
	}

	private static int getParent(int vertex, int[] parent) {
		int tmpVertex = vertex;
		for (tmpVertex = vertex; parent[tmpVertex] >= 0; tmpVertex = parent[tmpVertex]) {
		}
		int tmp = 0;
		while (tmpVertex != vertex) {
			tmp = parent[vertex];
			parent[vertex] = tmpVertex;
			vertex = tmp;
		}
		return tmpVertex;
	}
	public static class Edge implements Comparable {
		public int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		public int compareTo(Object o) {
			Edge to = (Edge) o;
			if (this.w > to.w)
				return 1;
			else if (this.w == to.w)
				return 0;
			else
				return -1;
		}
	}
```
## dijkstra
```java
private static void initial(int n) {
		hasvisited = new boolean[n];
		dist = new long[n];
		Arrays.fill(dist, Long.MAX_VALUE);
		int m = input.nextInt();
		int k = input.nextInt();
		dij = new long[k][n];
		mid = new int[k];
		citys = new ArrayList[n];
		for (int i = 0; i < citys.length; i++)
			citys[i] = new ArrayList<>();
		int u, v, distance;
		while (m-- > 0) {
			u = input.nextInt() - 1;
			v = input.nextInt() - 1;
			distance = input.nextInt();
			citys[u].add(new Node(v, distance));
			citys[v].add(new Node(u, distance));
		}
		for (int i = 0; i < k; i++)
			mid[i] = input.nextInt() - 1;
	}

	public static long[] dijkstra(int start, long[] dist, ArrayList<Node>[] citys) {
		int n = citys.length;
		dist[start] = 0;
		int v, distance;
		for (int i = 0; i < citys[start].size(); i++) {

			v = citys[start].get(i).next;
			distance = citys[start].get(i).distance;
			if (dist[v] > distance)
				dist[v] = distance;
		}

		Arrays.fill(hasvisited, false);
		hasvisited[start] = true;
		/* dijkstra initialized */

		int next;
		long nextDistance;
		for (int i = 0; i < n - 1; i++) {
			next = Integer.MAX_VALUE;
			nextDistance = Long.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (hasvisited[j] == false && dist[j] < nextDistance) {
					nextDistance = dist[j];
					next = j;
				}
			}
			hasvisited[next] = true;
			for (int j = 0; j < citys[next].size(); j++) {
				v = citys[next].get(j).next;
				distance = citys[next].get(j).distance;
				if (hasvisited[v] == false && dist[v] > dist[next] + distance)
					dist[v] = dist[next] + distance;
			}
		}
		return dist;
	}
  ```
  ## MyTree
  ```java
  public class MyTree {
	class Node {
		int value;
		Node leftChild;
		Node rightChild;

		Node(int value) {
			this.value = value;
		}

		public void display() {
			System.out.print(this.value + "\t");
		}

		@Override
		public String toString() { // TODO Auto-generated method stub
			return String.valueOf(value);
		}
	}

	class BinaryTree {

		private Node root = null;

		BinaryTree(int value) {

			root = new Node(value);

			root.leftChild = null;

			root.rightChild = null;

		}

		public Node findKey(int value) {
			Node current = root;
			while (true) {
				if (value == current.value) {
					return current;
				} else if (value < current.value) {
					current = current.leftChild;
				} else if (value > current.value) {
					current = current.rightChild;
				}
				if (current == null) {
					return null;
				}
			}
		}

		public String insert(int value) {
			String error = null;
			Node node = new Node(value);
			if (root == null) {
				root = node;
				root.leftChild = null;
				root.rightChild = null;
			} else {
				Node current = root;
				Node parent = null;
				while (true) {
					if (value < current.value) {
						parent = current;
						current = current.leftChild;
						if (current == null) {
							parent.leftChild = node;
							break;
						}
					} else if (value > current.value) {
						parent = current;
						current = current.rightChild;
						if (current == null) {
							parent.rightChild = node;
							break;
						}
					} else {
						error = "having same value in binary tree";
					}
				} // end of while
			}
			return error;
		}

		/**
		 * //中序遍历(递归)： 1、调用自身来遍历节点的左子树 2、访问这个节点 3、调用自身来遍历节点的右子树
		 */
		public void inOrderTraverse() {
			System.out.print("中序遍历:");
			inOrderTraverse(root);
			System.out.println();
		}

		private void inOrderTraverse(Node node) {
			if (node == null)
				return;
			inOrderTraverse(node.leftChild);
			node.display();
			inOrderTraverse(node.rightChild);
		}

		/**
		 * 中序非递归遍历： 1）对于任意节点current，若该节点不为空则将该节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
		 * 2）若左子树为空，栈顶节点出栈，访问节点后将该节点的右子树置为current 3) 重复1、2步操作，直到current为空且栈内节点为空。
		 */
		public void inOrderByStack() {
			System.out.print("中序非递归遍历:");
			Stack<Node> stack = new Stack<Node>();
			Node current = root;
			while (current != null || !stack.isEmpty()) {
				while (current != null) {
					stack.push(current);
					current = current.leftChild;
				}
				if (!stack.isEmpty()) {
					current = stack.pop();
					current.display();
					current = current.rightChild;
				}
			}
			System.out.println();
		}

		/**
		 * //前序遍历(递归)： 1、访问这个节点 2、调用自身来遍历节点的左子树 3、调用自身来遍历节点的右子树
		 */
		public void preOrderTraverse() {
			System.out.print("前序遍历:");
			preOrderTraverse(root);
			System.out.println();
		}

		private void preOrderTraverse(Node node) {
			if (node == null)
				return;

			node.display();
			preOrderTraverse(node.leftChild);
			preOrderTraverse(node.rightChild);
		}

		/**
		 * 前序非递归遍历：
		 * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
		 * 2）若左子树为空，栈顶节点出栈，将该节点的右子树置为current 3) 重复1、2步操作，直到current为空且栈内节点为空。
		 */
		public void preOrderByStack() {
			System.out.print("前序非递归遍历:");
			Stack<Node> stack = new Stack<Node>();
			Node current = root;
			while (current != null || !stack.isEmpty()) {
				while (current != null) {
					stack.push(current);
					current.display();
					current = current.leftChild;
				}

				if (!stack.isEmpty()) {
					current = stack.pop();
					current = current.rightChild;
				}
			}
			System.out.println();
		}

		/**
		 * //后序遍历(递归)： 1、调用自身来遍历节点的左子树 2、调用自身来遍历节点的右子树 3、访问这个节点
		 */
		public void postOrderTraverse() {
			System.out.print("后序遍历:");
			postOrderTraverse(root);
			System.out.println();
		}

		private void postOrderTraverse(Node node) {
			if (node == null)
				return;

			postOrderTraverse(node.leftChild);
			postOrderTraverse(node.rightChild);
			node.display();
		}

		/**
		 * 后序非递归遍历：
		 * 1）对于任意节点current，若该节点不为空则访问该节点后再将节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
		 * 2）若左子树为空，取栈顶节点的右子树，如果右子树为空或右子树刚访问过，则访问该节点，并将preNode置为该节点 3)
		 * 重复1、2步操作，直到current为空且栈内节点为空。
		 */
		public void postOrderByStack() {
			System.out.print("后序非递归遍历:");
			Stack<Node> stack = new Stack<Node>();
			Node current = root;
			Node preNode = null;
			while (current != null || !stack.isEmpty()) {
				while (current != null) {
					stack.push(current);
					current = current.leftChild;
				}

				if (!stack.isEmpty()) {
					current = stack.peek().rightChild;
					if (current == null || current == preNode) {
						current = stack.pop();
						current.display();
						preNode = current;
						current = null;
					}
				}
			}
			System.out.println();
		}

		// 得到最小(大)值：依次向左(右)直到空为之
		public int getMinValue() {
			Node current = root;
			while (true) {
				if (current.leftChild == null)
					return current.value;

				current = current.leftChild;
			}
		}

	}
}
```
