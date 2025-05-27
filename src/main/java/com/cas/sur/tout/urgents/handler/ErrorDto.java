package com.cas.sur.tout.urgents.handler;

import com.cas.sur.tout.urgents.exception.ErrorCodes;
import lombok.*;
import lombok.Builder.Default;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    @Default private List<String> errors = new ArrayList<>();

}
