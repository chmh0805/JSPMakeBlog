package com.cos.blog.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateReqDto {
	private String password;
	private String email;
	private String address;
}
