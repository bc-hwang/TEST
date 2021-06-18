package com.dataus.deverse.global.vo.base;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class ReplyVO {

    @JsonProperty(value="commentNo")
    private int replyNo;

    @JsonProperty(value="postNo")
    private int bbscttNo;
    
    @JsonProperty(value="commentRegistDate")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date replyRegistDt;
    @JsonProperty(value="userNo")
    private int wrterUserNo;
    
    @NotBlank(message="NoCommentContent")
    @JsonProperty(value="commentContent")
    private String replyCn;
    
    @JsonIgnore
    private String delYn;
    
    @JsonIgnore
    private Date instDt;
    @JsonIgnore
    private String instId;
    
    @JsonProperty(value="commentUpdateDate")
    @JsonInclude(Include.NON_NULL)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date updtDt;
    @JsonIgnore
    private String updtId;
    
}
