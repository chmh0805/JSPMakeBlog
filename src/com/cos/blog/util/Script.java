package com.cos.blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	public static void responseData(HttpServletResponse resp, String jsonData) {
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.print(jsonData);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void back(HttpServletResponse resp, String msg) throws IOException {
		PrintWriter out = resp.getWriter();
		out.append("<script>");
		out.append("alert('"+msg+"');");
		out.append("history.back();");
		out.append("</script>");
		out.flush();
	}
}
