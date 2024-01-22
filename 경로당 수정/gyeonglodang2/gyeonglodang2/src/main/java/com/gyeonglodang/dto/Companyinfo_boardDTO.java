package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class Companyinfo_boardDTO {
   private int companyIdx;
   private String company_name;
   private String company_email;
   private String company_operation;
   private String company_note;   
}