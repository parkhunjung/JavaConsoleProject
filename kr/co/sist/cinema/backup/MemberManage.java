package kr.co.sist.cinema.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.test.example.io.FilePath;

public class MemberManage {
	
	private Scanner scan;
	
	public MemberManage() {
		scan = new Scanner(System.in);
	}
	
	
	public void start() {
		
		System.out.println("프로그램을 시작합니다.");
		
		//업무
		boolean loop = true;

		numberViews(); //관람횟수 수정메소드
		
		while (loop) {
			
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			System.out.println("=====================");
			System.out.println("회원정보 관리");
			System.out.println("1. 회원 보기");
			System.out.println("2. 회원 검색");
			System.out.println("3. 회원 삭제");
			System.out.println("0. 종료");
			System.out.println("=====================");
			
			System.out.print("선택(번호) : ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				memberView();
				pause();
			}else if (input.equals("2")) {
				memberSearch();
			}else if (input.equals("3")) {
				memberDelete();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
			
			
		}
		
		
		
		
		System.out.println("프로그램을 종료합니다.");
	}
	
	/**
	 * 회원 삭제
	 */
	public void memberDelete() {
		
		System.out.println("[회원 정보 삭제]");
	
		//HashMap<Integer, String[]> hash = new HashMap<Integer, String[]>();
		ArrayList<String[]> list = new ArrayList<String[]>();// 회원 정보를 새로 넣을 배열
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.MEMBER));
			memberView(); // 멤버뷰를 불러와서 정보를 출력해줌
			
			deleteId(list, reader);

			reader.close();//리더종료
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.MEMBER));
			
			
			for (String[] temp : list) {
//				System.out.println(Arrays.toString(temp));
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■\r\n"
						, temp[0], temp[1], temp[2], temp[3], temp[4]
							, temp[5], temp[6], temp[7], temp[8], temp[9]));
				
			}// list에 검색된 이름을 제외한 모든회원이 들어간 리스트를 새롭게 메모장에 입력
		
			pause();
			writer.close();
			
		} 
		catch (Exception e) {
			e.getMessage();
		}
		
	}//delete

	
	
	// 검색된 번호를 제외한 모든 회원을 집어넣는 루프
//	private void mNumber(ArrayList<String[]> list, BufferedReader reader) throws IOException {
//		
//		System.out.println("(번호 형식을 맞춰주세요.)");
//		System.out.print("삭제할 회원전화번호 : ");
//		String number = scan.nextLine();
//		
//		String line;
//		while((line = reader.readLine()) != null) {
//			
//			String[] temp = line.split("\\t");
//			if (!temp[0].equals(number)) {
//				list.add(temp);
//			}
//			
//		}
//	}
	
	
	
	
	// 검색된 이름을 제외한 모든 회원을 집어넣는 루프
	private void deleteId(ArrayList<String[]> list, BufferedReader reader) throws IOException {
		
		System.out.print("삭제할 아이디 : ");
		String id = scan.nextLine();
		
		String line = null;
		
		System.out.println("정말로 삭제하시겠습니까?(y/n) : "); //삭제할지 한번더 묻는 확인작업
		String check = scan.nextLine();
		if(check.equals("y")) {
			while((line = reader.readLine()) != null) { //삭제를 y 실행하면 검색된아이디와 동일한 데이터를 삭제
				
				String[] temp = line.split("\\■");
				if (!temp[0].equals(id)) {
					list.add(temp);
				}
				
			}
			System.out.println("삭제 완료");
		}else if(check.equals("n")) {
			System.out.println("삭제를 취소합니다."); // 삭제를 취소하고 원래 데이터를 다시 집어넣음
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("\\■");
				list.add(temp);
			}
		}else {
			System.out.println("잘못 입력하셨습니다."); // 잘못입력시 삭제를 취소하고 원래 데이터를 다시 집어넣음
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("\\■");
				list.add(temp);
			}
		}
			
	}//데이터 삭제
	
	/**
	 * 회원목록 읽어오기
	 */
	public void memberView() {
		System.out.println("[회원 정보 보기]");
		
		System.out.println("[아이디]\t[패스워드]\t[성별]\t[이름]\t[나이]\t[전화]\t\t[주소]\t\t\t\t[저번달 관람횟수]\t[이번달 관람횟수]\t[포인트]");
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.MEMBER));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
//				System.out.println(line);
				String[] temp = line.split("\\■"); //회원정보를 \\■구분자를 이용하여 출력
				
				System.out.printf("%s\t%s\t%s\t%s\t%6d\t%s\t%s\t%17d\t%17d\t%8d\t\r\n"
						, temp[0], temp[1], temp[2], temp[3], Integer.parseInt(temp[4]), temp[5], temp[6]
								, Integer.parseInt(temp[7]), Integer.parseInt(temp[8]), Integer.parseInt(temp[9]));
				
				
			}
			
			
			reader.close();
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
		}
		
		
		
		
	}//reading
	
	
	/**
	 * 회원검색
	 */
	public void memberSearch() {
		
		System.out.print("검색할 아이디 : ");
		String sId = scan.nextLine();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.MEMBER));
			
			String line = null;
			String[] user = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
			
				if (temp[0].equals(sId)) { // 회원정보와 입력된 id가 같으면 그 데이터를 출력해준다.
					
					user = temp;
					
				} 
				
			}
			
			
			if (user != null) {
				System.out.println("[아이디]\t[패스워드]\t[성별]\t[이름]\t[나이]\t[전화]\t\t[주소]\t\t\t\t[저번달 관람횟수]\t[이번달 관람횟수]\t[포인트]"
						+ "");
				
				System.out.printf("%s\t%s\t%s\t%s\t%6d\t%s\t%s\t%17d\t%17d\t%8d\t\r\n"
						, user[0], user[1], user[2], user[3], Integer.parseInt(user[4]), user[5], user[6]
								, Integer.parseInt(user[7]), Integer.parseInt(user[8]), Integer.parseInt(user[9]));
				
			} else {
				System.out.println("없는 아이디입니다. 다시 입력해주세요.");
			}
			
			System.out.println("검색 완료");
			pause();
			reader.close();
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
		}
		
	}//search
	
	
	/**
	 * 이번달 관람횟수를 저번달관람횟수로 이동 및 이번달관람횟수 0으로 초기화
	 */
	public void numberViews() {
		
		String space = "0";
		Calendar now = Calendar.getInstance();
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		try {
			

			//데이터를 읽어와서 list 에 때려넣음
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.MEMBER));
			
//			memberView();
			
			String line = null;
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
			reader.close(); 
			
			
			
			//list의 데이터를 수정 이번달과 저번달 스윗칭
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.MEMBER));
			
			
			
			for(String[] temp : list) {
				if(now.get(Calendar.DATE) != 1) { //날짜가 1일이 아닐경우 그대로 출력
					writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■\r\n"
							, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8], temp[9]));
				}else { // 오늘 날짜가 1일일 경우 이번달의 데이터를 저번달로 옮기고 이번달은 0으로 초기화한다.
					writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■\r\n"
							, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], space, temp[9]));
				}
				
			}
			
			writer.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		
		
	}//numberViews
	
	
	public void pause() {
		
		System.out.print("계속 하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
	} //pause
	

	
	
}//











