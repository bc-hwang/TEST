package com.dataus.deverse.global.vo.base;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class BbscttRcmdVO {

    private int rcmdNo;
    private int bbscttNo;
    private int rcmdUserNo;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date rcmdDt;
    
    @JsonIgnore
    private String delYn;
    @JsonIgnore
    private Date instDt;
    @JsonIgnore
    private String instId;
    
}
