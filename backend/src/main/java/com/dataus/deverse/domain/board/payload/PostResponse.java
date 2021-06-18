package com.dataus.deverse.domain.board.payload;

import java.util.List;

import com.dataus.deverse.domain.board.vo.PostVO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostResponse {

    private String category;
    private int currentPageNo;
    private int endPageNo;
    private int postCount;
    private List<PostVO> postList;
    
}
