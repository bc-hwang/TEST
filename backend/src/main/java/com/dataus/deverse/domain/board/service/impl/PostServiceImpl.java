package com.dataus.deverse.domain.board.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataus.deverse.domain.board.mapper.PostMapper;
import com.dataus.deverse.domain.board.payload.PostResponse;
import com.dataus.deverse.domain.board.service.PostService;
import com.dataus.deverse.domain.board.vo.PostVO;
import com.dataus.deverse.domain.board.vo.TagVO;
import com.dataus.deverse.global.exception.custom.BadRequestException;
import com.dataus.deverse.global.exception.custom.DatabaseErrorException;
import com.dataus.deverse.global.exception.custom.ResourceNotFoundException;
import com.dataus.deverse.global.exception.payload.ErrorType;
import com.dataus.deverse.global.pagination.Pagination;
import com.dataus.deverse.global.payload.ApiResponse;
import com.dataus.deverse.global.principal.UserPrincipal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    public PostResponse getPostList(Pagination page) {
        
        List<PostVO> postList = Collections.emptyList();
        int postTotalCount = postMapper.getPostTotalCount(page);

        if (postTotalCount > 0) {
            postList = postMapper.getPostList(page);
        }
        
        return PostResponse.builder()
                .category(page.getCategory().toString())
                .currentPageNo(page.getCurrentPageNo())
                .endPageNo(page.getEndPage(postTotalCount))
                .postCount(postTotalCount)
                .postList(postList)
                .build();
    }

    @Override
    public PostVO getPostDetail(int postNo, UserPrincipal userPrincipal) {
        
        PostVO postOptional = postMapper.getPostDetail(postNo, userPrincipal)
                                .orElseThrow(() -> new ResourceNotFoundException(ErrorType.ResourceNotFound));
        
        return postOptional;
    }

    @Override
    public ApiResponse addPost(PostVO post, UserPrincipal user) {

        Map<String, Object> data = new HashMap<String, Object>();
        
        int postCount = postMapper.addPost(post, user);
        int tagCount = 0;
        
        for (TagVO tag : post.getTagList()) {
            postMapper.addPostTag(
                post.getBbscttNo(), 
                user.getName(),
                tag.getTagNo()
            );

            tagCount++;
        }
        
        if((postCount == 1) && (tagCount == post.getTagList().size())) {
            data.put("postNo", post.getBbscttNo());

            return ApiResponse.builder()
                    .success(true)
                    .message("Success to add post")
                    .data(data)
                    .build();
        } 
        
        throw new DatabaseErrorException(ErrorType.DatabaseError);
        
    }
    
    @Override
    public ApiResponse deletePost(int postNo, UserPrincipal user) {
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", postNo);

        if(!postMapper.checkExistsPostUser(postNo, user)) {
            if(!postMapper.checkExistsPostUser(postNo, null)) {
                throw new BadRequestException(ErrorType.InvalidPostNo);
            }
            throw new BadRequestException(ErrorType.UnauthorizedRequest);
        }
        
        int deleteCommentRecommendCount = postMapper.deleteCommentRecommend(postNo, user);
        int deleteCommentCount = postMapper.deletePostComment(postNo, user);
        int deletePostRecommendCount = postMapper.deletePostRecommend(postNo, user);
        int deleteTagCount = postMapper.deletePostTag(postNo, user);
        int deletePostCount = postMapper.deletePost(postNo, user);
        
        if((deletePostCount == 1)) {
            data.put("tagCount", deleteTagCount);
            data.put("postRecommendCount", deletePostRecommendCount);
            data.put("commentCount", deleteCommentCount);
            data.put("commentRecommendCount", deleteCommentRecommendCount);

            return ApiResponse.builder()
                    .success(true)
                    .message("Success to delete post")
                    .data(data)
                    .build();
        }
        
        throw new DatabaseErrorException(ErrorType.DatabaseError);
        
    }

    @Override
    public ApiResponse modifyPost(PostVO post, UserPrincipal user) {

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", post.getBbscttNo());

        if(!postMapper.checkExistsPostUser(post.getBbscttNo(), user)) {
            if(!postMapper.checkExistsPostUser(post.getBbscttNo(), null)) {
                throw new BadRequestException(ErrorType.InvalidPostNo);
            }
            throw new BadRequestException(ErrorType.UnauthorizedRequest);
        }

        postMapper.deletePostTag(post.getBbscttNo(), user);
        
        for (TagVO tag : post.getTagList()) {
            postMapper.addPostTag(
                post.getBbscttNo(), 
                user.getName(),
                tag.getTagNo()
            );
        }
            
        int modifyPostCount = postMapper.modifyPost(post, user);

        if((modifyPostCount == 1) 
                && (postMapper.getPostTagCount(post.getBbscttNo()) == post.getTagList().size())) {
            return ApiResponse.builder()
                    .success(true)
                    .message("Success to modify post")
                    .data(data)
                    .build();
        }
            
        throw new DatabaseErrorException(ErrorType.DatabaseError);
        
    }

    @Override
    public ApiResponse recommendPost(int postNo, UserPrincipal user) {
        
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("postNo", postNo);

        if(!postMapper.checkExistsPostUser(postNo, null)) {
            throw new BadRequestException(ErrorType.InvalidPostNo);
        }

        if(postMapper.checkUserRecommendPost(postNo, user)) {
            int deleteRecommendPostCount = postMapper.deleteRecommendPost(postNo, user);

            if(deleteRecommendPostCount > 0) {
                data.put("process", "delete");

                return ApiResponse.builder()
                        .success(true)
                        .message("Success to delete recommend post")
                        .data(data)
                        .build();
            }
        } else {
            int addRecommendPostCount = postMapper.addRecommendPost(postNo, user);

            if(addRecommendPostCount > 0) {
                data.put("process", "add");

                return ApiResponse.builder()
                        .success(true)
                        .message("Success to add recommend post")
                        .data(data)
                        .build();
            }            
        }

        throw new DatabaseErrorException(ErrorType.DatabaseError);

    }    
    
}
