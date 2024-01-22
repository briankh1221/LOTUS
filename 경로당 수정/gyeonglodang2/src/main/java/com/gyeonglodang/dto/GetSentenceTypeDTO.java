package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSentenceTypeDTO {
	private int idx;
	private int resumecustomidx;
	private String sentenceQuestion;
	private String sentenceNotice;
	private String sentencePilsu;
}
