package com.cos.blog.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.dto.CommonRespDto;
import com.cos.blog.domain.board.dto.DetailRespDto;
import com.cos.blog.domain.board.dto.SaveReqDto;
import com.cos.blog.domain.board.dto.UpdateReqDto;
import com.cos.blog.domain.user.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
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
		BoardService boardService = new BoardService();
		RequestDispatcher dis = null;
		
		HttpSession session = request.getSession();
		if (cmd.equals("saveForm")) {
			User principal = (User) session.getAttribute("principal");
			if (principal != null) {
				dis = request.getRequestDispatcher("board/saveForm.jsp");
			} else {
				dis = request.getRequestDispatcher("user/loginForm.jsp");
			}
			
		} else if (cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			SaveReqDto dto = SaveReqDto.builder()
					.userId(userId)
					.title(title)
					.content(content)
					.build();
			
			int result = boardService.글쓰기(dto);
			if (result == 1) { // 정상
				dis = request.getRequestDispatcher("index.jsp");
			} else {
				Script.back(response, "글쓰기 실패");
			}
			
		} else if (cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page")); // 최초 0, Next:+1, Prev:-1
			List<Board> boards = boardService.목록보기(page);
			int boardCount = boardService.게시물갯수세기();
			int lastPage = (boardCount - 1) / 4;
			double currentPosition = (double)page/lastPage*100;
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("boardList", boards);
			request.setAttribute("currentPosition", currentPosition);
			dis = request.getRequestDispatcher("board/list.jsp");
			
		} else if (cmd.equals("detail")) {
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			DetailRespDto dto = boardService.상세보기(boardId);
			request.setAttribute("board", dto);
			dis = request.getRequestDispatcher("board/openDetail.jsp");
			
		} else if (cmd.equals("delete")) {
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			int result = boardService.게시물삭제(boardId);
			
			CommonRespDto<String> respDto = new CommonRespDto<>();
			respDto.setStatusCode(result);
			respDto.setData("성공");
			
			Gson gson = new Gson();
			String respData = gson.toJson(respDto);
			
			PrintWriter out = response.getWriter();
			out.print(respData);
			out.flush();
			return;

		} else if (cmd.equals("updateForm")) {
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			DetailRespDto dto = boardService.상세보기(boardId);
			request.setAttribute("board", dto);
			dis = request.getRequestDispatcher("board/updateForm.jsp");

		} else if (cmd.equals("update")) {
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			UpdateReqDto dto = UpdateReqDto.builder()
					.boardId(boardId)
					.title(title)
					.content(content)
					.build();
			
			int result = boardService.게시물수정(dto);
			if (result == 1) {
				dis = request.getRequestDispatcher("board?cmd=detail&id="+boardId);
			} else {
				Script.back(response, "글 수정 실패");
			}

		}
		
		dis.forward(request, response);
	}
}
