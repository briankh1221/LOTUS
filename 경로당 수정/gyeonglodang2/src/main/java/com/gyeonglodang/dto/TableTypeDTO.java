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
public class TableTypeDTO {
	private int idx;
	private int resumeCustomIdx;
	private String tableTitle;
	private String tableMinRow;
	private String tableMaxRow;
	private String tableMaxCol;
	@Override
	public String toString() {
		return "TableTypeDTO [idx=" + idx + ", resumeCustomIdx=" + resumeCustomIdx + ", tableTitle=" + tableTitle
				+ ", tableMinRow=" + tableMinRow + ", tableMaxRow=" + tableMaxRow + ", tableMaxCol=" + tableMaxCol
				+ "]";
	}
	
	
}
