package com.dataus.deverse.domain.board.mapper;

import java.util.List;

import com.dataus.deverse.domain.board.vo.TagVO;
import com.dataus.deverse.global.pagination.Pagination;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagMapper {
    
    public List<TagVO> getPostPopularTag(@Param("page") Pagination page);

    public int getPostTagTotalCount(@Param("page") Pagination page);

    public List<TagVO> getSearchTag(@Param("page") Pagination page);
    
    public int getSearchTagTotalCount(@Param("page") Pagination page);
    
}
