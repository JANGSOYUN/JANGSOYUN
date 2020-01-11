package com.itbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.controller.JoinRequest;
import com.itbank.dao.MemberDAO;
import com.itbank.dao.MemberVO;

@Service
public class MemberService {
	@Autowired private MemberDAO dao;
	
	public int join(JoinRequest jr) {
		if(jr.passwordSame() == false) {
			return -1;
		}
		
		MemberVO vo = new MemberVO();
		vo.setUserid(jr.getUserid());
		vo.setUserpw(jr.getUserpw());
		vo.setUsername(jr.getUsername());
		
		return dao.join(vo);
	}
	
	public MemberVO login(MemberVO vo) {
		return dao.login(vo);
	}
	
	public int update(MemberVO vo) {
		return dao.update(vo);
	}
	
	public int delete(MemberVO vo) {
		return dao.delete(vo);
	}
}
