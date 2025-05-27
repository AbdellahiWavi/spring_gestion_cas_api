package com.cas.sur.tout.urgents.dto.zone_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneRequest {
    private String nom;
    private double[][] coordinates;
}
