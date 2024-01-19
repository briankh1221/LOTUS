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
public class InfoDTO {
	private int idx;
	private int gonggoIdx;
	private String info;
	private String infoTitle;
	private String infoNotice;
	private String infoName;
	private String infoBirth;
	private String infoAddress;
	private String infoPhone;
	private String infoArmy;
	private String infoVulnerable;
	private String infoYouth;
	private String infoDisabled;
}
