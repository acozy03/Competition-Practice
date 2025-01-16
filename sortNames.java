import java.util.*;

public class sortNames {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();

        // Read in data.
		item[] arr = new item[n];
		for (int i=0; i<n; i++) {
			String name = stdin.next();
			int age = stdin.nextInt();
			int money = stdin.nextInt();
			arr[i] = new item(name, age, money);
		}

		// Use Java's sort.
		Arrays.sort(arr);

        // Print data.
		for (int i=0; i<n; i++)
			System.out.println(arr[i]);
	}
}

class item implements Comparable<item> {

	public String name;
	public int age;
	public int money;

    // Standard constructor.
	public item(String s, int a, int m) {
		name = s;
		age = a;
		money = m;
	}

    // Natural to use this if we have to print the object.
	public String toString() {
		return name+" "+age+" "+money;
	}

    // Valid compareTo given the problem statement.
	public int compareTo(item other) {
		if (this.money != other.money) return other.money - this.money;
		return this.age - other.age;
	}
}