package kr.co.sist.cinema.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import com.test.example.io.FilePath;

public class NoticeBoard {
	
	private Scanner scan;
	private Calendar now;
	
	public NoticeBoard() {
		
		scan = new Scanner(System.in);
		now = Calendar.getInstance();
	}
	
	private int noticeIndex = 11;
	private int questionIndex = 11;
	
	
	public void NoticeBoardStart() {
		
		System.out.println("프로그램을 시작합니다.");
		
		//업무
		boolean loop = true;
		
		
		while (loop) {
			
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			System.out.println("=======================");
			System.out.println("======게시판 관리======");
			System.out.println("1. 공지사항");
			System.out.println("2. 문의사항");
			System.out.println("0. 종료");
			System.out.println("=======================");
			
			System.out.print("선택(번호) : ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				notice();
				pause();
			}else if (input.equals("2")) {
				questions();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
			
			
		}
		
		
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void notice() {
		
		//업무
		boolean loop = true;
		
		while (loop) {
			
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			System.out.println("=========================");
			System.out.println("======공지사항 관리======");
			System.out.println("1. 글쓰기");
			System.out.println("2. 삭제");
			System.out.println("3. 수정");
			System.out.println("4. 글 목록 보기");
			System.out.println("0. 종료");
			System.out.println("=========================");
			
			System.out.print("선택(번호) : ");
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				noticeWriting();
				pause();
			}else if (input.equals("2")) {
				noticeDelete();
			}else if (input.equals("3")) {
				noticeModify();
			}else if (input.equals("4")) {
				noticeView();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
			
		}
		
		
	}//notice
	
	
	
	public void questions() {
		
		//업무
		boolean loop = true;
		
		while (loop) {
			
			
			//메뉴 출력 / 항목 선택 / 기능 실행
			System.out.println("=========================");
			System.out.println("======문의사항 관리======");
			System.out.println("1. 글쓰기");
			System.out.println("2. 삭제");
			System.out.println("3. 수정");
			System.out.println("4. 글 목록 보기");
			System.out.println("0. 종료");
			System.out.println("=========================");
			
			System.out.print("선택(번호) : ");
			String input = scan.nextLine();
			
			
			if(input.equals("1")) {
				questionWriting();
				pause();
			}else if (input.equals("2")) {
				questionDelete();
			}else if (input.equals("3")) {
				questionModify();
			}else if (input.equals("4")) {
				questionView();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
			
		}

		
		
		
	}//questions
	
	public void noticeView() {
		
		System.out.println("[식별코드]\t[공지제목]\t\t[공지내용]");
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.NOTICEBOARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				
				System.out.printf("%s\t%s\t : \t%s\t%s\t%s\r\n", temp[0], temp[1], temp[2], temp[3], temp[4]); //기존데이터 반환
				
				
			}
			
			
			pause();
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		
	}//공지사항 글 목록 보기
	
	
	public void noticeWriting() {
		
		System.out.println("공지사항 글쓰기를 시작합니다.");
		int c = 65;
			
			try {
				
				
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.NOTICEBOARD));
				
				
				
				System.out.print("제목 입력 : ");
				String title = scan.nextLine();
				
				System.out.print("내용 입력 : ");
				String content = scan.nextLine();
				
				String[] wrt = new String[2];
			
				wrt[0] = title;
				wrt[1] = content;
				
				
				
				writer.write(String.format("A%03d■%s■%s■%tF■%tT\r\n", noticeIndex, wrt[0], wrt[1], now, now));
				
				System.out.println("글쓰기 완료");
				writer.close();
				noticeIndex++;
				
				
		
			} catch (Exception e) {
				System.out.println(e.toString());
			}
				
		
	}//공지사항 글쓰기 메소드
	
