package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTableTypeDTO {
	private int idx;
	private int resumecustomidx;
	private String tabletitle;
	private String tableMinRow;
	private String tableMaxRow;
	private String tableMaxCol;
}
