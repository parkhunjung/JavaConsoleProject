package kr.co.sist.cinema.reservation;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import kr.co.sist.cinema.initialscreen.FileRead;

public class CinemaScheduleDataInsert extends FileRead {

	public void timeInsert() {
		
		Random random = new Random();
		
		initialReader("movieinfo.dat");
		
		
				
		try(
			BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "지역.txt"));	
			)
		{
			for(int i =0; i < list.size(); i++) {
				String[] division = list.get(i).split("\\■");
				if(division[1].equals("1")) {
					writer.write(division[0] + "■");
					writer.write(random.nextInt(6)+7 + "■");
					writer.write(random.nextInt(60) + "■");
					writer.write("1" + "■\r\n");
					writer.write(division[0] + "■");
					writer.write(random.nextInt(6)+7 + "■");
					writer.write(random.nextInt(60) + "■");
					writer.write("2" + "■\r\n");
					writer.write(division[0] + "■");
					writer.write(random.nextInt(6)+7 + "■");
					writer.write(random.nextInt(60) + "■");
					writer.write("3" + "■\r\n");
					writer.write(division[0] + "■");
					writer.write(random.nextInt(6)+7 + "■");
					writer.write(random.nextInt(60) + "■");
					writer.write("4" + "■\r\n");
					writer.write(division[0] + "■");
					writer.write(random.nextInt(6)+7 + "■");
					writer.write(random.nextInt(60) + "■");
					writer.write("5" + "■\r\n");
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("경로 없음");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
				
				
		
		System.out.println("                                   상영시간 업데이트 완료");
		
	}

}
