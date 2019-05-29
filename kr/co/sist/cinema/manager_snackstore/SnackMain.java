package kr.co.sist.cinema.manager_snackstore;
import java.util.Scanner;

public class SnackMain {
	
	public static void main(String[] args) {
		
		UI ui = new UI();
		
		Scanner scan = new Scanner(System.in);
		
		SnackManager store = new SnackManager();
		
		boolean loop = true;
		
		store.load();
		while(loop) {
			
			ui.menu();
			
			switch(scan.nextLine()) {
			case "1":
				store.printItemList();
				break;
			case "2":
				store.putItem();
				break;
			case "3":
				store.pullItem();
				break;
			default:
				loop = false;
				break;
			}
		}
		store.save();
	}
}
