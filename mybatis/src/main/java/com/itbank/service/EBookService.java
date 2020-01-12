package com.itbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.dao.EBookDAO;
import com.itbank.dao.EBookVO;

@Service
public class EBookService {
	@Autowired private EBookDAO dao;
	
	public int add(EBookVO vo) {
		return dao.add(vo);
	}
}
