package ru.clevertec.dto;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @param content  list of entities (<T>ReadDto)
 * @param metadata inner class
 * @param <T>      parameterized type of response page
 */
public record PageResponse<T>(List<T> content,
                              ru.clevertec.dto.PageResponse.Metadata metadata) {

    public static <T> PageResponse<T> of(Page<T> page) {
        var metadata = new Metadata(page.getNumber(), page.getSize(), page.getTotalElements());
        return new PageResponse<>(page.getContent(), metadata);
    }

    /**
     * Inner class for PageResponse<T>>
     *
     * @param page          number of page
     * @param size          total number of elements per page
     * @param totalElements total number of elements in the response
     */
    public record Metadata(int page,
                           int size,
                           long totalElements) {

    }
}
