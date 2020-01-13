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
	
		@Override		// �������̵� -> ������ �޼��� �������θ� ��� �����ϴ� (������ ���� ����)
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// 1. ��ö�� �߻��� ���� ��Ʈ�ѷ��� �޼��带 �����ϱ� ���� ���� 
			// (��ȯ�� : ��Ʈ�ѷ����� �������� ����, true - ��� ���� // false - ���̻� �������� �ʴ´�)
			System.out.println("preHandle");
			HttpSession session = request.getSession(false);
			// ���� ������ �����ϸ� ������ ��ȯ�ϰ�, ������ ������ ���� ������ �ʰ� null�� ��ȯ�Ѵ�.
			
			if(session != null && session.getAttribute("member") != null) {
				System.out.println("\tpreHandle : true");
				return true;	// ���� ����
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
			
			// 2. ��Ʈ�ѷ��� �޼��尡 ����� ���� view�� �ѱ�� ���� ����
			System.out.println("postHandle");
		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			// 3. view���� ������ ���� ���� ����
			
			
			System.out.println("afterCompletion");
		}
		
		

	
	
}
