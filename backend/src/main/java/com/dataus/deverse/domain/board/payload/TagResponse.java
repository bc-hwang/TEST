package com.dataus.deverse.domain.board.payload;

import java.util.List;

import com.dataus.deverse.domain.board.vo.TagVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TagResponse {

    @JsonInclude(Include.NON_EMPTY)
    private String category;
    @JsonInclude(Include.NON_DEFAULT)
    private int currentPageNo;
    @JsonInclude(Include.NON_DEFAULT)
    private int endPageNo;
    private int tagCount;
    private List<TagVO> tagList;
    
}
