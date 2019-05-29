package kr.co.sist.cinema.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.test.example.io.FilePath;

public class CardManage {
	
	private Scanner scan;
	
	public CardManage() {
		scan = new Scanner(System.in);
	}
	
	
	public void start() {
		
		System.out.println("프로그램을 시작합니다.");
		
		boolean loop = true;
		
		while (loop) { // 프로그램 시작 및 반복실행
			
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			System.out.println("===========================");
			System.out.println("========카드사 선택========");
			System.out.println("1. 롯데 카드");
			System.out.println("2. 삼성 카드");
			System.out.println("3. 신한 카드");
			System.out.println("4. 하나 카드");
			System.out.println("5. 현대 카드");
			System.out.println("6. 비씨 카드");
			System.out.println("7. 현재 카드 할인율 정보");
			System.out.println("0. 종료");
			System.out.println("===========================");
			
			System.out.print("선택(번호) : ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				disLotteCard();
			}else if (input.equals("2")) {
				disSamsungCard();
			}else if (input.equals("3")) {
				disSinhanCard();
			}else if (input.equals("4")) {
				disHanaCard();
			}else if (input.equals("5")) {
				disHyundaeCard();
			}else if (input.equals("6")) {
				disBcCard();
			}else if (input.equals("7")) {
				cardView();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
			
			
		}
		
		
	}//start
	
	public void cardView() { // 메모장에 만들어진 데이터 및 수정된 카드할인율을 확인할수 있게 해주는 카드뷰
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				
				System.out.printf("%s\t%s\t%s\r\n", temp[0], temp[1], temp[2]);
				
				
			}
					
			reader.close();
			
			pause();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		
	}
	
	
	/**
	 * 카드 데이터 생성 메소드
	 */
	public void card() {
		int count = 6;
		
//		String[] cardList = new String[count];
		
		String[] cardNumber = {"A001", "A002", "A003", "A004", "A005", "A006"};
		
		String[] cardName = {"롯데 카드", "삼성 카드", "신한 카드", "하나 카드", "현대 카드", "비씨 카드"};
		
		String[] cardDiscount = {"5", "10", "15", "20" ,"25", "30"};
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			for(int i=0; i<count; i++) {
				
				writer.write(String.format("%s■%s■%s\r\n"
										, cardNumber[i]
										, cardName[i]
										, cardDiscount[i]));
				
			}//생성된 배열을 메모장에 입력
			
			System.out.println("작성 완료");
			writer.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}//card 데이터 만들기
	
	public void disLotteCard() {
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		System.out.print("입력(할인율) : ");
		String disInput = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) { // list에 읽어온 데이터 전부를 넣음
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close();
			
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			
			for(String[] temp : list) { //list에 있던 데이터를 temp배열에 순서대로 넣는중
				if(temp[0].equals("A001")) { //고유번호 A001 을 만나는 순간 할인율 적용 변경
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], disInput));
				}else {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
				}
				
			}
			System.out.println("할인율 적용 완료");
			pause();
			writer.close();
			
			
		} catch (Exception e) {
			
		}	
			
			
	}//롯데카드
	
	public void disSamsungCard() {
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		System.out.print("입력(할인율) : ");
		String disInput = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close();
			
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			
			for(String[] temp : list) {
				if(temp[0].equals("A002")) {//고유번호 A002 을 만나는 순간 할인율 적용 변경
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], disInput));
				}else {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
				}
				
			}
			System.out.println("할인율 적용 완료");
			pause();
			writer.close();
			
			
		} catch (Exception e) {
			
		}	
			
			
	}//삼성카드
	
	public void disSinhanCard() {
	
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		System.out.print("입력(할인율) : ");
		String disInput = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close();
			
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			
			for(String[] temp : list) {
				if(temp[0].equals("A003")) {//고유번호 A003 을 만나는 순간 할인율 적용 변경
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], disInput));
				}else {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
				}
				
			}
			System.out.println("할인율 적용 완료");
			pause();
			writer.close();
			
			
		} catch (Exception e) {
			
		}	
		
		
	}//신한카드
	
	public void disHanaCard() {
	
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		System.out.print("입력(할인율) : ");
		String disInput = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close();
			
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			
			for(String[] temp : list) {
				if(temp[0].equals("A004")) {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], disInput));
				}else {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
				}
				
			}
			System.out.println("할인율 적용 완료");
			pause();
			writer.close();
			
			
		} catch (Exception e) {
			
		}	
		
		
	}//하나카드
	
	public void disHyundaeCard() {
	
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		System.out.print("입력(할인율) : ");
		String disInput = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close();
			
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			
			for(String[] temp : list) {
				if(temp[0].equals("A005")) {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], disInput));
				}else {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
				}
				
			}
			System.out.println("할인율 적용 완료");
			pause();
			writer.close();
			
			
		} catch (Exception e) {
			
		}	
		
		
	}//현대카드
	
	public void disBcCard() {
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		System.out.print("입력(할인율) : ");
		String disInput = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.CARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close();
			
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.CARD));
			
			
			for(String[] temp : list) {
				if(temp[0].equals("A006")) {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], disInput));
				}else {
					writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
				}
				
			}
			
			System.out.println("할인율 변경 완료");
			pause();
			writer.close();
			
			
		} catch (Exception e) {
			
		}	
			
			
	}//비씨카드
	
	public void pause() {
		
		System.out.print("계속 하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
	}
	// 중간에 멈춰주는 포즈
}





