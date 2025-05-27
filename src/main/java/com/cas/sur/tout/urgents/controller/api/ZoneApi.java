package com.cas.sur.tout.urgents.controller.api;

import com.cas.sur.tout.urgents.dto.ZoneDto;
import com.cas.sur.tout.urgents.dto.zone_request.ZoneRequest;
import org.springframework.web.bind.annotation.*;


public interface ZoneApi {

    @GetMapping("/locate")
    ZoneDto findZoneByPoint(@RequestParam double lat, @RequestParam double lon);

    @PostMapping("/add")
    ZoneDto createZone(@RequestBody ZoneRequest dto);

}
