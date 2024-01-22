package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SentenceTypeDTO {
	private int idx;
	private int resumeCustomIdx;
	private String sentenceQuestion;
	private String sentenceNotice;
	private String sentencePilsu;
}
