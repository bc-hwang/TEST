package com.dataus.deverse.global.pagination;

import java.util.List;

import com.dataus.deverse.domain.board.vo.PostCategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    private int currentPageNo;  // 현재 페이지 번호
    private int recordsPerPage; // 페이지당 출력 건수

    private PostCategoryType category;
    private String includeContent;
    private String searchKeyword;
    private List<Integer> tags;

    public Pagination() {
        this.currentPageNo = 1;
        this.recordsPerPage = 10;
    }

    public int getStartPage() {
        return (currentPageNo - 1) * recordsPerPage;
    }

    public int getEndPage(int totalCount) {

        int endPage = 0;

        if(totalCount % recordsPerPage == 0) {
            endPage = totalCount / recordsPerPage;
        } else {
            endPage = (totalCount / recordsPerPage) + 1;
        }

        return endPage;

    }
    
}
