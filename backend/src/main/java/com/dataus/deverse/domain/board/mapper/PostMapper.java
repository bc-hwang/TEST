package com.dataus.deverse.domain.board.mapper;

import java.util.List;
import java.util.Optional;

import com.dataus.deverse.domain.board.vo.PostVO;
import com.dataus.deverse.global.pagination.Pagination;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostMapper {
    
    public List<PostVO> getPostList(@Param("page") Pagination page);

    public int getPostTotalCount(@Param("page") Pagination page);

    public Optional<PostVO> getPostDetail(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

    public int addPost(@Param("post") PostVO post, @Param("user") UserPrincipal user);

    public int addPostTag(@Param("postNo") int postNo, @Param("userId") String userId, @Param("tagNo") int tagNo);

    public int getPostTagCount(@Param("postNo") int postNo);

    public int deletePost(@Param("postNo") int postNo, @Param("user") UserPrincipal user);
    
    public int deletePostTag(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

    public int deletePostRecommend(@Param("postNo") int postNo, @Param("user") UserPrincipal user);
    
    public int deletePostComment(@Param("postNo") int postNo, @Param("user") UserPrincipal user);
    
    public int deleteCommentRecommend(@Param("postNo") int postNo, @Param("user") UserPrincipal user);
    
    public int modifyPost(@Param("post") PostVO post, @Param("user") UserPrincipal user);

    public boolean checkUserRecommendPost(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

    public int addRecommendPost(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

    public int deleteRecommendPost(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

    public boolean checkExistsPostUser(@Param("postNo") int postNo, @Param("user") UserPrincipal user);

}
