package com.cas.sur.tout.urgents.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIncidentStatusDto {

    private Long id;
    private String status;

}
