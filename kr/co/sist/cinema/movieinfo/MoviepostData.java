package kr.co.sist.cinema.movieinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class MoviepostData {
	
	private static void moviePostData() {
			// TODO Auto-generated method stub
			
		}
	
	
	//확인용
	public static void main(String[] args) {
		moviePostDataAdd();
	}
	public static void moviePostDataAdd() {
		try {
			//무비포스트 데이터 파일 경로
			String path = "D:\\class\\java\\cinema\\data\\post.dat";
			
			Random rnd = new Random();
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			/*
			[무비포스트데이터 종류]
			영화명고유번호 PostSerialNum
			작성시간 PublishedDate
			아이디	id
			제목	Head
			내용	Body
			노출여부(0,1) IsPublic
			평점	Rating
			 */
			
			
			
			
			
			String[] id1 = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
					,"A","B","C","D"};
			String[] id2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
			
			String[] dummyHead = {"여행","그리고","좋은","기묘한","형편없는","놓쳐버리는","몰입도","최고의","원래","만약","그 순간", "안타깝다","과연","보고 왔다","어떻게","목격자가","현실","가상의","영화!","출신","변호사","검사","범인","마음","사랑","다툼","결혼","솔직 리뷰","이유","관람후기","..."};
		
			String[] dummyBody = {"리뷰를 남깁니다.","누구였을까요..","개연성없는 스토리","당연한 결과다.","최고의 영화","울 수 밖에 없는","설레는","감동과 여운","이별을 위로하는","여행과 힐링","그리고","좋은","기묘한 스토리","형편없는","놓쳐버리는","몰입도","최고의","원래","만약","그 순간", "안타깝다","과연","보고 왔다","어떻게","목격자가","현실","가상의","영화!","출신","변호사","검사","범인","마음","사랑","다툼","결혼","솔직 리뷰","이유","관람후기","..."};
		
			String[] dummyRating = {"1","2","3","4","5"};
		
			String[] dummyPublishedDate = {"2019-01-08", "2018-02-07", "2019-01-16", "2018-01-02", "2018-11-26", "2005-06-13", "2000-06-04", "2016-08-29", "2017-06-05", "2018-07-"
					+ "23"};
		
			String[] dummyIsPublic = {"0","1"};
			
			for(int i =0; i<200; i++) {
				writer.write((i+1)+ "■");
				writer.write(rnd.nextInt(37)+1+"■");
				writer.write(dummyPublishedDate[rnd.nextInt(dummyPublishedDate.length)]+"■");
				writer.write(id1[rnd.nextInt(id1.length)]+id1[rnd.nextInt(id1.length)]+id1[rnd.nextInt(id1.length)]+id1[rnd.nextInt(id1.length)]+id1[rnd.nextInt(id1.length)]+id1[rnd.nextInt(id1.length)]+id2[rnd.nextInt(id2.length)]+id2[rnd.nextInt(id2.length)]+id2[rnd.nextInt(id2.length)]+"■");
				writer.write(dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+dummyHead[rnd.nextInt(dummyHead.length)]+"■");
				writer.write(dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+dummyBody[rnd.nextInt(dummyBody.length)]+"■");
				writer.write(dummyIsPublic[rnd.nextInt(dummyIsPublic.length)]+"■");
				writer.write(dummyRating[rnd.nextInt(dummyRating.length)]+"■");
				writer.write("\r\n");
			}
			writer.close();
			
			
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
		}
	}//
	
	
	
}




































