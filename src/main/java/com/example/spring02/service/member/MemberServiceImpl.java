package com.example.spring02.service.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring02.model.member.dao.MemberDAO;
import com.example.spring02.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService	{
	
	@Inject
	MemberDAO memberDao;
	
	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) {
		String name = memberDao.loginCheck(dto);
		if (name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
		}
		return name;
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate();	// 세션 초기화
	}

}
