package com.ntconsult.hotelreservation.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    @JsonProperty("pageNum")
    private Long pageNum;

    @JsonProperty("pageSize")
    private Long pageSize;

    @JsonProperty("totalElements")
    private Long totalElements;

    private List<T> list;

    private Integer totalPages;

    public static <T> PageResult<T> page(List<T> list, long total, long current, long size, Integer totalPages) {
        PageResult<T> result = new PageResult<>();
        result.setTotalElements(total);
        result.setPageNum(current);
        result.setPageSize(size);
        result.setList(list);
        result.setTotalPages(totalPages);
        return result;
    }
}
