package application;

import java.text.ParseException;
import java.util.Scanner;
import structures.List;
import structures.exceptions.DomainException;
import structures.files.CSV;

//Consertar entrada e entrada e saida e add o generics
public class Main {
	public static void main(String[] args) throws ParseException, DomainException {
		// TODO Auto-generated method stub
		// check if is good put vector
		// read file and check status list
		Scanner sc = new Scanner(System.in);
		System.out.println("-------- Main Menu --------");
		System.out.println("Do you want to use? ");
		System.out.println("[ 1 ] List");
		System.out.println("[ 2 ] Queue");
		System.out.println("[ 3 ] Stack");
		System.out.print("Option: ");
		int option = sc.nextInt();
		System.out.println("---------------------------");
		
		switch (option) {
		case 1:
			// new list = file
			List list = new List(sc);
			list = CSV.read(list, "./data/List.CSV");
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
		sc.close();
	}
}
