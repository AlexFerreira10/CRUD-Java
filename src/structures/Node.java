package structures;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Node {
	private Integer registration;
	private Date date;
	private Node nextNode;
	
	public Node(int registration, Date date) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	//consertar saida data
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = sdf.format(date);
		return "Node [registration=" + registration + ", date= " + formattedDate + "]";
	}
}
