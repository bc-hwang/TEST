package com.dataus.deverse.domain.board.mapper;

import java.util.List;

import com.dataus.deverse.domain.board.vo.CommentVO;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    public List<CommentVO> getCommentList(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

    public int getCommentCount(@Param("postNo") int postNo);

    public boolean checkPostNo(@Param("postNo") int postNo);

    public int addComment(@Param("comment") CommentVO comment, @Param("user") UserPrincipal user);

    public int deleteComment(@Param("postNo") int postNo, @Param("commentNo") int commentNo, UserPrincipal user);
    
    public int deleteCommentRecommend(@Param("postNo") int postNo, @Param("commentNo") int commentNo, UserPrincipal user);

    public int modifyComment(@Param("comment") CommentVO comment, @Param("user") UserPrincipal user);

    public boolean checkUserRecommendComment(@Param("commentNo") int commentNo, UserPrincipal user);

    public int addRecommendComment(@Param("commentNo") int commentNo, UserPrincipal user);

    public int deleteRecommendComment(@Param("commentNo") int commentNo, UserPrincipal user);

    public boolean checkExistsCommentUser(@Param("postNo") int postNo, @Param("commentNo") int commentNo, UserPrincipal user);
    
}
