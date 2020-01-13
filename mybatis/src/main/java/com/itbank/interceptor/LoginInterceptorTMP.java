package com.itbank.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.itbank.dao.MemberVO;

public class LoginInterceptorTMP  extends HandlerInterceptorAdapter {
	// (Shift + Alt + S) + V : Override Methods
	
		@Override		// 오버라이딩 -> 지정된 메서드 형식으로만 사용 가능하다 (내용은 변경 가능)
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// 1. 요철이 발생한 이후 컨트롤러의 메서드를 수행하기 전에 개입 
			// (반환값 : 컨트롤러에게 연결할지 여부, true - 계속 진행 // false - 더이상 진행하지 않는다)
			System.out.println("preHandle");
			HttpSession session = request.getSession(false);
			// 만약 세션이 존재하면 세션을 반환하고, 세션이 없으면 새로 만들지 않고 null을 반환한다.
			
			if(session != null && session.getAttribute("member") != null) {
				System.out.println("\tpreHandle : true");
				return true;	// 진행 시켜
			}
//			String next = request.getRequestURI().substring(request.getContextPath().length());
			String next = request.getServletPath();
			response.sendRedirect(request.getContextPath() + "/member?next=" + next);
			System.out.println("\tpreHandle : false");
			return false;
		}
		
		
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			String auto = request.getParameter("auto");
			String save = request.getParameter("idsave");
			HttpSession session = request.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			if(auto!=null) {
				session.setMaxInactiveInterval(600);
				Cookie jsession = new Cookie("JSESSIONID", session.getId());
				jsession.setMaxAge(600);
				response.addCookie(jsession);
			}
			if(save!=null) {
				Cookie saveCookie = new Cookie("idsave", member.getUserid());
				saveCookie.setMaxAge(600);
				response.addCookie(saveCookie);
			}
			
			// 2. 컨트롤러의 메서드가 수행된 이후 view로 넘기기 전에 개입
			System.out.println("postHandle");
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			// 3. view로의 전달이 끝난 이후 개입
			
			
			System.out.println("afterCompletion");
		}
		
		

	
	
}
