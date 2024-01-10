package structures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import structures.exceptions.DomainException;

public class List {
	private static Node startNode;
	private static int counter;
	private Scanner sc;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public List() {
	}

	public List(Scanner sc) {
		super();
		this.sc = sc;
	}
	
	public List(Node startNode) {
		super();
		List.startNode = startNode;
	}

	public List(Node startNode, int counter, Scanner sc) {
		super();
		List.startNode = startNode;
		List.counter = counter;
		this.sc = sc;
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		List.startNode = startNode;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		List.counter = counter;
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
			counter++;
			System.out.println("Node added!");
		} else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
	}

	//verificar se ha uma logica melhor, para nao precisar utilizar variaveis estaticas
	//fiz essas modificacoes por causa da classe de arquivos
	public static void addTheEnd(Node node) throws DomainException  {
		Node aux = startNode;
		if (startNode != null) {
			while (aux.getNextNode() != null) {
				aux = aux.getNextNode();
			}
			aux.setNextNode(node);
			node.setNextNode(null);
			
			counter++;
			System.out.println("Node added!");
		} else { //improve this later
			node.setNextNode(startNode);
			startNode = node;
			counter++;
		}
	}
	
	public void edit(int searchedValue) throws DomainException, ParseException  {
	
		Node aux = search(searchedValue);
		System.out.print("Enter a new registration: ");
		int registration = sc.nextInt();
		System.out.print("Enter a new data: ");
		Date date = sdf.parse(sc.next());
		aux.setRegistration(registration);
		aux.setDate(date);
		System.out.println("Update data node! ");

	}
	
	public void remove(int searchedValue) throws DomainException {
		search(searchedValue);// Confirm if there is this node
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
		System.out.println("Node removed!");
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

	public Node search(int target) throws DomainException {
		Node aux = startNode;
		if(startNode != null) {
			while(aux != null && aux.getRegistration() != target) {
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
	    for (int i = 0; i < counter; i++) {
	        Node currentNode = startNode;
	        Node previousNode = null;

	        while (currentNode != null && currentNode.getNextNode() != null) {
	            Node nextNode = currentNode.getNextNode();

	            if (currentNode.getRegistration() > nextNode.getRegistration()) {
	                //If the swap is between the first two nodes
	                if (previousNode == null) {
	                    startNode = nextNode;
	                } else {
	                    previousNode.setNextNode(nextNode);
	                }

	                // Swap between node
	                currentNode.setNextNode(nextNode.getNextNode());
	                nextNode.setNextNode(currentNode);
	                
	                // Update reference from previous node to next swapped node
	                previousNode = nextNode;
	            } else {
	                previousNode = currentNode;
	                currentNode = nextNode;
	            }
	        }
	    }
	    System.out.println("List is ordered!");
	}

	public void mergeSort() throws DomainException{
		
		if(startNode != null) {
			startNode = mergeSort(startNode);
			System.out.println("List is ordered!");
		}
		else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
	}
	
	private Node mergeSort(Node head) {
		if (head == null || head.getNextNode() == null) {
			return head;
		}

		// Split the list in half; half = metade
		Node middle = getMiddle(head);
		Node nextToMiddle = middle.getNextNode();
		middle.setNextNode(null);

		// Recursively sort the two halves
		Node left = mergeSort(head);
		Node right = mergeSort(nextToMiddle);

		// Merge the two ordered halves
		return merge(left, right);

	}

	private Node merge(Node left, Node right) {
		Node result = null;
		// Choose the smallest value between left and right
		 if (left == null) {
	            return right;
	        } else if (right == null) {
	            return left;
	        }

		 	// Choose the smallest value between left and right
	        if (left.getRegistration() <= right.getRegistration()) {
	            result = left;
	            result.setNextNode(merge(left.getNextNode(), right));
	        } else {
	            result = right;
	            result.setNextNode(merge(left, right.getNextNode()));
	        }

	        return result;
	}

	private Node getMiddle(Node head) {
		// Could also do a for loop until counter/2
		 if (head == null) {
	            return null;
	        }

	        Node slow = head;
	        Node fast = head.getNextNode();

	        while (fast != null) {
	            fast = fast.getNextNode();
	            if (fast != null) {
	                slow = slow.getNextNode();
	                fast = fast.getNextNode();
	            }
	        }

	        return slow;
	}
	
	public void clean() {
		startNode = null;
		System.out.println("Lista is empty!");
	}
	
	public void menu() throws ParseException {
		int option = 0;
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
		
			try {
				switch (option) {
				case 1:// addBeginning
					System.out.print("Which registration? ");
					int registration = sc.nextInt();
					System.out.print("Which date? ");
					Date date = sdf.parse(sc.next());
					Node node = new Node(registration,date);
					addBeginning(node);
					break;
				case 2:
					System.out.print("Which registration? ");
					registration = sc.nextInt();
					System.out.print("Which date? ");
					date = sdf.parse(sc.next());
					node = new Node(registration,date);
					addMiddle(node);
					break;
				case 3:
					System.out.print("Which registration? ");
					registration = sc.nextInt();
					System.out.print("Which date? ");
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
					System.out.println("Which registration? ");
					registration = sc.nextInt();
					Node aux = search(registration);
					System.out.println(aux);
					break;
				case 8:
					bubbleSort();
					break;
				case 9:
					mergeSort();
					break;
				case 10:
					//salve
					break;
				case 11:
					clean();
					break;
				case 12:
					option = 12;
					break;
				default:
					System.out.println("Invalid digit!");
				}
			} catch (DomainException a) {
				System.out.println("Error: " + a.getMessage());
			}
			System.out.println("----------------------");
		} while (option != 12);
	}
}
