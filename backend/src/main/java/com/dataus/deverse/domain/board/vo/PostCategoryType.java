package com.dataus.deverse.domain.board.vo;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostCategoryType {

    ALL("00", "전체"),
    TREND("01", "트렌드"),
    EXPERT("02", "전문가"),
    LIFE("03", "삶"),
    QNA("04", "질의응답"),
    
    UNKNOWN(null, null);

    private String code;
    private String name;

    @JsonCreator
    public static PostCategoryType fromValue(String value) {
        return Stream.of(PostCategoryType.values()).filter(e -> e.toString().equals(value.toUpperCase())).findFirst()
                .orElse(UNKNOWN);
    }

}
