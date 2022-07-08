package eda.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class LoginFilter extends GenericFilterBean {
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			if (SecurityContextHolder.getContext().getAuthentication() != null
				  && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				  && (((HttpServletRequest)request).getRequestURI().equals("/eda/login") || ((HttpServletRequest)request).getRequestURI().equals("/eda/"))) {
				System.out.println("user is authenticated but trying to access login page, redirecting to /");
				((HttpServletResponse)response).sendRedirect("/eda/dashboard");
			}
			chain.doFilter(request, response);
	}
}
