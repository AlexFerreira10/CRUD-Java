package model.services;

import java.util.Scanner;

import model.entities.Node;
import model.exceptions.DomainException;

public class List {
	private Node startNode;
	private int counter;
	private Scanner sc;

	public List() {
	}

	public List(Scanner sc) {
		super();
		this.sc = sc;
	}

	public List(Node startNode, int counter, Scanner sc) {
		super();
		this.startNode = startNode;
		this.counter = counter;
		this.sc = sc;
	}

	public Node getStartNode() {
		return startNode;
	}

//I guess that won'nt use it
	/*
	 * public void setStartNode(Node startNode) { this.startNode = startNode; }
	 */
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void addBeginning(Node node) {
		/*
		 * Easy mode //Case 1: List is empty if(startNode == null) { startNode = node;
		 * node.setNextNode(null); } //Case 2: In list there is only or there are
		 * several node else { Node aux = startNode; startNode = node;
		 * node.setNextNode(aux); }
		 */

		// Optimized mode
		node.setNextNode(startNode);
		startNode = node;
		counter++;
	}

	public void addMiddle(Node node) throws DomainException {
		Node aux = startNode;
		if (startNode != null && startNode.getNextNode() != null) {

			for (int i = 1; i < (counter / 2); i++) {
				aux = aux.getNextNode();
			}

			node.setNextNode(aux.getNextNode());
			aux.setNextNode(node);
		} else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
		counter++;
	}

	public void addTheEnd(Node node) throws DomainException {
		Node aux = startNode;
		if (startNode != null) {
			while (aux.getNextNode() != null) {
				aux = aux.getNextNode();
			}
			aux.setNextNode(node);
			node.setNextNode(null);
		} else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
		counter++;
	}

	public void print() throws DomainException {
		Node aux = startNode;
		if (startNode != null) {
			while (aux != null) {
				System.out.println(aux);
				aux = aux.getNextNode();
			}
			System.out.println("Quantify of node: " + counter);
		} else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
	}

	public void menu() {
		int opcao = 0;

		do {
			System.out.println("-------- List --------");
			StringBuilder sb = new StringBuilder();
			sb.append("[ 1 ] Insert (Begnning)\n");
			sb.append("[ 2 ] Insert (Middle)\n");
			sb.append("[ 3 ] Insert (The end)\n");
			sb.append("[ 4 ] Edit\n");
			sb.append("[ 5 ] Remove\n");
			sb.append("[ 6 ] Print\n");
			sb.append("[ 7 ] Search(Sequential)\n");
			sb.append("[ 8 ] Search(Binária)\n");
			sb.append("[ 9 ] Sort (BubleSort)\n");
			sb.append("[ 10 ] Sort (CollectionsSort)\n");
			sb.append("[ 11 ] Sort (QuickSort)\n");
			sb.append("[ 12 ] Clean\n");
			sb.append("[ 13 ] Save\n");
			sb.append("[ 14 ] Exit\n");
			sb.append("Escolha uma opção: ");
			System.out.println(sb);
			int option = sc.nextInt();
			try {
				switch (option) {
				case 1:// addBeginning
					System.out.println("Which registration?");
					int registration = sc.nextInt();
					Node node = new Node(registration);
					addBeginning(node);
					break;
				case 2:
					System.out.println("Which registration?");
					registration = sc.nextInt();
					node = new Node(registration);
					addMiddle(node);
					break;
				case 3:
					System.out.println("Which registration?");
					registration = sc.nextInt();
					node = new Node(registration);
					addTheEnd(node);
					break;
				case 4:

					break;
				case 5:

					break;
				case 6:
					print();
					break;
				case 7:

					break;
				case 8:

					break;
				case 9:

					break;
				case 10:

					break;
				case 11:

					break;
				case 12:

					break;
				case 13:
					opcao = 13;

					break;
				case 14:

					break;
				default:
					System.out.println("Invalid digit!");
				}
			} catch (DomainException a) {
				System.out.println("Error: " + a.getMessage());
			}
			
		} while (opcao != 13);
	}
}
