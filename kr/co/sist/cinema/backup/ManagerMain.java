package kr.co.sist.cinema.backup;

import java.io.IOException;

public class ManagerMain {
	public static void main(String[] args) {

		memberDummy mk = new memberDummy();
//		mk.dummyMember();
		
		MemberManage m = new MemberManage();
//		m.start();
	
		CardManage c = new CardManage();
//		c.start();
//		c.card();
		
		
		NoticeBoard n = new NoticeBoard();
//		n.start();
//		n.noticeDelete();
//		n.dum();
		
		
		ContentDummy dummy = new ContentDummy();
//		dummy.noticeData();
//		dummy.questionData();
		
		cineTest te = new cineTest();
//		te.cTest();
		
	}
}
