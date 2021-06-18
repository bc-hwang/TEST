package com.dataus.deverse.domain.board.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
    "tagRank",
    "tagNo",
    "tagName"
})
public class TagVO {
    
    @JsonProperty("tagRank")
    @JsonInclude(Include.NON_DEFAULT)
    private int tagRank;

    @JsonProperty("tagNo")
    private int tagNo;

    @JsonProperty("tagName")
    private String tagNm;

    
}
