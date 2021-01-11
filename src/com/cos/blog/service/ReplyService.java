package com.cos.blog.service;

import com.cos.blog.domain.reply.ReplyDao;
import com.cos.blog.domain.reply.dto.SaveReqDto;
import com.cos.blog.domain.reply.dto.SaveRespDto;

public class ReplyService {
	ReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}
	
	public int 댓글쓰기(SaveReqDto dto) {
		return replyDao.save(dto);
	}
	
	public SaveRespDto 댓글찾기(int replyId) {
		return replyDao.findById(replyId);
	}
}
