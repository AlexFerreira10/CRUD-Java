package application;

import java.util.Scanner;

public abstract class UI {
	
	protected Scanner sc;
	
	public abstract void menu();
	
	public abstract void edit(int target);
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
