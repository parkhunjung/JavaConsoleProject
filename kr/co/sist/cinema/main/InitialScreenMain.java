package kr.co.sist.cinema.main;

import kr.co.sist.cinema.initialscreen.InitialScreen;
import kr.co.sist.cinema.manager.MemberManage;

public class InitialScreenMain {

	public static void main(String[] args) {
		
		MemberManage memberManage = new MemberManage();
		memberManage.numberViews();
		
		InitialScreen start = new InitialScreen();
		start.initial();
		
	}
}
