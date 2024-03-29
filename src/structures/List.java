package structures;

import java.time.format.DateTimeFormatter;
import structures.exceptions.DomainException;

public class List {
	protected Node startNode;
	protected int counter;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public List() {
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
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
			counter++;
			System.out.println("Node added!");
		} else {
			throw new DomainException("There aren't suficient nodes in the list! ");
		}
	}

	public void addTheEnd(Node node) throws DomainException {
		Node aux = startNode;
		if (startNode != null) {
			while (aux.getNextNode() != null) {
				aux = aux.getNextNode();
			}
			aux.setNextNode(node);
			node.setNextNode(null);

			counter++;
			System.out.println("Node added!");
		} else { // improve this later
			node.setNextNode(startNode);
			startNode = node;
			counter++;
		}
	}

	public void remove(int searchedValue) throws DomainException {
		search(searchedValue, true);// Confirm if there is this node
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
	
	//Verification is used to confirmation
	public Node search(int target, boolean verification) throws DomainException {
		Node aux = startNode;
		if (startNode != null) {
			while (aux != null && aux.getRegistration() != target) {
				aux = aux.getNextNode();
			}
			if (aux == null && !verification) {
				throw new DomainException("The node didn't find! ");
			} else {
				return aux;
			}
		} else {
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
					// If the swap is between the first two nodes
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

	public void mergeSort() throws DomainException {

		if (startNode != null) {
			startNode = mergeSort(startNode);
			System.out.println("List is ordered!");
		} else {
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
}
