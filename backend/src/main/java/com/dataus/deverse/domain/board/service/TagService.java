package com.dataus.deverse.domain.board.service;

import com.dataus.deverse.domain.board.payload.TagResponse;
import com.dataus.deverse.global.pagination.Pagination;

public interface TagService {

    public TagResponse getPopularTag(Pagination page);

    public TagResponse getSearchTag(Pagination page);
    
}
