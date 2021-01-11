package com.cos.blog.domain.reply.dto;

import lombok.Data;

@Data
public class DeleteReqDto {
	private int userId;
	private int replyId;
}
