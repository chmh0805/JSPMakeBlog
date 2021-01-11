package com.cos.blog.domain.reply.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaveRespDto {
	private int id;
	private int userId;
	private String username;
	private String content;
	private Timestamp createDate;
}
