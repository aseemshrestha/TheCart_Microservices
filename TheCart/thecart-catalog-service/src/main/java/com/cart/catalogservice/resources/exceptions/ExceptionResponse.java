package com.cart.catalogservice.resources.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
