package Lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Random1 {
	public static void main(String[] args) {
		List list = new ArrayList<Integer>();
		for (int i = 0; i < 25; i++)
			list.add(i + 1);
		Collections.shuffle(list);

		for (int i = 0; i < 25; i++) {
			System.out.print(list.get(i) + " ");
		}
	}
}
