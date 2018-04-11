package restaurant;

public class DesksClient {
	private Client[] deskClient;
	private int numClient, size = 0;
	
	public DesksClient(int i) {
		deskClient = new Client[i];
		size = i;
		numClient = 0;
	}

	public Client[] getDeskClient() {
		return deskClient;
	}
	
	public boolean setDeskClient(Client dClient) {
		boolean cond = true;
		boolean flag = true;
		int i = 0;
		if (isFull()) {
			while (deskClient[i] != null && flag) {
				if (deskClient[i].getTshirt() == dClient.getTshirt()) {
					cond = false;
					flag = false;
				}
				i++;
			}
		
			if (cond) {
				deskClient[i] = dClient;
				return true;
			}
			return false;
		}	
		return false;
	}	
	
	public boolean isFull() {
		return deskClient.length == numClient;
	}

	public void mostrar() {
		for (int i = 0; i < size; i++) {
			if (deskClient[i] != null) {
				System.out.println(deskClient[i].getTshirt() + " " + deskClient[i].getTin());
			}
		}
	}
	
}
