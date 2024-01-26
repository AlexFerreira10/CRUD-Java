package structures;

import java.time.LocalDate;

public class Node {
	private Integer registration;
	private LocalDate date;
	private Node nextNode;
	
	public Node(int registration) {
		super();
		this.registration = registration;
	}
	
	public Node(int registration, LocalDate date) {
		super();
		this.registration = registration;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	//consertar saida data
	public String toString() {
		return "Node [registration=" + registration + ", date= " + date + "]";
	}
}
