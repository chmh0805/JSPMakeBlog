package com.cos.blog.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForbiddenUrlConfig implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		StringBuffer url = request.getRequestURL();
		String path = url.substring(url.length()-4);
		
		if (path.equals(".jsp")) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		} else {
			chain.doFilter(request, response);
		}
	}
}
