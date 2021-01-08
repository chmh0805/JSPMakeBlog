package com.cos.blog.service;

import java.util.List;

import com.cos.blog.domain.board.Board;
import com.cos.blog.domain.board.BoardDao;
import com.cos.blog.domain.board.dto.SaveReqDto;

public class BoardService {
	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	public int 글쓰기(SaveReqDto dto) {
		return boardDao.save(dto);
	}
	
	public List<Board> 목록보기(int page) {
		return boardDao.findAll(page);
	}
	
	public Board 상세보기(int boardId) {
		return boardDao.findById(boardId);
	}
	
	public int 게시물갯수세기() {
		return boardDao.count();
	}
}
