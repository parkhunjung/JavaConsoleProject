package kr.co.sist.cinema.post.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BannedWordManager {
	
	private static Scanner scan;
	private static String BANNEDWORD;
	private static ArrayList<BannedWord> bannedWords;
	private static BannedWord bannedWord;
	
	static {
		
		scan = new Scanner(System.in);
		BANNEDWORD = ".\\data\\bannedword.dat";
		bannedWords = new ArrayList<BannedWord>();
		bannedWord = new BannedWord();
	}
	
	public void load() {
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(BANNEDWORD));
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("■");
				bannedWord.setContent(temp[0]);
				bannedWord.setContent(temp[1]);
				
				bannedWords.add(bannedWord);
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString() + "load");
		}
	}
	
	public void AddBannedWord() {
		
		int countBannedWord = bannedWords.size(); 
		boolean isDone = true;
		
		System.out.println("종료하시려면 \"종료\"를 입력하십시오");
		
		while (isDone) {
			
			System.out.println("금지어 입력 : ");
			String input = scan.nextLine();
			
			if (! input.equals("종료")) {
			
				bannedWord.setContent(input);
				bannedWord.setSerialNum(countBannedWord + "");
				countBannedWord ++;
				
			} else if (input.equals("종료")){
				
				isDone = false;
				break;
			}
		}
	}
	
	public void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(BANNEDWORD));
			
			for (BannedWord bannedWord : bannedWords) {
				
				writer.write(String.format("%s■%s\r\n"
											, bannedWord.getSerialNum()
											, bannedWord.getContent()));
			}
			
			writer.close();
		} catch (Exception e) {
			System.out.println(e.toString() + "putBannedWord");
		}
	}
	
	public void print() {
		
		System.out.println("번호\t금지어");
		for (BannedWord bannedWord : bannedWords) {
			
			System.out.println((String.format("%s. %s\r\n"
										, bannedWord.getSerialNum()
										, bannedWord.getContent())));
		}
	}
}