	public void noticeModify() {
		System.out.println("[공지사항 글 수정하기]");
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		ArrayList<String[]> list2 = new ArrayList<String[]>();
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.NOTICEBOARD));
			
			String line = null;
			while((line = reader.readLine()) != null) { //리스트에 공지사항 목록데이터를 넣음
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
			
//			modifyNotice(list, reader);
			
			reader.close();
			
			System.out.print("수정하려는 글의 식별코드 : ");
			String modify = scan.nextLine();
			
			

			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.NOTICEBOARD));
			
//			for(String[] temp : list) {
//				writer.write(String.format("%s■%s■%s\r\n", temp[0], temp[1], temp[2]));
//			}
			
			
			for(String[] temp : list) { //비교값과 같을때 수정한 내용을 목록에 추가시키고 데이터를 바꿔준다.
				
				if(!temp[0].equals(modify)) {
					writer.write(String.format("%s■%s■%s■%s■%s\r\n", temp[0], temp[1], temp[2], temp[3], temp[4])); //기존데이터
				
				}else if(temp[0].equals(modify)){
					
					System.out.print("수정한 내용 : ");
					String modifyContent = scan.nextLine();
					
					writer.write(String.format("%s■%s■%s■%tF■%tT\r\n", temp[0], temp[1], modifyContent, now, now)); //글을 수정시 현재 수정한 날짜와 시간을반환
					System.out.println("내용수정 완료");
				}
				
				
			}//for문

			failCompare(list2, modify); //입력코드값과 비교했을때 다를경우 초기화면으로 리셋시킨다. 또한 같을경우 수정을 완료하고 화면으로 돌아온다.
			
			
			pause();
			writer.close();
			
			
			
		} catch (Exception e) {
		
		}
		
		
	}//공지사항 글 수정 메소드

	
	public void noticeDelete() {
		System.out.println("[공지사항 글 삭제하기]");
		
		ArrayList<String[]> list = new ArrayList<String[]>();// 공지사항 데이터를 새로 넣을 배열

		try {
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.NOTICEBOARD)) ;	
			noticeView(); //공지사항 글 정보를 보여줌
			
			deleteWrite(list, reader);
			
			reader.close();
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.NOTICEBOARD));
			
			for (String[] temp : list ) {
				writer.write(String.format("%s■%s■%s■%s■%s\r\n", temp[0], temp[1], temp[2], temp[3], temp[4]));
			}
			
			pause();
			writer.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		
		
		
	}// 공지사항 글 삭제 메소드

	private void deleteWrite(ArrayList<String[]> list, BufferedReader reader) throws IOException {
		
		String line = null;
		
		System.out.print("삭제할 글의 식별코드 : ");
		String number = scan.nextLine();
		
		System.out.println("정말로 삭제하시겠습니까?(y/n) : "); //삭제할지 한번더 묻는 확인작업
		String check = scan.nextLine();
		
		if(check.equals("y")) {
			while((line = reader.readLine()) != null) { //삭제를 y 실행하면 검색된아이디와 동일한 데이터를 삭제
				
				String[] temp = line.split("\\■");
				if (!temp[0].equals(number)) {
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
			System.out.println("잘못 입력하셨습니다. 다시 시도해주세요."); // 잘못입력시 삭제를 취소하고 원래 데이터를 다시 집어넣음
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("\\■");
				list.add(temp);
			}
		}
		
		
	}//글삭제 참조 메소드
	
	
	public void questionWriting() {
		
		System.out.println("문의사항 글쓰기를 시작합니다.");
		
			
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.QUESTIONBOARD, true));
				
				
				
				System.out.print("제목 입력 : ");
				String title = scan.nextLine();
				
				System.out.print("내용 입력 : ");
				String content = scan.nextLine();
				
				String[] wrt = new String[2];
			
				wrt[0] = title;
				wrt[1] = content;
				
				
				
				writer.write(String.format("A%03d■%s■%s■%tF■%tT\r\n", questionIndex, wrt[0], wrt[1], now, now)); //글쓰고 완료시 현재날짜와 시간을 반영
				
				System.out.println("글쓰기 완료");
				
				writer.close();
				questionIndex++;
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
				
		
	}//문의사항 글쓰기 메소드
	
	public void questionView() {
		
		System.out.println("[식별코드]\t[문의정보제목]\t\t[문의정보내용]");
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.QUESTIONBOARD));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\■");
				
				System.out.printf("%s\t%s\t : \t%s\t%s\t%s\r\n", temp[0], temp[1], temp[2], temp[3], temp[4]);
				
				
			}
			
			
			pause();
			reader.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
	}//문의사항 글목록 보기
	
	
	public void questionDelete() {
		
		System.out.println("[문의사항 글 삭제하기]");
		
		ArrayList<String[]> list = new ArrayList<String[]>();// 문의사항 데이터를 새로 넣을 배열

		try {
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.QUESTIONBOARD)) ;	
			questionView(); //문의사항 글 정보를 보여줌
			
			deleteWrite(list, reader);
			
			reader.close();
			
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.QUESTIONBOARD));
			
			for (String[] temp : list ) {
				writer.write(String.format("%s■%s■%s■%s■%s\r\n", temp[0], temp[1], temp[2], temp[3], temp[4]));
			}
			
			pause();
			writer.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}//문의사항 삭제 메소드
	
	public void questionModify() {
		
		System.out.println("[문의사항 글 수정하기]");
		
		ArrayList<String[]> list = new ArrayList<String[]>(); //실제값
		ArrayList<String[]> list2 = new ArrayList<String[]>(); //코드값을 비교하기위한 리스트2
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.QUESTIONBOARD));
			
			String line = null;
			while((line = reader.readLine()) != null) { //리스트에 문의사항 목록데이터를 넣음
				
				String[] temp = line.split("\\■");
				list.add(temp);
				
			}
	
			reader.close();
			
			System.out.print("수정하려는 글의 식별코드 : ");
			String modify = scan.nextLine();
			
			

			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.QUESTIONBOARD));

			
			
			for(String[] temp : list) {
				
				if(!temp[0].equals(modify)) {
					writer.write(String.format("%s■%s■%s■%s■%s\r\n", temp[0], temp[1], temp[2], temp[3], temp[4]));
				
				}else if(temp[0].equals(modify)){
					
					System.out.print("수정할 내용 : ");
					String modifyContent = scan.nextLine();
					
					writer.write(String.format("%s■%s■%s■%tF■%tT\r\n", temp[0], temp[1], modifyContent, now, now)); //수정한 날짜와 시간을 반영
					System.out.println("내용수정 완료");
				}
				
				
			}//for문

			failCompare(list2, modify); //입력코드값과 비교했을때 다를경우 실패를 가져옴
			
			
			pause();
			writer.close();
			
			
			
		} catch (Exception e) {
		
		}
		
		
	}
	
	
	public void pause() {
		
		System.out.print("계속 하시려면 엔터를 입력하세요.");
		scan.nextLine();
		
	}//pause
	
	public void dum() {
		int s = 1;
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.SDUM));
			
			for (int i=0; i<1000; i++) {
				System.out.printf("A%03d■\r\n",s);
				writer.write(String.format("A%03d■\r\n", s));
				s++;
			}
			
			writer.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		
	}//식별코드 비교를 위한 더미생성 메소드
	
	private void failCompare(ArrayList<String[]> list2, String modify) throws FileNotFoundException, IOException {
		
		BufferedReader reader2 = new BufferedReader(new FileReader(FilePath.SDUM));
		
		String line2 = null;
		while((line2 = reader2.readLine()) != null) { //비교목록 데이터를 넣어줌
			
			String[] temp2 = line2.split("\\■");
			list2.add(temp2);
			
		}
		
		
		for(String[] temp2 : list2) { //입력코드값과 비교하는 리스트
			if(temp2[0].equals(modify)){
				System.out.println("초기화면으로 돌아갑니다.");
				break;
			}else {
				System.out.println("초기화면으로 돌아갑니다.");
				break;
			}
		}
		
		reader2.close();
	}//입력 코드 비교 실패 메소드
	
	
}
