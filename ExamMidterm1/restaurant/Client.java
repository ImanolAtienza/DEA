package restaurant;

public class Client {
	private int ntshirt; //number on the T-shirt
	private String taxnum; // Tax identification number
	private double bill; //amount to pay
	
	public Client(int tshirt, String tin, double pounds){
		ntshirt = tshirt;
		taxnum = tin;
		bill = pounds;
	}
	
	public int getTshirt(){return ntshirt;}
	public String getTin(){return taxnum;};
	public double getBill(){return bill;}
}
