package com.ntconsult.hotelreservation.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PageFilters {

    private Integer pagesSize = 10;

    private Integer pageNumber = 1;

    private String sortBy = "id";

    private Sorter.Direction sortDirection = Sorter.Direction.ASC;

    public PageFilters() {
        Optional.of(pagesSize).filter(pS -> pS > 0).ifPresent(this::setPagesSize);
        Optional.ofNullable(pageNumber).filter(pN -> pN > 0).ifPresent(this::setPageNumber);
        Optional.ofNullable(sortDirection).ifPresent(this::setSortDirection);
        Optional.ofNullable(sortBy).ifPresent(this::setSortBy);
    }

}
