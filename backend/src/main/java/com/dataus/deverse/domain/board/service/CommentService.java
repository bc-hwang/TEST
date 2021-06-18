package com.dataus.deverse.domain.board.service;

import com.dataus.deverse.domain.board.payload.CommentResponse;
import com.dataus.deverse.domain.board.vo.CommentVO;
import com.dataus.deverse.global.payload.ApiResponse;
import com.dataus.deverse.global.principal.UserPrincipal;

public interface CommentService {

    public CommentResponse getCommentList(int postNo, UserPrincipal user);

    public ApiResponse addComment(CommentVO comment, UserPrincipal user);

    public ApiResponse deleteComment(int postNo, int commentNo, UserPrincipal user);

    public ApiResponse modifyComment(CommentVO comment, UserPrincipal user);

    public ApiResponse recommendComment(int postNo, int commentNo,UserPrincipal user);
    
}
