package com.dataus.deverse.global.vo.base;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class CmmnCdVO {

    private String cdTypeId;
    private String cd;
    private String cdNm;
    private String cdEngNm;

    private String delYn;

    private Date instDt;
    private String instId;
    
    private Date updtDt;
    private String updtId;

}
