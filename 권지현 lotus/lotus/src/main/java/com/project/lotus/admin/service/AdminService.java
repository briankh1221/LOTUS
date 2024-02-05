package com.project.lotus.admin.service;

import com.project.lotus.admin.dto.QnaReplyForm;

public interface AdminService {

    // 관리자 Q&A 답변 등록 *24.01.30 jihyun
    public void addReply(QnaReplyForm qnaReplyForm, Long productIdx);

    // 관리자 Q&A 답변 수정 *24.01.30 jihyun
    public void modifyReply(Long productIdx, String token);

    // 관리자 Q&A 답변 삭제 *24.01.30 jihyun
    public void removeReply(Long productIdx);
}
