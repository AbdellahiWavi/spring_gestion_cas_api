package com.cas.sur.tout.urgents.service;

import com.cas.sur.tout.urgents.dto.ZoneDto;


public interface ZoneService {

    ZoneDto createZone(String nom, double[][] coordinates);

    ZoneDto findZoneByCoordinates(double lat, double lon);

}
