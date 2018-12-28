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
//		System.out.println("city.size = " + citys[start].size());
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
//				System.out.println(Arrays.toString(dist));
				if (hasvisited[j] == false && dist[j] < nextDistance) {
					nextDistance = dist[j];
					next = j;
				}
			}
//			System.out.println("next = " + next);
//			System.out.println("next = " + next);
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
