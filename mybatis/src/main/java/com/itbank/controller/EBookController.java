package com.itbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itbank.dao.EBookVO;
import com.itbank.service.EBookService;

@Controller
public class EBookController {
	@Autowired private EBookService ebs;
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(EBookVO vo) {
		return "EBook/add";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String add() {
		return null;
	}
}
