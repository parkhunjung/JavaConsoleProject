package kr.co.sist.opgg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.test.example.io.FilePath;

public class MemberManage {
	
	private Scanner scan;
	public static ArrayList<Member> members;
	
	public MemberManage() {
		scan = new Scanner(System.in);

	}
	static { 
		members = new ArrayList<Member>();
	}
	
	public void start() {
		
		boolean loop = true;
		
		while(loop) {
			System.out.println("===========================");
			System.out.println("1. 회원 보기");
			System.out.println("2. 회원 검색");
			System.out.println("3. 회원 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 회원 일부정보 보기");
			System.out.println("0. 종료");
			System.out.println("===========================");
			System.out.print("번호 선택 : ");
			String input = scan.nextLine();
			if(input.equals("1")) {
				memberView();
			}else if(input.equals("2")) {
				
			}else if(input.equals("3")) {
				
			}else if(input.equals("4")) {
				
			}else if(input.equals("5")) {
				load();
				setMember();
			}else if(input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			
		}
		
		
		
		
	}//start
	
	public void memberView() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.TEST2));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("\\■");
				System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\r\n", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
			}
			
			
			
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString() + "멤버없음");
		}
		
		
		
		
		
		
	}//멤버보기
	
	public void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FilePath.TEST2));
			
			String line = null;
			
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("\\■");
				Member m2 = new Member();
				
				m2.setSerialNum(temp[0]);
				m2.setName(temp[4]);
				m2.setAge(temp[5]);
				
				members.add(m2);
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString() + "잘못 입력된값");
		}
		
		
		
		
		
		
	}//불러오기
	
	public void setMember() {
		
		int countList = 1;
		
		System.out.print("찾고자 하는 아이디 식별번호 : ");
		String num = scan.nextLine();
		
		for(Member member : members) {
			
			String number = member.getSerialNum();
			if(num.equals(number)) {
				System.out.printf("%s번\t%s\t%s\r\n", member.getSerialNum(), member.getName(), member.getAge());
			}
			
			
		}
		
		
		
		
	}//가져오기
	
}

























