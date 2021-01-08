package com.cos.blog.domain.board.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateReqDto {
	private int boardId;
	private String title;
	private String content;
}
