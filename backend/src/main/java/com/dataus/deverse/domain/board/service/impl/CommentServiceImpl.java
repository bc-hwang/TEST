package com.dataus.deverse.domain.board.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataus.deverse.domain.board.mapper.CommentMapper;
import com.dataus.deverse.domain.board.payload.CommentResponse;
import com.dataus.deverse.domain.board.service.CommentService;
import com.dataus.deverse.domain.board.vo.CommentVO;
import com.dataus.deverse.global.exception.custom.BadRequestException;
import com.dataus.deverse.global.exception.custom.DatabaseErrorException;
import com.dataus.deverse.global.exception.payload.ErrorType;
import com.dataus.deverse.global.payload.ApiResponse;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    public CommentResponse getCommentList(int postNo, UserPrincipal user) {

        List<CommentVO> commentList = Collections.emptyList();
        if(!commentMapper.checkPostNo(postNo)) {
            throw new BadRequestException(ErrorType.InvalidPostNo);
        }
        
        int commentTotalCount = commentMapper.getCommentCount(postNo);

        if(commentTotalCount > 0) {            
            commentList = commentMapper.getCommentList(postNo, user);
        }

        return CommentResponse.builder()
                .postNo(postNo)
                .commentCount(commentTotalCount)
                .commentList(commentList)
                .build();
    }

    @Override
    public ApiResponse addComment(CommentVO comment, UserPrincipal user) {
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", comment.getBbscttNo());

        if(!commentMapper.checkPostNo(comment.getBbscttNo())) {
            throw new BadRequestException(ErrorType.InvalidPostNo);
        }

        int commentCount = commentMapper.addComment(comment, user);
        
        if(commentCount == 1) {
            data.put("commentNo", comment.getReplyNo());

            return ApiResponse.builder()
                    .success(true)
                    .message("Success to add comment")
                    .data(data)
                    .build();
        } 
        
        throw new DatabaseErrorException(ErrorType.DatabaseError);
        
    }

    @Override
    public ApiResponse deleteComment(int postNo, int commentNo, UserPrincipal user) {

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", postNo);
        data.put("commentNo", commentNo);

        if(!commentMapper.checkExistsCommentUser(postNo, commentNo, user)) {
            if(!commentMapper.checkExistsCommentUser(postNo, commentNo, null)) {
                throw new BadRequestException(ErrorType.InvalidCommentNo);
            }
            throw new BadRequestException(ErrorType.UnauthorizedRequest);
        }

        int deleteCommentCount = commentMapper.deleteComment(postNo, commentNo, user);
        int deleteRecommendCount = commentMapper.deleteCommentRecommend(postNo, commentNo, user);

        if(deleteCommentCount == 1) {
            data.put("recommendCount", deleteRecommendCount);
            
            return ApiResponse.builder()
                    .success(true)
                    .message("Success to delete comment")
                    .data(data)
                    .build();
        } 
        
        throw new DatabaseErrorException(ErrorType.DatabaseError);
        
    }

    @Override
    public ApiResponse modifyComment(CommentVO comment, UserPrincipal user) {
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", comment.getBbscttNo());
        data.put("commentNo", comment.getReplyNo());

        if(!commentMapper.checkExistsCommentUser(comment.getBbscttNo(), comment.getReplyNo(), user)) {
            if(!commentMapper.checkExistsCommentUser(comment.getBbscttNo(), comment.getReplyNo(), null)) {
                throw new BadRequestException(ErrorType.InvalidCommentNo);
            }
            throw new BadRequestException(ErrorType.UnauthorizedRequest);
        }

        int modifyCommentCount = commentMapper.modifyComment(comment, user);

        if(modifyCommentCount == 1) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Success to modify comment")
                    .data(data)
                    .build();
        } 
            
        throw new DatabaseErrorException(ErrorType.DatabaseError);
        
    }

    @Override
    public ApiResponse recommendComment(int postNo, int commentNo, UserPrincipal user) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", postNo);
        data.put("commentNo", commentNo);

        if(!commentMapper.checkExistsCommentUser(postNo, commentNo, null)) {
            throw new BadRequestException(ErrorType.InvalidCommentNo);
        }

        if(commentMapper.checkUserRecommendComment(commentNo, user)) {
            int deleteRecommendCommentCount = 
                commentMapper.deleteRecommendComment(commentNo, user);

            if(deleteRecommendCommentCount > 0) {
                data.put("process", "delete");

                return ApiResponse.builder()
                        .success(true)
                        .message("Success to delete recommend comment")
                        .data(data)
                        .build();
            }
        } else {
            int addRecommendCommentCount = 
                commentMapper.addRecommendComment(commentNo, user);

            if(addRecommendCommentCount > 0) {
                data.put("process", "add");

                return ApiResponse.builder()
                        .success(true)
                        .message("Success to add recommend comment")
                        .data(data)
                        .build();
            }
        }

        throw new DatabaseErrorException(ErrorType.DatabaseError);

    }    
    
}
