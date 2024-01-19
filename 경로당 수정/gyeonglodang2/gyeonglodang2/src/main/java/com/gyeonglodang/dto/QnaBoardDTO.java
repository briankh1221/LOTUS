package com.gyeonglodang.dto;

import lombok.Data;

@Data
public class QnaBoardDTO {
   
   private int rownum;
   private Integer idx;
   private String gonggo_list;
   private String   field_list;
   private String   recruit_name;
   private String   recruit_email;
   private String   recruit_password;
   private String   recruit_phonenum;
   private String   qna_title;
   private String   qna_context;
   private String qna_registration_date;
   private String qna_answer_board;
   private String companyIdx;

}