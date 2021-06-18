package com.dataus.deverse.domain.board.controller;

import javax.validation.Valid;

import com.dataus.deverse.domain.board.payload.CommentResponse;
import com.dataus.deverse.domain.board.service.CommentService;
import com.dataus.deverse.domain.board.service.impl.CommentServiceImpl;
import com.dataus.deverse.domain.board.vo.CommentVO;
import com.dataus.deverse.global.principal.CurrentUser;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/post/{postNo}/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentService = commentServiceImpl;
    }

    /*
     * getCommnetList(): 게시글 댓글 목록 불러오기
     * @param postNo: 게시글 번호
     */ 
    @GetMapping
    public CommentResponse getCommnetList(
        @CurrentUser                                 UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true) int postNo
    ) {
        return commentService.getCommentList(postNo, userPrincipal);
    }

    /*
     * addComment(): 댓글 추가하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     * @param comment: 댓글 정보
     */ 
    @PostMapping
    public ResponseEntity<?> addComment(
        @CurrentUser                                 UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true) int postNo,
        @Valid @RequestBody(required=true)           CommentVO comment
    ) {
        comment.setBbscttNo(postNo);

        return ResponseEntity.ok(
            commentService.addComment(comment, userPrincipal)
        );
    }

     /*
     * deleteComment(): 댓글 삭제하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     * @param commentNo: 댓글 번호
     */ 
    @DeleteMapping(value="{commentNo}")
    public ResponseEntity<?> deleteComment(
        @CurrentUser                                    UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true)    int postNo,
        @PathVariable(value="commentNo", required=true) int commentNo
    ) {
        return ResponseEntity.ok(
            commentService.deleteComment(postNo, commentNo, userPrincipal)
        );
    }

    /*
     * modifyComment(): 댓글 수정하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     * @param comment: 댓글 정보
     */ 
    @PutMapping(value="{commentNo}")
    public ResponseEntity<?> modifyComment(
        @CurrentUser                                    UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true)    int postNo,
        @PathVariable(value="commentNo", required=true) int commentNo,
        @Valid @RequestBody(required=true)              CommentVO comment
    ) {
        comment.setBbscttNo(postNo);
        comment.setReplyNo(commentNo);

        return ResponseEntity.ok(
            commentService.modifyComment(comment, userPrincipal)
        );
    }

    /*
     * recommendComment(): 댓글 추천하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     * @param commentNo: 댓글 번호
     */ 
    @PostMapping(value="{commentNo}/recommend")
    public ResponseEntity<?> recommendComment(
        @CurrentUser                                    UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true)    int postNo,
        @PathVariable(value="commentNo", required=true) int commentNo
    ) {
        return ResponseEntity.ok(
            commentService.recommendComment(postNo, commentNo, userPrincipal)
        );
    }
    
}
