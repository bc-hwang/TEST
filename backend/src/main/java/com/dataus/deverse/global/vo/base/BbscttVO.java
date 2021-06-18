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
public class BbscttVO {

    @JsonProperty(value="postNo")
    private int bbscttNo;
    
    @JsonIgnore
    private String ctgrySeCd;

    @JsonProperty(value="userNo")
    private int wrterUserNo;
    
    @JsonProperty(value="postRegistDate")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date bbscttRegistDt;

    @NotBlank(message="NoTitle")
    @JsonProperty(value="postTitle")
    private String bbscttTitle;
    
    @NotBlank(message="NoContent")
    @JsonProperty(value="postContent")
    private String bbscttCn;
    
    @JsonIgnore
    private String delYn;    
    
    @JsonIgnore
    private Date instDt;
    @JsonIgnore
    private String instId;    
    
    @JsonProperty(value="postUpdateDate")
    @JsonInclude(Include.NON_NULL)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date updtDt;

    @JsonIgnore
    private String updtId;
    
}
