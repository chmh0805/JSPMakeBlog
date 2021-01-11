package com.cos.blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.dto.CommonRespDto;
import com.cos.blog.domain.reply.dto.DeleteReqDto;
import com.cos.blog.domain.reply.dto.SaveReqDto;
import com.cos.blog.domain.reply.dto.SaveRespDto;
import com.cos.blog.service.ReplyService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		ReplyService replyService = new ReplyService();		
		HttpSession session = request.getSession();
		
		if (cmd.equals("save")) {
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			Gson gson = new Gson();
			CommonRespDto<SaveRespDto> respDto = new CommonRespDto<>();
			SaveRespDto saveRespDto = null;
			
			SaveReqDto dto = gson.fromJson(reqData, SaveReqDto.class);
			int replyId = replyService.댓글쓰기(dto);
			
			if (replyId != -1) { // 댓글쓰기 실패 시 -1이 리턴되기 때문
				saveRespDto = replyService.댓글찾기(replyId);
				respDto.setData(saveRespDto);
				respDto.setStatusCode(1);
			} else {
				respDto.setStatusCode(-1);
			}
			
			String responseData = gson.toJson(respDto);
			Script.responseData(response, responseData);
			
		} else if (cmd.equals("delete")) {
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			Gson gson = new Gson();
			CommonRespDto<String> respDto = new CommonRespDto<>();
			
			DeleteReqDto dto = gson.fromJson(reqData, DeleteReqDto.class);
			int result = replyService.댓글삭제(dto);
			
			respDto.setStatusCode(result); // 1, -1
			
			String respData = gson.toJson(respDto);
			Script.responseData(response, respData);
			
		}
		
	}
}
