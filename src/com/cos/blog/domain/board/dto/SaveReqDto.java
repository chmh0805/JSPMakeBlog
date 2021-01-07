package com.cos.blog.domain.board.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaveReqDto {
	private int userId;
	private String title;
	private String content;
}
