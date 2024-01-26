package application;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import structures.List;
import structures.exceptions.DomainException;
import structures.files.CSV;

public class Main {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		List list = new List();
		MenuList menu = new MenuList(list, sc);
		
		try {
			list = CSV.read(list, "./data/List.CSV");
			menu.menu();
		}
		catch(InputMismatchException e) {
			System.out.println("Error: " + e.getMessage());
			sc.nextLine();
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			sc.nextLine();
		} catch (DomainException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			sc.nextLine();
		}
		finally {
			sc.close();
		}
	}
}
