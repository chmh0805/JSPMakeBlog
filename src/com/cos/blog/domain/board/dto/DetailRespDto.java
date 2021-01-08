package com.cos.blog.domain.board.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetailRespDto {
	private int id;
	private int userId;
	private String title;
	private String content;
	private int readCount;
	private String username;
}
