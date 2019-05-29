package kr.co.sist.cinema.manager;

import java.io.IOException;

public class ManagerMain {
	public static void main(String[] args) {

		memberDummy mk = new memberDummy();
//		mk.dummyMember();
		
		MemberManage m = new MemberManage();
//		m.MemberManageStart();
	
		CardManage c = new CardManage();
//		c.CardManageStart();
		
		NoticeBoard n = new NoticeBoard();
		n.NoticeBoardstart();
//		n.noticeDelete();
//		n.dum();
		
		
		ContentDummy dummy = new ContentDummy();
//		dummy.noticeData();
//		dummy.questionData();
		
		PostManager ps = new PostManager();
		ps.PostManagerStart();
		
		
	}
}
