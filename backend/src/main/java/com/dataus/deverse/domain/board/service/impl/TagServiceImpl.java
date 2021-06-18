package com.dataus.deverse.domain.board.service.impl;

import java.util.Collections;
import java.util.List;

import com.dataus.deverse.domain.board.mapper.TagMapper;
import com.dataus.deverse.domain.board.payload.TagResponse;
import com.dataus.deverse.domain.board.service.TagService;
import com.dataus.deverse.domain.board.vo.TagVO;
import com.dataus.deverse.global.pagination.Pagination;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor=Exception.class)
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    @Override
    public TagResponse getPopularTag(Pagination page) {
        
        List<TagVO> tagList = Collections.emptyList();
        int tagTotalCount = tagMapper.getPostTagTotalCount(page);

        if (tagTotalCount > 0) {
            tagList = tagMapper.getPostPopularTag(page);
        }        
        
        return TagResponse.builder()
                .category(page.getCategory().toString())
                .currentPageNo(page.getCurrentPageNo())
                .endPageNo(page.getEndPage(tagTotalCount))
                .tagCount(tagTotalCount)
                .tagList(tagList)
                .build();

    }

    @Override
    public TagResponse getSearchTag(Pagination page) {
        
        List<TagVO> tagList = Collections.emptyList();
        int tagTotalCount = tagMapper.getSearchTagTotalCount(page);

        if (tagTotalCount > 0) {
            tagList = tagMapper.getSearchTag(page);
        }
        
        return TagResponse.builder()
                .currentPageNo(page.getCurrentPageNo())
                .endPageNo(page.getEndPage(tagTotalCount))
                .tagCount(tagTotalCount)
                .tagList(tagList)
                .build();
    }    
    
}
