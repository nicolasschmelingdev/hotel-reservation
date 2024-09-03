package com.ntconsult.hotelreservation.util;

import com.ntconsult.hotelreservation.infrastructure.dto.Sorter;
import org.springframework.data.domain.Sort;

public final class ConvertSort {

    private ConvertSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Converts sorting parameters into a Spring Data Sort object.
     *
     * @param sortBy        the field by which to sort; defaults to "id" if null or empty
     * @param sortDirection the direction of the sort, either ASCENDING or DESCENDING
     * @return a Sort object based on the provided parameters
     */
    public static Sort toSort(String sortBy, Sorter.Direction sortDirection) {
        String sortField = (sortBy != null && !sortBy.isEmpty()) ? sortBy : "id";
        Sort.Direction direction = (sortDirection == Sorter.Direction.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return Sort.by(direction, sortField);
    }
}
