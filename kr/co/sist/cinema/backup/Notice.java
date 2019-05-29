package kr.co.sist.cinema.backup;

public class Notice {
	public static void main(String[] args) {
		
		head();
		
	}

	private static void head() {
		int wall = 30;
		
//		for(int i=0; i<wall; i++) {
//			System.out.println("▣");
//		}
		for(int i=0; i<wall; i++) {
			System.out.print("▣");
		}
		
		for(int j=0; j<25; j++) {
			System.out.println("▣");
			for(int i=0; i<wall*2; i++) {
				System.out.print(" ");
			}
			System.out.println("▣");
		}
		
		
		
		
	}
}



