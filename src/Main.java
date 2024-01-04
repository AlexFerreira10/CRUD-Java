import java.util.Scanner;
import model.services.List;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// check if is good put vector
		// read file and check status list
		Scanner sc = new Scanner(System.in);

		System.out.println("Do you want to use? ");
		System.out.println("[ 1 ] List");
		System.out.println("[ 2 ] Queue");
		System.out.println("[ 3 ] Stack");
		int option = sc.nextInt();
		switch (option) {
		case 1:
			// new list = file
			List list = new List(sc);
			System.out.println();
			System.out.println();
			list.menu();
			break;
		case 2:
			// Queue
			break;
		case 3:
			// Stack
			break;
		default:
			System.out.println("Invalid Digit! ");
		}
	}
}
