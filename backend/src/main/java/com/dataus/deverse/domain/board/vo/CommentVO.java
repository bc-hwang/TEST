package com.dataus.deverse.domain.board.vo;

import com.dataus.deverse.global.vo.base.ReplyVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder({
    "commentNo",
    "postNo",
    "userNo",
    "userNickName",
    "commentContent",
    "recommendCount",
    "recommend",
    "commentRegistDate",
    "commentUpdateDate"
})
public class CommentVO extends ReplyVO {

    @JsonProperty(value="userNickName")
    public String userNick;

    @JsonProperty(value="recommendCount")
    public int replyRcmdCount;

    @JsonProperty(value="recommend")
    private boolean recommend;

    @Override
    public void setBbscttNo(int bbscttNo) {
        super.setBbscttNo(bbscttNo);
    }

    @Override
    public void setReplyNo(int replyNo) {
        super.setReplyNo(replyNo);
    }       
    
}
