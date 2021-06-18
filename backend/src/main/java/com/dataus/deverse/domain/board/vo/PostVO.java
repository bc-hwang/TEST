package com.dataus.deverse.domain.board.vo;

import java.util.List;

import com.dataus.deverse.global.exception.annotation.EnumValidator;
import com.dataus.deverse.global.vo.base.BbscttVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
    "postNo",
    "categoryName",
    "userNo",
    "userNickName",
    "postTitle",
    "postContent",
    "commentCount",
    "recommendCount",
    "tags",
    "recommend",
    "postRegistDate",
    "postUpdateDate"
})
public class PostVO extends BbscttVO {

    @JsonProperty(value="categoryName")
    @EnumValidator(message="InvalidCategory")
    private PostCategoryType categoryNm;

    @JsonProperty(value="userNickName")
    private String userNick;

    @JsonProperty(value="commentCount")
    private int replyCount;

    @JsonProperty(value="recommendCount")
    private int rcmdCount;

    @JsonProperty(value="tags")
    private List<TagVO> tagList;

    @JsonProperty(value="recommend")
    private boolean recommend;

    @Override
    public void setBbscttNo(int bbscttNo) {
        super.setBbscttNo(bbscttNo);
    }   
    
}
