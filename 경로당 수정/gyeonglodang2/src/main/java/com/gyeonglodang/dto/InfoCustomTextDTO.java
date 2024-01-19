package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoCustomTextDTO {
	private int text_idx;
	private String email;
	private int gonggoidx;
	private String text;
}
