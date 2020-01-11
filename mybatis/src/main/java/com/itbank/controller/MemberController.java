package com.itbank.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.dao.MemberVO;
import com.itbank.service.MemberService;

@Controller
public class MemberController {
	@Autowired private MemberService ms;
	
	@RequestMapping(value="join", method=RequestMethod.GET)
	public ModelAndView join() {
		return new ModelAndView("join");
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public ModelAndView join(JoinRequest jr) {
		ModelAndView mv = new ModelAndView("redirect:/");
		int row = ms.join(jr);
		mv.addObject("row", row);
		return mv;
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView login(HttpSession session, Model model) {
		if(session.getAttribute("auto") != null) {
			model.addAttribute("autologin", session.getAttribute("auto"));
			return new ModelAndView("home");
		}else {
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(MemberVO vo, HttpSession session, Model model,
							String idsave, String auto, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		MemberVO r = ms.login(vo);
		
		if(r != null) {
			mv.setViewName("redirect:/");
			session.setAttribute("login", r);
		}else {
			model.addAttribute("no", "로그인 실패");
		}
		if(idsave != null) {
			Cookie save = new Cookie("idsave", r.getUserid());
			save.setMaxAge(60*60*24*7);
			response.addCookie(save);
		}else {
			Cookie not = new Cookie("not", idsave);
			not.setMaxAge(0);
			response.addCookie(not);
		}
		if(auto != null) {
			session.setAttribute("auto", r);
			Cookie a = new Cookie("JSESSIOID", session.getId());
			a.setMaxAge(60*60*24*7);
			response.addCookie(a);
		}
		return mv;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response, String idsave) {
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value="mypage", method=RequestMethod.GET)
	public void mypage() {}
	
	@RequestMapping(value="mypage", method=RequestMethod.POST)
	public ModelAndView mypage(MemberVO vo, HttpSession session) {
		int up = ms.update(vo);
		if(up == 1) {
			session.setAttribute("login", vo);
			return new ModelAndView("info");
		}else {
			return new ModelAndView("mypage");
		}
	}
	
	@RequestMapping("delete")
	public String delete(String userpw, HttpSession session, HttpServletResponse response) {
		MemberVO userd = (MemberVO)session.getAttribute("login");
		if(userpw.equals(userd.getUserpw())){
			int d = ms.delete(userd);
			session.removeAttribute("login");
			Cookie dd = new Cookie("idsave", null);
			dd.setMaxAge(0);
			response.addCookie(dd);
			return "home";
		}else {
			return "mypage";
		}
	}
}
