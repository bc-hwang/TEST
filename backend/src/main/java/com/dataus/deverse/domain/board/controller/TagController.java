package com.dataus.deverse.domain.board.controller;

import com.dataus.deverse.domain.board.service.TagService;
import com.dataus.deverse.domain.board.service.impl.TagServiceImpl;
import com.dataus.deverse.domain.board.vo.PostCategoryType;
import com.dataus.deverse.global.pagination.Pagination;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagService = tagServiceImpl;
    }

    @GetMapping(value="popular")
    public ResponseEntity<?> getPopularTag(
        @RequestParam(value="category", required=false, defaultValue="ALL") String category,
        @RequestParam(value="page",     required=false, defaultValue="1")   int pageNo,
        @RequestParam(value="size",     required=false, defaultValue="10")  int pageSize,
        @RequestParam(value="keyword",  required=false, defaultValue="")    String searchKeyword
    ) {

        Pagination page = new Pagination();

        page.setCategory(PostCategoryType.valueOf(category.toUpperCase()));
        page.setCurrentPageNo(pageNo);
        page.setRecordsPerPage(pageSize);
        page.setSearchKeyword(
            (searchKeyword.indexOf('#') == 0) ? searchKeyword.substring(1) : searchKeyword
        );

        return ResponseEntity.ok(
            tagService.getPopularTag(page)
        );
        
    }

    @GetMapping(value="search")
    public ResponseEntity<?> getSearchTag(
        @RequestParam(value="page",     required=false, defaultValue="1")   int pageNo,
        @RequestParam(value="size",     required=false, defaultValue="10")  int pageSize,
        @RequestParam(value="keyword",  required=false, defaultValue="")    String searchKeyword
    ) {

        Pagination page = new Pagination();

        page.setCurrentPageNo(pageNo);
        page.setRecordsPerPage(pageSize);
        page.setSearchKeyword(
            (searchKeyword.indexOf('#') == 0) ? searchKeyword.substring(1) : searchKeyword
        );

        return ResponseEntity.ok(
            tagService.getSearchTag(page)
        );

    }
    
}
