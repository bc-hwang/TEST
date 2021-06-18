package com.dataus.deverse.domain.board.controller;

import java.util.List;

import javax.validation.Valid;

import com.dataus.deverse.domain.board.payload.PostResponse;
import com.dataus.deverse.domain.board.service.PostService;
import com.dataus.deverse.domain.board.service.impl.PostServiceImpl;
import com.dataus.deverse.domain.board.vo.PostCategoryType;
import com.dataus.deverse.domain.board.vo.PostVO;
import com.dataus.deverse.global.pagination.Pagination;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postService = postServiceImpl;
    }

    /*
     * getPostList(): 게시글 목록 불러오기
     * @param category: 카테고리 명
     * @param pageNo: 페이지 번호
     * @param pageSize: 페이지당 레코드 건수
     * @param content: 내용 포함 여부
     * @param tags: 태그 번호 리스트
     */
    @GetMapping
    public ResponseEntity<PostResponse> getPostList(
        @RequestParam(value="category", required=false, defaultValue="ALL") String category,
        @RequestParam(value="page",     required=false, defaultValue="1")   int pageNo,
        @RequestParam(value="size",     required=false, defaultValue="10")  int pageSize,
        @RequestParam(value="content",  required=false, defaultValue="N")   String content,
        @RequestParam(value="keyword",  required=false, defaultValue="")    String searchKeyword,
        @RequestParam(value="tags",     required=false, defaultValue="")    List<Integer> tags
    ) {
        
        Pagination page = new Pagination();

        page.setCategory(PostCategoryType.valueOf(category.toUpperCase()));
        page.setCurrentPageNo(pageNo);
        page.setRecordsPerPage(pageSize);
        page.setIncludeContent(content.toUpperCase());
        page.setSearchKeyword(searchKeyword);
        page.setTags(tags);
        
        return ResponseEntity.ok(
            postService.getPostList(page)
        );

    }

    /*
     * getPostDetail(): 게시글 1건 불러오기
     * @param postNo: 게시글 번호
     */ 
    @GetMapping(value="{postNo}")
    public ResponseEntity<PostVO> getPostDetail(
        @CurrentUser                                 UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true) int postNo
    ) {
        return ResponseEntity.ok(
            postService.getPostDetail(postNo, userPrincipal) 
        );
    }

    /*
     * addPost(): 게시글 추가하기
     * @param userPrincipal: 사용자 정보
     * @param post: 게시글 정보
     */ 
    @PostMapping
    public ResponseEntity<?> addPost(
        @CurrentUser                       UserPrincipal userPrincipal,
        @Valid @RequestBody(required=true) PostVO post
    ) {
        return ResponseEntity.ok(
            postService.addPost(post, userPrincipal)     
        );
    }
    
    /*
     * deletePost(): 게시글 삭제하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     */ 
    @DeleteMapping(value="{postNo}")
    public ResponseEntity<?> deletePost(
        @CurrentUser UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true) int postNo
    ) {
        return ResponseEntity.ok(
            postService.deletePost(postNo, userPrincipal)
        );
    }

    /*
     * modifyPost(): 게시글 수정하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     * @param post: 게시글 정보
     */ 
    @PutMapping(value="{postNo}")
    public ResponseEntity<?> modifyPost(
        @CurrentUser                                 UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true) int postNo,
        @Valid @RequestBody(required=true)           PostVO post
    ) {
        post.setBbscttNo(postNo);

        return ResponseEntity.ok(
            postService.modifyPost(post, userPrincipal)
        );
    }

    /*
     * recommendPost(): 게시글 추천하기
     * @param userPrincipal: 사용자 정보
     * @param postNo: 게시글 번호
     */ 
    @PostMapping(value="{postNo}/recommend")
    public ResponseEntity<?> recommendPost(
        @CurrentUser                                 UserPrincipal userPrincipal,
        @PathVariable(value="postNo", required=true) int postNo
    ) {
        return ResponseEntity.ok(
            postService.recommendPost(postNo, userPrincipal)
        );
    }

}
