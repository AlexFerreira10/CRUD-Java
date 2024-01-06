package structures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import structures.exceptions.DomainException;

public class List {
	private Node startNode;
	private int counter;
	private Scanner sc;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
	
	public void edit(int searchedValue) throws DomainException, ParseException  {
	
		Node aux = sequentialSearch(searchedValue);
		System.out.print("Enter a new registration: ");
		int registration = sc.nextInt();
		System.out.print("Enter a new data: ");
		Date date = sdf.parse(sc.next());
		aux.setRegistration(registration);
		aux.setDate(date);

	}
	
	public void remove(int searchedValue) throws DomainException {
		sequentialSearch(searchedValue);// Confirm if there is this node
		Node aux = startNode;
		
		// Case 1: There is only node in the list
		if (startNode.getRegistration() == searchedValue) {
			startNode = startNode.getNextNode();
		}
		// Case 2: There are several node in this list
		else {
			while (aux.getNextNode().getRegistration() != searchedValue) {
				aux = aux.getNextNode();
			}
			aux.setNextNode(aux.getNextNode().getNextNode());
		}
		counter--;
	}
	
	public Node sequentialSearch(int searchedValue) throws DomainException {
		Node aux = startNode;
		if(startNode != null) {
			while(aux != null && aux.getRegistration() != searchedValue) {
				aux = aux.getNextNode();
			}
			if(aux == null) {
				throw new DomainException("The node didn't find! ");
			}
			else {
				return aux;
			}
		}
		else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
	}
		
	public void bubbleSort() {
	    for (int i = 0; i < counter - 1; i++) {
	        Node aux = startNode;
	        while (aux != null && aux.getNextNode() != null) {
	            if (aux.getRegistration() > aux.getNextNode().getRegistration()) {
	                Node aux2 = aux.getNextNode();
	                aux.setNextNode(aux2.getNextNode());
	                aux2.setNextNode(aux);

	                // Atualize a referência do início se o nó movido for o primeiro nó
	                if (startNode == aux) {
	                    startNode = aux2;
	                } else {
	                    Node prevAux = startNode;
	                    while (prevAux.getNextNode() != aux) {
	                        prevAux = prevAux.getNextNode();
	                    }
	                    prevAux.setNextNode(aux2);
	                }
	                aux = aux2; // Atualize a posição de 'aux'
	            }
	            aux = aux.getNextNode();
	        }
	    }
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
	
	public void menu() throws ParseException {
		int option = 0;
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
			option = sc.nextInt();
			
			try {
				switch (option) {
				case 1:// addBeginning
					System.out.print("Which registration?");
					int registration = sc.nextInt();
					System.out.print("Which date?");
					Date date = sdf.parse(sc.next());
					Node node = new Node(registration,date);
					addBeginning(node);
					break;
				case 2:
					System.out.print("Which registration?");
					registration = sc.nextInt();
					System.out.print("Which date?");
					date = sdf.parse(sc.next());
					node = new Node(registration,date);
					addMiddle(node);
					break;
				case 3:
					System.out.print("Which registration?");
					registration = sc.nextInt();
					System.out.print("Which date?");
					date = sdf.parse(sc.next());
					node = new Node(registration,date);
					addTheEnd(node);
					break;
				case 4:
					System.out.print("Which registration do you want to change? ");
					registration = sc.nextInt();
					edit(registration );
					break;
				case 5:
					System.out.print("Which registration do you want to remove? ");
					registration = sc.nextInt();
					remove(registration);
					break;
				case 6:
					print();
					break;
				case 7:
					System.out.println("Which registration?");
					registration = sc.nextInt();
					Node aux = sequentialSearch(registration);
					System.out.println(aux);
					break;
				case 8:
					
					break;
				case 9:
					bubbleSort();
					break;
				case 10:

					break;
				case 11:

					break;
				case 12:

					break;
				case 13:
					
					break;
				case 14:
					option = 13;
					break;
				default:
					System.out.println("Invalid digit!");
				}
			} catch (DomainException a) {
				System.out.println("Error: " + a.getMessage());
			}
	
		} while (option != 14);
	}
}
