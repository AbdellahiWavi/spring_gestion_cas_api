package com.cas.sur.tout.urgents.controller;

import com.cas.sur.tout.urgents.controller.api.ZoneApi;
import com.cas.sur.tout.urgents.dto.ZoneDto;
import com.cas.sur.tout.urgents.dto.zone_request.ZoneRequest;
import com.cas.sur.tout.urgents.service.impl.ZoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cas.sur.tout.urgents.utils.Constants.ZONE_ENDPOINT;

@RestController
@RequestMapping(ZONE_ENDPOINT)
public class ZoneController implements ZoneApi {

    private final ZoneServiceImpl zoneService;

    @Autowired
    public ZoneController(ZoneServiceImpl zoneService) {
        this.zoneService = zoneService;
    }


    @Override
    public ZoneDto findZoneByPoint(double lat, double lon) {
        return zoneService.findZoneByCoordinates(lat, lon);
    }

    @Override
    public ZoneDto createZone(ZoneRequest dto) {
        return zoneService.createZone(dto.getNom(), dto.getCoordinates());
    }

}
