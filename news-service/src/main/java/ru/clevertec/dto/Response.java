package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class with text for {@link ru.clevertec.exception.RestAdvice}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String message;
}
