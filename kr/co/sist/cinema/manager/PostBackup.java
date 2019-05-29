package kr.co.sist.cinema.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.sist.cinema.initialscreen.FileRead;

public class PostBackup extends FileRead {
	
	private int count = 1;
	private Scanner scan;
	public PostBackup() {
		scan = new Scanner(System.in);
		
	}
	
	public void PostManagerStart() {
		
		System.out.println("프로그램 시작");
		
		boolean loop = true;
		
		while(loop) {
			try {
				UIpost.begin();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String input = scan.nextLine();
			
			if(input.equals("1")) {
				System.out.println("[무비포스트 열람]");
				postView();
				pause();
			}else if (input.equals("2")) {
				postDelete();
				pause();
			}else if (input.equals("3")) {
				editPost();
				pause();
			}else if (input.equals("0")) {
				loop = false;
			}else {
				System.out.println("잘못 입력하셨습니다. 다시 번호를 선택해 주세요.");
			}
			
		}
		System.out.println("프로그램을 종료합니다.");
	}//시작
		
	public void editPost() {
		
		System.out.println("[무비포스트 수정]");
		ArrayList<String> list2 = new ArrayList<String>(); //수정데이터를 넣을 배열
		
		initialReader("post.dat"); // 메모장읽어오기
		
//		String[] temp = list.get(0).split("\\■");
//		int postIndex = Integer.parseInt(temp[0]);
//		
//		postIndex++;
		String read = "";
		String id = "woojin";
				
		if(id.equals("woojin")) {
			
			
			
			System.out.print("수정하고자 하는 글의 식별코드 : "); //내가원하는 식별코드 입력
			int iNum = scan.nextInt();
			scan.nextLine();
			
			
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "post.dat")); //다시쓸 wirter부름
				
				for(int i=0; i<list.size(); i++) {
					String[] division = list.get(i).split("\\■"); // division에 ■별로 하나씩 넣어줌 division[0]~division[6]
					
					if(!division[0].equals(String.format("%03d", iNum))) { // 만약 division[0]과 받아온 식별코드가 다를시 원래 데이터를 가져옴
						list2.add(list.get(i)+"\r\n");
					}else if(division[0].equals(String.format("%03d", iNum))) { // division[0]과 받아온 식별코드가 같을시 내용수정 루프로 들어감
						
						boolean loop = true;
						
						while(loop) {
							System.out.println("===================");
							System.out.println("1. 제목만 수정");
							System.out.println("2. 내용만 수정");
							System.out.println("3. 평점만 수정");
							System.out.println("4. 전부 수정");
							System.out.println("0. 종료");
							System.out.println("===================");
							System.out.print("번호 선택 : ");
							
							String input = scan.nextLine();
							if(input.equals("1")) { 
								System.out.print("수정하고자 하는 제목 : ");
								String title = scan.nextLine();
								
								writer.write(String.format("%03d■%s■%s■%s■%s■%s■%s\r\n"
															, iNum, division[1], division[2]
															, title, division[4], division[5], division[6])); //제목만
								
								System.out.println("내용 수정 완료");
								break;
							}else if(input.equals("2")) {
								boolean loop2 = true;
								
								System.out.print("수정하고자 하는 내용(빈내용 입력시 종료) : ");
								while(loop2) {
									String content = scan.nextLine();
									if(content.isEmpty()) {
										break;
									}
									read += content + " ";
								}
								writer.write(String.format("%03d■%s■%s■%s■%s■%s■%s\r\n"
															, iNum, division[1], division[2]
															, division[3], division[4], division[5], read)); //내용만
								
								System.out.println("내용 수정 완료");
								break;
							}else if(input.equals("3")) {
								System.out.print("수정하고자 하는 평점 : ");
								String score = scan.nextLine();
								
								if(score.equals("1") || score.equals("2") || score.equals("3") || score.equals("4") || score.equals("5")) {
									writer.write(String.format("%03d■%s■%s■%s■%s■%s■%s\r\n"
																, iNum, division[1], division[2]
																, division[3], division[4], score, division[6])); //평점만
									
									System.out.println("내용 수정 완료");
									break;
								}else {
									System.out.println("잘못 입력하셨습니다.");
									
								}
								
							}else if(input.equals("4")) {
								System.out.print("수정하고자 하는 제목 : ");
								String title = scan.nextLine();
								
								System.out.print("수정하고자 하는 내용 : ");
								boolean loop3 = true;
								while(loop3) {
									String content = scan.nextLine();
									if(content.isEmpty()) {
										break;
									}
									read += content + " ";
								}
								
								System.out.print("수정하고자 하는 평점 : ");
								String score = scan.nextLine();
								
								if(score.equals("1") || score.equals("2") || score.equals("3") || score.equals("4") || score.equals("5")) {
									writer.write(String.format("%03d■%s■%s■%s■%s■%s■%s\r\n"
																, iNum, division[1], division[2]
																, title, division[4], score, read)); //제목 평점 내용
									
									System.out.println("내용 수정 완료");
									break;
								}else {
									System.out.println("잘못 입력하셨습니다.");
									
								}
							}else if(input.equals("0")) {
								writer.write(String.format("%03d■%s■%s■%s■%s■%s■%s\r\n"
										, iNum, division[1], division[2]
										, division[3], division[4], division[5], division[6])); // 원래코드
								loop = false;
							}
							
						}
					
					}//if문
				
				
				
				}//for
				
				for(int i=0; i<list2.size(); i++) {
					writer.write(list2.get(i));
				}
				
				writer.close();
				System.out.println("초기화면으로 돌아갑니다.");
			} catch (FileNotFoundException e) {
				System.out.println("post.dat" + " 경로 없음");
			}
			catch(IOException e )
			{
				e.printStackTrace();
			}
		
		}else {
			System.out.println("글이 없습니다.");
		}
		
	}//포스트 수정하는 작업// 내용별로 // 메소드만 따로 봐야함

	public void postView() {

		System.out.println("[영화명고유번호]\t[작성시간]\t[아이디]\t[제목]\t[노출여부]\t[평점]\t[내용]");
		
		initialReader("post.dat");
		for(int i=0; i<list.size(); i++) {
			String[] division = list.get(i).split("\\■");
			
			System.out.printf("%s\t\t\t%s\t%s\t%s\t"
								+ "%s\t%s\t%s\r\n"
								, division[0], division[1], division[2], division[3]
								, division[4], division[5], division[6]);
		
		}
		
		
	}//post열람
		
	
	public void postDelete() {
		
		ArrayList<String> list2 = new ArrayList<String>();// 무비포스트 정보를 새로 넣을 배열
		
		System.out.println("[무비포스트 삭제]");
		
		postView();
		
		System.out.print("식별 코드(번호) : ");
		String iNum = scan.nextLine();
		
		initialReader("post.dat");
		
		String noiNum = "";
		for(int i=0; i<list.size(); i++) {
			String[] division = list.get(i).split("\\■"); 
			
			if(iNum.equals(division[0])) {
				noiNum = division[0];
			}else {
				list2.add(list.get(i));
			}
			
		}//for
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "post.dat"));
		
			for(int i=0; i<list2.size(); i++) {
				writer.write(list2.get(i) + "\r\n");
			}

			
			writer.close();
			
		}catch (FileNotFoundException e) {
			System.out.println("post.dat 파일 없음");
		}catch (Exception e) {
			System.out.println();
		}
		if(!noiNum.equals("")) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패(해당 데이터의 식별 번호가 없습니다.)");
		}
		
		
		
		
	}//post 삭제
	
	
}//postManager