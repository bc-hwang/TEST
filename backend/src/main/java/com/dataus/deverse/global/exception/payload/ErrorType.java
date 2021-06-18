package com.dataus.deverse.global.exception.payload;

import java.util.stream.Stream;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {

    ResourceNotFound("E00001", HttpStatus.NOT_FOUND,"해당 자원을 찾을 수 없음"),
    UnauthorizedRequest("E00002", HttpStatus.BAD_REQUEST, "인증되지 않은 요청"),
    UnauthorizedRedirection("E00003", HttpStatus.BAD_REQUEST, "인증되지 않은 리다이렉션"),
    FailToAuthenticate("E00004", HttpStatus.UNAUTHORIZED, "인증 실패"),

    InvalidPostNo("E10001", HttpStatus.BAD_REQUEST, "유효하지 않은 게시글 번호"),
    NoTitle("E10002", HttpStatus.BAD_REQUEST, "글 제목 없음"),
    NoContent("E10003", HttpStatus.BAD_REQUEST, "글 내용 없음"),
    InvalidCategory("E10004", HttpStatus.BAD_REQUEST, "유효하지 않은 카테고리"),

    InvalidCommentNo("E20001", HttpStatus.BAD_REQUEST, "유효하지 않은 댓글 번호"),
    NoCommentContent("E20002", HttpStatus.BAD_REQUEST, "댓글 내용 없음"),

    DatabaseError("E30001", HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 오류"),
    
    UnknownError("E99999", HttpStatus.BAD_GATEWAY, "알 수 없는 오류");

    private String code;
    private HttpStatus status;
    private String message;

    public static ErrorType fromValue(String value) {
        return Stream.of(ErrorType.values()).filter(e -> e.toString().equals(value)).findFirst()
                .orElse(UnknownError);
    }

}
