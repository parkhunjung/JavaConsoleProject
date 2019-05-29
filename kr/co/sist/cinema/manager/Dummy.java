package kr.co.sist.cinema.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.Arrays;
import java.util.Random;

import com.test.example.io.FilePath;



public class Dummy {
	
	
	
	
	
	
	/**
	 * 더미파일 회원 정보 만들기
	 */
	public void dummyMember() {
		int count = 700;
		
		
		/**
		 * 이름
		 * 나이
		 * 성별
		 * 주소
		 * 아이디
		 * 패스워드
		 * 관람횟수
		 * 포인트
		 * 
		 */
		String[] name = new String[count]; 
		int[] age = new int[count];
		int[] gender = new int[count];
		String[] tel = new String[count];
		String[] address = new String[count];
		String[] id = new String[count];
		String[] password = new String[count];
		String[] viewNum1 = new String[count];
		String[] viewNum2 = new String[count];
		String[] point = new String[count];
		
		String[] name1 = {"김", "이", "박", "최", "정", "한", "지", "임", "조", "유", "장", "소", "우", "오", "추", "신", "양", "남"};
		String[] name2 = {"대", "은", "창", "미", "준", "수", "영", "우", "진", "인", "재", "하", "훈", "석", "동", "가", "람", "섭", "지"
						, "춘", "성", "훈", "돈", "만", "효", "장"};
		
		String[] ad1 = {"서울특별시", "부산광역시", "인천광역시", "대구광역시", "대전광역시", "광주광역시", "경기도 고양시", "경기도 용인시", "경기도 성남시",
				"경기도 부천시", "충청북도 청주시", "경기도 안산시", "경기도 화성시", "경기도 파주시", "경기도 김포시", "강원도 원주시" ,
				"강원도 강릉시", "전라남도 여수시", "전라남도 순천시", "충청북도 논산시", "충청북도 음성군", "충청남도 보령시", "충청남도 홍성군",
				"경상북도 문경시", "경상남도 창녕군", "경상북도 군위군"};
		String[] ad2 = {"서대문구", "동대문구", "남구", "북구", "중구", "동구", "남구", "은평구", "강동구", "구로구", "동작구", "성산구", "진해구", "일산서구", "일산동구"};
		String[] ad3 = {"역삼동", "중동", "대치동", "논현동", "양재동", "삼성동", "수서동" , "후곡동", "신사동", "청담동"};
		String[] telNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		String[] id1 = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
						,"A","B","C","D"};
		String[] id2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		
		String[] ps1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] ps2 = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] ps3 = {"!", "~", "@", "#", "$", "%"};
		
		String[] v1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
		
		String[] po1 = {"0"};
		
		Random rnd = new Random();
		
		//더미 회원 생성
		for (int i=0; i<count; i++) {
			
			//아이디
			id[i] = id1[rnd.nextInt(id1.length)] 
					+ id1[rnd.nextInt(id1.length)]
					+ id1[rnd.nextInt(id1.length)]
					+ id1[rnd.nextInt(id1.length)]
					+ id1[rnd.nextInt(id1.length)]
					+ id1[rnd.nextInt(id1.length)]
					+ id2[rnd.nextInt(id2.length)]
					+ id2[rnd.nextInt(id2.length)]
					+ id2[rnd.nextInt(id2.length)];
			
			//패스워드
			password[i] = ps1[rnd.nextInt(ps1.length)]
						+ ps1[rnd.nextInt(ps1.length)]
						+ ps1[rnd.nextInt(ps1.length)]
						+ ps1[rnd.nextInt(ps1.length)]
						+ ps1[rnd.nextInt(ps1.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps2[rnd.nextInt(ps2.length)]
						+ ps3[rnd.nextInt(ps3.length)];
																				
			
			
			//이름
			name[i] = name1[rnd.nextInt(name1.length)]
					+ name2[rnd.nextInt(name2.length)]
					+ name2[rnd.nextInt(name2.length)];
			
			//나이
			age[i] = rnd.nextInt(61) + 10; // 10~70세
			
			//성별
			gender[i] = rnd.nextInt(2) + 1; //1~2
			
			//연락처
			tel[i] = "010-" + telNum[rnd.nextInt(telNum.length)]
								+ telNum[rnd.nextInt(telNum.length)]
								+ telNum[rnd.nextInt(telNum.length)]
								+ telNum[rnd.nextInt(telNum.length)] + "-"		
								+ telNum[rnd.nextInt(telNum.length)]
								+ telNum[rnd.nextInt(telNum.length)]
								+ telNum[rnd.nextInt(telNum.length)];
									
									
									
									
			//주소
			address[i] =  ad1[rnd.nextInt(ad1.length)] + " "
						+ ad2[rnd.nextInt(ad2.length)] + " "
						+ ad3[rnd.nextInt(ad3.length)];
			
			
			//저번달 관람횟수
			viewNum1[i] = v1[rnd.nextInt(v1.length)];
			
			//이번달 관람횟수
			viewNum2[i] = v1[rnd.nextInt(v1.length)];
			
			//포인트
			point[i] = po1[rnd.nextInt(po1.length)];
			
			
			
			
		}
		
//		for (int i=0; i<count; i++) {
//			System.out.printf("%s\t%d\t%s\t%s\n", name[i], age[i], gender[i] == 1 ? "남자" : "여자", address[i]);
//			
//		}
		
		try {
			
			//쓰기(FileWriter & BufferedWriter)
			//읽기(FileReader & BufferedReader)
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath.MEMBER));
			
			
			
			for (int i=0; i<count; i++) {
				writer.write(String.format("%s■%s■%s■%s■%d■%s■%s■%s■%s■%s■\r\n"
						, id[i]
						, password[i], gender[i] == 1 ? "남자" : "여자"
						, name[i]
						, age[i], tel[i]
						, address[i]
						, viewNum1[i]
						, viewNum2[i]
						, point[i]));

			}
			
			
			writer.close();
			
			System.out.println("완료");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
		//회원 정보 검색
		
		
		
//		System.out.println(Arrays.toString(name));
//		System.out.println(name.length);
		
		
	}
}























