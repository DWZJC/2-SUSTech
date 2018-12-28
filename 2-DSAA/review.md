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
