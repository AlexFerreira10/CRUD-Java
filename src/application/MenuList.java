package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import structures.List;
import structures.Node;
import structures.exceptions.DomainException;
import structures.files.CSV;

public class MenuList extends UI {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private List list;

	public MenuList(List list, Scanner sc) {
		this.sc = sc;
		this.list = list;
	}

	@Override
	public void menu() {
		int option = 0;
		UI.clearScreen();
		do {
			System.out.println();
			System.out.println("-------- List --------");
			StringBuilder sb = new StringBuilder();
			sb.append("[ 1 ] Insert (Beginning)\n");
			sb.append("[ 2 ] Insert (Middle)\n");
			sb.append("[ 3 ] Insert (The end)\n");
			sb.append("[ 4 ] Edit\n");
			sb.append("[ 5 ] Remove\n");
			sb.append("[ 6 ] Print\n");
			sb.append("[ 7 ] Search(Sequential)\n");
			sb.append("[ 8 ] Sort (BubleSort)\n");
			sb.append("[ 9 ] Sort (MergeSort)\n");
			sb.append("[ 10 ] Clean\n");
			sb.append("[ 11 ] Save\n");
			sb.append("[ 12 ] Exit\n");
			sb.append("Escolha uma opção: ");
			System.out.println(sb);
			System.out.print("Option: ");
			option = sc.nextInt();
			boolean registrationExists;
			LocalDate date = null;
			int registration;

			try {
				switch (option) {
				case 1:
					do {
						System.out.print("Which registration? ");
						registration = sc.nextInt();
						sc.nextLine();
						System.out.print("Which date?(dd/mm/yyyy): ");
						try {
							date = LocalDate.parse(sc.next(), dtf);
							registrationExists = (list.search(registration, true) != null);

							if (registrationExists) {
								System.out.println("Registration already exists. Please enter a unique registration.");
							}
						} catch (DateTimeParseException e) {
							System.out.println("Invalid date format. Please enter a valid date.");
							registrationExists = true;
						}

					} while (registrationExists);

					list.addBeginning(new Node(registration, date));
					break;
				case 2:
					do {
						System.out.print("Which registration? ");
						registration = sc.nextInt();
						sc.nextLine();
						System.out.print("Which date?(dd/mm/yyyy): ");
						try {
							date = LocalDate.parse(sc.next(), dtf);
							registrationExists = (list.search(registration, true) != null);

							if (registrationExists) {
								System.out.println("Registration already exists. Please enter a unique registration.");
							}
						} catch (DateTimeParseException e) {
							System.out.println("Invalid date format. Please enter a valid date.");
							registrationExists = true;
						}

					} while (registrationExists);
					list.addMiddle(new Node(registration, date));
				case 3:
					do {
						System.out.print("Which registration? ");
						registration = sc.nextInt();
						sc.nextLine();
						System.out.print("Which date?(dd/mm/yyyy): ");
						try {
							date = LocalDate.parse(sc.next(), dtf);
							registrationExists = (list.search(registration, true) != null);

							if (registrationExists) {
								System.out.println("Registration already exists. Please enter a unique registration.");
							}
						} catch (DateTimeParseException e) {
							System.out.println("Invalid date format. Please enter a valid date.");
							registrationExists = true;
						}

					} while (registrationExists);

					list.addTheEnd(new Node(registration, date));
					break;
				case 4:
					System.out.print("Which registration do you want to change? ");
					registration = sc.nextInt();
					edit(registration);
					break;
				case 5:
					System.out.print("Which registration do you want to remove? ");
					registration = sc.nextInt();
					list.remove(registration);
					break;
				case 6:
					list.print();
					break;
				case 7:
					System.out.println("Which registration? ");
					registration = sc.nextInt();
					Node aux = list.search(registration, false);
					System.out.println(aux);
					break;
				case 8:
					list.bubbleSort();
					break;
				case 9:
					list.mergeSort();
					break;
				case 10:
					list.clean();
					break;
				case 11:
					CSV.write(list.getStartNode(), "./data/List.CSV");
					break;
				case 12:
					option = 12;
					break;
				default:
					System.out.println("Invalid digit!");
				}
			} catch (DomainException e) {
				System.out.println("Error: " + e.getMessage());
			}
			System.out.println("----------------------");
			UI.clearScreen();
		} while (option != 12);
	}

	@Override
	public void edit(int target) {
		Node aux = null;
		try {
			aux = list.search(target, false);
		} catch (DomainException e) {
			e.printStackTrace();
		}
		System.out.print("Enter a new registration: ");
		int registration = sc.nextInt();
		System.out.print("Enter a new data: ");
		sc.nextLine();
		LocalDate date = LocalDate.parse(sc.nextLine(), dtf);
		aux.setRegistration(registration);
		aux.setDate(date);
		System.out.println("Update data node! ");

	}
}