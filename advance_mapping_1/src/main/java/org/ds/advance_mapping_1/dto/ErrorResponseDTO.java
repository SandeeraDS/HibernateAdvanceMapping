package org.ds.advance_mapping_1.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponseDTO {
    private int status;
    private String message;
    private long timestamp;
}
