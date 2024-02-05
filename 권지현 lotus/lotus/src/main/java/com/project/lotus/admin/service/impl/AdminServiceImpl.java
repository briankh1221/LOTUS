package com.project.lotus.admin.service.impl;

import com.project.lotus.admin.dto.QnaReplyForm;
import com.project.lotus.admin.repository.QnaReplyRepository;
import com.project.lotus.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminServiceImpl adminServiceImpl;

    private final QnaReplyRepository qnaReplyRepository;

    @Override
    public void addReply(QnaReplyForm qnaReplyForm, Long productIdx) {

    }

    @Override
    public void modifyReply(Long productIdx, String token) {

    }

    @Override
    public void removeReply(Long productIdx) {

    }
}
