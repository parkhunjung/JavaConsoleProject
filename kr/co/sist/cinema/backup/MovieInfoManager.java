package kr.co.sist.cinema.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieInfoManager {
	
	private final static String MOVIEINFO ;
	private static Scanner scan;
	private static UI ui; 
	private final static ArrayList<Movie> movies;
	
	static {
		MOVIEINFO = ".\\data\\movieinfo.dat";
		scan  = new Scanner(System.in);
		ui  = new UI();
		movies = new ArrayList<Movie>();
	}
	public static void main(String[] args) {
		
		
		MovieInfoManager manager = new MovieInfoManager(); 
		
		manager.load();
		
		boolean loop = true;
		
		ui.begin();
		
		while (loop) {
			
			ui.menu();
			
			switch(scan.nextLine()) {
			case "1":
				manager.printMovie();
				break;
			case "2":
				manager.addMovie();
				break;
			default:
				loop = false;
				break;
			}
		}
		manager.save();
		ui.end();
	}
	
	
	
	public void load() {
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(MOVIEINFO));

			String line = null;
			while((line = reader.readLine()) != null) {
				String[] temp = line.split("■");
				
				Movie movie = new Movie();
				
				movie.setSerialNum(temp[0]);
				movie.setIsShowing(temp[1]);
				movie.setTitle(temp[2]);
				movie.setReleaseDate(temp[3]);
				movie.setRuntime(temp[4]);
				movie.setDirector(temp[5]);
				movie.setActors(temp[6]);
				movie.setTrailer(temp[7]);
				movie.setRating(temp[8]);
				movie.setSynopsis(temp[9]);
				movie.setPost(temp[10]);
				movie.setPoster(temp[11]);
				
				System.out.println("고유번호" + temp[0]);
				System.out.println("제목" + temp[2]);
				movies.add(movie);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.toString() + "load");
		}
	}
	
	public void save() {
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIEINFO));
			
			for (Movie movie : movies) {
				
				writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s■%s■%s\r\n"
						, movie.getSerialNum()
						, movie.getIsShowing()
						, movie.getTitle()
						, movie.getReleaseDate()
						, movie.getRuntime()
						, movie.getDirector()
						, movie.getActors()
						, movie.getTrailer()
						, movie.getRating()
						, movie.getSynopsis()
						, movie.getPost()
						, movie.getPoster()));
				
			}
			
			writer.close();
			
		} catch (Exception e) {
		System.out.println(e.toString() + "save");
		}
	}
	
	public void printMovieSerialNum() {
		System.out.println(movies.size());
		for (int i=0; i<movies.size(); i++ ) {
			System.out.println(movies.get(i).getSerialNum());
		}
	}
	
	public void printMovie() {
		
		ui.title(ui.PRINT);

		// 관리자가 입력 후 입력한 내용을 확인하기 위한 내용으로 파생데이터인 평점과 무비포스트는 출력하지 않음.
		for (int i=movies.size()-1; i>movies.size()-6; i--) {
						

						System.out.println("==================================================================================================");
						System.out.println("                                		영화 고유 번호");
						System.out.printf("                                 		%s\n", movies.get(i).getSerialNum());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		제목(등급)");
						System.out.printf("                                 		%s\n", movies.get(i).getTitle());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		상태");
						System.out.printf("                                 		%s\n", movies.get(i).getIsShowing());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		개봉일");
						System.out.printf("                                 		%s\n", movies.get(i).getReleaseDate());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		상영시간");
						System.out.printf("                                 		%s\n", movies.get(i).getRuntime());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		감독");
						System.out.printf("                                 		%s\n", movies.get(i).getDirector());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		배우");
						System.out.printf("                                 		%s\n", movies.get(i).getRuntime());
						
						System.out.println("==================================================================================================");
						System.out.println("                                		예고편");
						System.out.printf("     %s\n", movies.get(i).getTrailer());
				
						System.out.println("==================================================================================================");
						System.out.println("                              		  	줄거리");
						System.out.printf("     %s\n", movies.get(i).getSynopsis());
						System.out.println("=================================================================================================");
							
						System.out.println("                                		포스터");
						System.out.printf("     %s\n", movies.get(i).getPoster());
						System.out.println("=================================================================================================");
						
		}
		
		ui.pause(ui.PRINT);
	}
	
	public void addMovie() {
		
		Movie movie = new Movie();
		
		movie.setSerialNum(movies.size() + 1 + "");

		movie.setIsShowing("1");
		
		System.out.println("영화 제목(등급) 입력 : ");
		movie.setTitle(scan.nextLine());
		
		System.out.println("개봉일 입력 (형식 : 2019-03-10) : ");
		movie.setReleaseDate(scan.nextLine());
		
		System.out.println("상영시간 입력 (형식 : 120분) : ");
		movie.setRuntime(scan.nextLine());
		
		System.out.println("감독 입력 : ");
		movie.setDirector(scan.nextLine());
		
		System.out.println("배우 입력 (형식 : 김혜수, 전지현, 강소라) : ");
		movie.setActors(scan.nextLine());
		
		System.out.println("예고편 URL 입력 : ");
		movie.setTrailer(scan.nextLine());
		
		movie.setRating("평점");
		//TODO 평점 계산해 가져오기
		
		System.out.println("줄거리 입력 : 	");
		movie.setSynopsis(scan.nextLine());
		
		movie.setPost("무비포스트 최신 3개");
		// TODO 무비포스트 가져오기
		
		System.out.println("포스터 입력 : ");
		movie.setSynopsis(scan.nextLine());
		
	}

	
	


}
