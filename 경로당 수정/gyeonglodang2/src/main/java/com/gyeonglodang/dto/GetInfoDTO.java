package com.gyeonglodang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInfoDTO {
	private int idx;
	private int gonggoidx;
	private String infoTitle;
	private String infoNotice;
	private int infoName;
	private int infoBirth;
	private int infoAddress;
	private int infoPhone;
	private int infoArmy;
	private int infoVulnerable;
	private int infoYouth;
	private int infoDisabled;
}
