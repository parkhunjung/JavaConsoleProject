package kr.co.sist.cinema.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class PostMain {
   
   
   public static Scanner scan;
   public static String POST;
   public static String MOVIEINFO;
   public static String BANNEDWORD;
   public static String bodyFinal;
   public static UI ui;

   public static ArrayList<Post> posts;
   public static ArrayList<Movie> movies; 
   public static ArrayList<String> bannedWords;
   private static ArrayList<String> inputBannedWords;
   public static Movie movie;
   
   static {
      scan = new Scanner(System.in);
      POST = ".\\data\\post.dat";
      MOVIEINFO = ".\\data\\movieinfos.dat";
      BANNEDWORD = ".\\data\\bannedWord.dat";
      bodyFinal = "";
      ui = new UI();

      posts = new ArrayList<Post>();
      movies = new ArrayList<Movie>();
      bannedWords = new ArrayList<String>();
      inputBannedWords = new ArrayList<String>();
      movie = new Movie();
   }
   
   public static void main(String[] args) {
         
         PostMain postManager = new PostMain();
         ui.begin();
         
         postManager.load();
         
         boolean loop = true;
         
         while(loop) {
            
            ui.menu();
            
            switch(scan.nextLine()) {
            case "0":
               loop = false;
               break;
            case "1":
               postManager.writePost();
               break;
            case "2":
               
               boolean loop2 = true;
               while (loop2) {
                  ui.myPostMenu();
                  
                  switch(scan.nextLine()) {
                  case "1":
                     postManager.printMyPost();
                     break;
                  case "2":
                     postManager.editPost();
                     break;
                  case "3":
                     postManager.deletePost();
                     break;
                  case "0":
                     loop2 = false;
                     break;
                     }
               }
            }
         }
         
         postManager.save();
         
      }
   
   
   
   
   

   public void load() {
         
         try {
            
            BufferedReader reader = new BufferedReader(new FileReader(POST));
            
            
            String line = null;
            while ((line = reader.readLine()) != null) {
               
               String[] temp = line.split("■");
               
               Post post = new Post();
               
               post.setPostSerialNum(temp[0]);
               post.setMovieSerialNum(temp[1]);
               post.setPublishedDate(temp[2]);
               post.setId(temp[3]);
               post.setHead(temp[4]);
               post.setBody(temp[5]);
               post.setIsPublic(temp[6]);
               post.setRating(temp[7]);
               
               posts.add(post);
               
            }
            
            reader.close();
            
         } catch (Exception e) {
            System.out.println(e.toString() + "load");
         }
         
         try {
            BufferedReader reader = new BufferedReader(new FileReader(MOVIEINFO));
            
            String line = null;
            
            while ((line = reader.readLine()) != null) {
               
               String[] temp = line.split("■");
               
               Movie movie = new Movie();
               movie.setSerialNum(temp[0]);
               movie.setTitle(temp[2]);
               movie.setDirector(temp[5]);
               
               movies.add(movie);
            }
            
            reader.close();
            
         } catch (Exception e) {
            System.out.println(e.toString() + "movieinfo_load");
         }
         
      
         try {
            BufferedReader reader = new BufferedReader(new FileReader(BANNEDWORD));
      
            String line = null;
            
            while ((line = reader.readLine()) != null) {
               String[] temp = line.split("■");
               bannedWords.add(temp[1]);
            }
            
            reader.close();
            
         } catch (Exception e) {
            System.out.println(e.toString() + "bannedWord_load");
         }
      }
   
   public void deletePost() {
      //회원 아이디 받아 글 출력하기
      ui.title(UI.DELETE);
      String id = "woojoovove";
      //TODO 아이디 받아오기
      
      int countSearchResult = 1;
      ArrayList<Post> searchResult = new ArrayList<Post>();
   // 사용자가 수정할 글을 선택하기위해 필요한 정보만을 출력
      System.out.println("[번호] [영화제목]\t\t[작성자]\t\t[작성일자]\t\t[평점]    [제목]\t\t[내용]");
      for (Post post : posts)   {
      
         if (post.getId().equals(id)
        		 && post.getIsPublic().equals("노출")) {
         
            System.out.printf(" %d.   %s\t%s  \t %s   \t%s점 \t  제목 : %s \t\t내용 : %s\n"
                           ,countSearchResult
                           ,movies.get(Integer.parseInt(post.getMovieSerialNum())-1).getTitle()
                           ,post.getId()
                           ,post.getPublishedDate()
                           ,post.getRating()
                           ,post.getHead()
                           ,post.getBody()
            			   ); 
            
            searchResult.add(post);
            countSearchResult++;
         }
      }
     
      
      //글 노출 여부 "삭제"로 변경
      System.out.println("수정할 글 번호 입력 : ");
      
      // 임시 ArrayList에서의 index = 사용자 입력값-1
      int index = Integer.parseInt(scan.nextLine())-1;
      
      // 고유번호를 통해 원본 ArrayList에서의 수정할 post index (index = 글 고유번호 -1)
      System.out.println("index (사용자 입력값-1) : " + index);
      System.out.println("원본포스트의 제목 : " + searchResult.get(index).getHead());
      int postToEditSerialNum = Integer.parseInt(searchResult.get(index).getPostSerialNum());
      Post originalPost = posts.get(postToEditSerialNum-1);
     
      // 수정할 원본 포스트 삭제
      originalPost.setIsPublic("삭제"); 
      System.out.println(originalPost.getIsPublic());
      
      ui.pause(UI.DELETE);
   }
   
   

   public void writePost() {
      
      Post post = new Post();
      setMovie(post);
      
      if (post.getMovieSerialNum() != null) {
         setOtherThanMovie(post);
      }
      
      if (post.getBody() != null) {
         posts.add(post);
      }
      ui.pause(UI.WRITE);
   }

   public void setMovie(Post post) {
      
      Scanner scan = new Scanner(System.in);
      
      // 영화제목 검색 결과를 임시로 저장할 ArrayList searchResult와 넘버링 인덱스 index
      ArrayList<Movie> searchResult = new ArrayList<Movie>();
      int countList = 1;
      
      ui.title(ui.WRITE);
      
      
      // 사용자 입력한 검색어에서 띄어쓰기 제거
      System.out.println("영화 제목 검색 : ");
      String inputTitle = scan.nextLine().replace(" ","");
      System.out.println(inputTitle);
      System.out.println("[번호]\t[영화 제목]\t\t[감독]");
      
   
      // 영화제목이 일치하는 결과부터 상위에 출력
      for (Movie movie : movies) {
         
         String title = movie.getTitle().replace(" ", "");
         if (title.equals(inputTitle)) {
            System.out.printf("%d. %s\t\t%s\n"
                  , countList
                  , movie.getTitle()
                  , movie.getDirector());
            searchResult.add(movie);
            countList++;
         } 
      }
      
      // 영화제목이 일부 일치하는 결과 출력
      for (Movie movie : movies) {
         
         String title = movie.getTitle().replace(" ", "");
         
         if (title.indexOf(inputTitle) != -1) {
            System.out.printf("%d. %s\t\t%s\n"
                  , countList
                  , movie.getTitle()
                  , movie.getDirector());
            searchResult.add(movie);
            countList++;
         } 
      }
      
      if (countList > 1) {
      
         System.out.println("검색 완료");
         System.out.println("찾는 영화 번호 입력: ");
         
         // index는 0부터 시작, searchResult 출력은 1부터 시작하므로 사용자가 입력하는 정수는 실제 index + 1
         int index = Integer.parseInt(scan.nextLine())-1;
         post.setMovieSerialNum(searchResult.get(index).getSerialNum());
         // 영화 고유 번호 set 완료
         
         System.out.println("글 작성을 계속합니다. 엔터를 입력하세요.");
         
      } else {
         System.out.println("찾는 영화가 없습니다. 계속하려면 엔터를 입력하세요.");
         
      }
   
      scan.nextLine();
   }

   
   
   
   
   
   
   public void setOtherThanMovie(Post post) {
      
	   
	  //글 고유번호 set
	  post.setPostSerialNum(String.format("%d", posts.size() + 1)); 
	  // 작성일자 set
      Calendar today = Calendar.getInstance();
      post.setPublishedDate(String.format("%tF", today));
      
      //회원 아이디 set
      // TODO 회원 아이디 받아오기
      post.setId("woojoovove");
      
      //노출 여부 set
      post.setIsPublic("노출");
      
      // 평점 set
      
      System.out.println("평점 입력 (1~5) : "  );
      String rating = scan.nextLine();
      if (rating.equals("1")
            || rating.equals("2")
            || rating.equals("3")
            || rating.equals("4")
            || rating.equals("5")) {
         post.setRating(rating);
      }
      
      setHead(post);
      if (post.getHead() != null) {
         
         setBody(post);
         
      } 
   }

   private void setHead(Post post) {
      // 제목 set
      boolean isBanned = true;
      while(isBanned) {
         
//         Scanner scan = new Scanner(System.in);
         System.out.println("제목 입력 : ");
         String input = scan.nextLine();
         
         if(input.equals("종료")) {
            isBanned = false;
         }
         
         // isLineClean = true 일 때 (금지어가 없었을 때) 루프탈출
         if(isLineClean(input, bannedWords)) {
            post.setHead(input);
            isBanned = false;
         } else {
            
            System.out.println("금지어가 포함되어 있습니다. 다시 입력해주세요.\n 종료를 원하시면 '종료'를 입력해주세요.");
         }
         
      } // while
      System.out.println("제목 입력 완료");
   }

   
   
   
   
   private void setBody(Post post) {
      // 내용 set
      
      System.out.println("'완료'를 입력시 글이 작성됩니다.");
      System.out.println("내용 입력 : ");
      
      
      ArrayList<String> inputBody = new ArrayList<String>(); 
      
      boolean isBad = true;
      while(isBad) {
         
         
         // 다중라인 입력
         boolean isWriting = true;
         while(isWriting) {
            
            String input = scan.nextLine();
            
            // 예약어 "완료" 입력시 while(isWriting) 탈출
            if(input.equals("완료")) {
               isWriting = false;
               isBad = false;
               break;
            }
            
            inputBody.add(input);
         }
         
         if (isAllClean(inputBody)) {
            post.setBody(bodyFinal);
         }
         
      }
      
      
   }// setBody
   
   
   private boolean isLineClean(String line, ArrayList<String> bannedWords) {
      
      boolean isLineClean = true;
      
      for (String bannedWord : bannedWords) {
         
         if (line.indexOf(bannedWord) > -1) {
            
            inputBannedWords.add(bannedWord);
            isLineClean = false;
         }
      } 
      
      return isLineClean;
   }






   private boolean isAllClean(ArrayList<String> inputBody) {
      
      boolean isAllClean = true;
      
      
      for (String line : inputBody) {
         // 한 줄에 금지어가 있는지 검사
         if (isLineClean(line, bannedWords)) {
            
            // 한 줄에 금지어가 없으면 누적
            bodyFinal += line + " ";
            
         } 
         // 금지어가 한 줄에라도 있을시 while(isBad) 탈출 X 내용 입력 계속
         else {
            isAllClean = false;
            break;
         }
      }
      
      return isAllClean;
   }
   


   public void editPost() {
      //회원 아이디 받아 글 출력하기
      ui.title(UI.EDIT);
      String id = "woojoovove";
      //TODO 아이디 받아오기
      
      int countSearchResult = 1;
      ArrayList<Post> searchResult = new ArrayList<Post>();
      
      System.out.println("[번호] [영화제목]\t\t[작성자]\t\t[작성일자]\t\t[평점]    [제목]\t\t[내용]");
      // 사용자가 수정할 글을 선택하기위해 필요한 정보만을 출력
      for (Post post : posts)   {
      
         if (post.getId().equals(id)
        		 && post.getIsPublic().equals("노출")) {
         
            System.out.printf(" %d.   %s\t%s  \t %s   \t%s점 \t  제목 : %s \t\t내용 : %s\n"
                           ,countSearchResult
                           ,movies.get(Integer.parseInt(post.getMovieSerialNum())-1).getTitle()
                           ,post.getId()
                           ,post.getPublishedDate()
                           ,post.getRating()
                           ,post.getHead()
                           ,post.getBody()
            			   ); 
            
            searchResult.add(post);
            countSearchResult++;
         }
      }
      
      
      System.out.println("수정할 글 번호 입력 : ");
      
      // 임시 ArrayList에서의 index = 사용자 입력값-1
      int index = Integer.parseInt(scan.nextLine())-1;
      
      // 고유번호를 통해 원본 ArrayList에서의 수정할 post index (index = 글 고유번호 -1)
//      System.out.println("index (사용자 입력값-1) : " + index);
//      System.out.println("원본포스트의 제목 : " + searchResult.get(index).getHead());
      int postToEditSerialNum = Integer.parseInt(searchResult.get(index).getPostSerialNum());
      Post originalPost = posts.get(postToEditSerialNum-1);
     
      // 수정할 원본 포스트 삭제
      originalPost.setIsPublic("삭제"); 
      System.out.println(originalPost.getIsPublic());
      
      //기존 글에서 영화 정보를 받아 새로 글 작성
      
      Post editedpost = new Post();
      editedpost.setMovieSerialNum(originalPost.getMovieSerialNum());
      setOtherThanMovie(editedpost);
      
      posts.add(editedpost);
      
      ui.pause(UI.EDIT);
   } 
   
   
   
   
   public void printMyPost() {

      ui.title(UI.PRINT);
      //회원 아이디 받아 글 출력하기
      String id = "woojoovove";
      //TODO 아이디 받아오기
      
      int countSearchResult = 1;
      ArrayList<Post> searchResult = new ArrayList<Post>();
      
      System.out.println("[번호] [영화제목]\t\t[작성자]\t\t[작성일자]\t\t[평점]    [제목]\t\t[내용]");
      // 사용자가 수정할 글을 선택하기위해 필요한 정보만을 출력
      for (Post post : posts)   {
      
         if (post.getId().equals(id)
        		 && post.getIsPublic().equals("노출")) {
         
            System.out.printf(" %d.   %s\t%s  \t %s   \t%s점 \t  제목 : %s \t\t내용 : %s\n"
                           ,countSearchResult
                           ,movies.get(Integer.parseInt(post.getMovieSerialNum())-1).getTitle()
                           ,post.getId()
                           ,post.getPublishedDate()
                           ,post.getRating()
                           ,post.getHead()
                           ,post.getBody()
            			   ); 
            
            searchResult.add(post);
            countSearchResult++;
         }
      }
      
      ui.pause(UI.PRINT);
   }
   
   
   
   
   public void save() {
         
         try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(POST));
            
            for (Post post : posts) {
               writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s\r\n"
                                 , post.getPostSerialNum()
                                 , post.getMovieSerialNum()
                                 , post.getPublishedDate()
                                 , post.getId()
                                 , post.getHead()
                                 , post.getBody()
                                 , post.getIsPublic()
                                 , post.getRating()));
               
            }
            
            writer.close();
         } catch (Exception e) {
            System.out.println(e.toString() + "save");
         }
      } 
}