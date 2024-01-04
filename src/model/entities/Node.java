package model.entities;

public class Node {
	private Integer registration;
	private Node nextNode;
	
	public Node(int registration) {
		super();
		this.registration = registration;
	}
	
	public Node(int registration, Node nextNode) {
		super();
		this.registration = registration;
		this.nextNode = nextNode;
	}

	public int getRegistration() {
		return registration;
	}

	public void setRegistration(int registration) {
		this.registration = registration;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		return "Node [registration=" + registration + "]";
	}
	
	
}
