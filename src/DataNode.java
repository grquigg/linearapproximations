
public class DataNode {
	/*
	 * This class acts similarly to a Node in a basic doubly linked list, with extra fields added for data, input and calculation
	 */
	
	private double x; 		//initial x
	private double x1; 		//final x
	private double y; 		//initial y
	private double y1;		//final y
	private double slope;	//m
	
	DataNode next;
	DataNode previous;
	
	public DataNode() {
		next = null;
		previous = null;
	}
	
	public void setInitX(double a) {
		x = a;
	}
	
	public double getInitX() {
		return x;
	}
	
	public void setInitY(double b) {
		y = b;
	}
	
	public double getInitY() {
		return y;
	}
	
	/**
	 * This method is for setting the next field of this DataNode 
	 * @param nextSet - the DataNode that next will point to
	 */
	public void setNext(DataNode nextSet) {
		next = nextSet;
	}
	
	/**
	 * This method is for setting the previous field of this DataNode 
	 * @param previousNode - the DataNode that previous will point to
	 */
	public void setPrevious(DataNode previousNode) {
		previous = previousNode;
	}
	
	public void setSlope(double x) {
		slope = x;
	}
	public double getSlope() {
		return slope;
	}
	
	public void setFinX(double c) {
		x1 = c;
	}
	
	public double getFinX() {
		return x1;
	}
	
	public void setFinY(double d) {
		y1 = d;
	}
	
	public double getFinY() {
		return y1;
	}
}
