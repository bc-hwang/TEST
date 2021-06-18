package com.dataus.deverse.domain.board.payload;

import java.util.List;

import com.dataus.deverse.domain.board.vo.CommentVO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentResponse {

    private int postNo;
    private int commentCount;
    private List<CommentVO> commentList;
    
}
