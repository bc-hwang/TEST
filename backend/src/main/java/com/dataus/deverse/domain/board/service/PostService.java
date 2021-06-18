package com.dataus.deverse.domain.board.service;

import com.dataus.deverse.domain.board.payload.PostResponse;
import com.dataus.deverse.domain.board.vo.PostVO;
import com.dataus.deverse.global.pagination.Pagination;
import com.dataus.deverse.global.payload.ApiResponse;
import com.dataus.deverse.global.principal.UserPrincipal;

public interface PostService {

    public PostResponse getPostList(Pagination page);

    public PostVO getPostDetail(int postNo, UserPrincipal userPrincipal);

    public ApiResponse addPost(PostVO post, UserPrincipal user);

    public ApiResponse modifyPost(PostVO post, UserPrincipal user);

    public ApiResponse deletePost(int postNo, UserPrincipal user);

    public ApiResponse recommendPost(int postNo, UserPrincipal user);
    
}
