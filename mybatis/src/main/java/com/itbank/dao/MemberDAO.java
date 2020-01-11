package com.itbank.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired private SqlSessionTemplate sql;
	
	// 회원가입
	public int join(MemberVO vo) {
		return sql.insert("join", vo);
	}
	
	// 로그인
	public MemberVO login(MemberVO vo) {
		return sql.selectOne("login", vo);
	}
	
	// 회원정보수정
	public int update(MemberVO vo) {
		return sql.update("update", vo);
	}
	
	// 회원탈퇴 
	public int delete(MemberVO vo) {
		return sql.delete("delete", vo);
	}
}
