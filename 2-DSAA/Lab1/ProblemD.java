package Lab1;

import java.util.Scanner;

public class ProblemD {
	public static void main(String[] args) {
		int m, n;
		Scanner input = new Scanner(System.in);
		//
		n = input.nextInt(); // 天数
		m = input.nextInt(); // 订单数
//		System.out.printf("\nn = %d, m = %d\n", n,m);
		int[] rooms = new int[n];
		int[] orders = new int[3];
		int result = 0;
		for (int i = 0; i < n; i++)
			rooms[i] = input.nextInt();
		int num = 1;
		while (m-- > 0) {
			for (int i = 0; i < 3; i++)
				orders[i] = input.nextInt();
			result = process(rooms, orders);
			if (result != 0) {
				System.out.printf("-1\n%d", num);
				while(m-->0) {
					for (int i = 0; i < 3; i++)
						orders[i] = input.nextInt();
				}
				break;
			}
			num++;
		}
		
		if (result == 0)
			System.out.print("0");
		
		
		//
		
		while(input.hasNext()) {
			n = input.nextInt(); // 天数
			m = input.nextInt(); // 订单数
//			System.out.printf("\nn = %d, m = %d\n", n,m);
			rooms = new int[n];
			orders = new int[3];
			result = 0;
			for (int i = 0; i < n; i++)
				rooms[i] = input.nextInt();
			num = 1;
			while (m-- > 0) {
				for (int i = 0; i < 3; i++)
					orders[i] = input.nextInt();
				result = process(rooms, orders);
				if (result != 0) {
					System.out.printf("\n-1\n%d", num);
					while(m-->0) {
						for (int i = 0; i < 3; i++)
							orders[i] = input.nextInt();
					}
					break;
				}
				num++;
			}
			
			if (result == 0)
				System.out.print("\n0");
		}
		input.close();
	}

	private static int process(int[] rooms, int[] orders) {
		/* print rooms  */
//		System.out.print("rooms: ");
//		for(int i:rooms) {
//			System.out.print(i+", ");
//		}
//		System.out.println();
		/*               */
		
		int dayNum = rooms.length;
		int dayStart;
		int dayEnd;
		int roomNum;
		roomNum = orders[0];
		dayStart = orders[1] - 1;
		dayEnd = orders[2];
		if (dayStart < 0 || dayEnd > dayNum||dayEnd<dayStart)// 订单日期违规
			return -1;
		for (int j = dayStart; j < dayEnd; j++) {
			rooms[j] -= roomNum;
			if (rooms[j] < 0)
				return -1;
		}
		return 0;
	}
}
