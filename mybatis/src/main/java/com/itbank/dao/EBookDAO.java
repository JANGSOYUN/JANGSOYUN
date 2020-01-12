package com.itbank.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EBookDAO {
	@Autowired private SqlSessionTemplate sql;
	
	public int add(EBookVO vo) {
		return sql.insert("add", vo);
	}
}
