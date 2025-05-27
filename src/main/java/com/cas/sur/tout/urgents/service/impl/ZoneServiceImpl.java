package com.cas.sur.tout.urgents.service.impl;

import com.cas.sur.tout.urgents.dto.ZoneDto;
import com.cas.sur.tout.urgents.model.Zone;
import com.cas.sur.tout.urgents.repository.ZoneRepo;
import com.cas.sur.tout.urgents.service.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKBWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Slf4j
@Transactional
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepo zoneRepository;

    @Autowired
    public ZoneServiceImpl(ZoneRepo zoneRepo) {
        this.zoneRepository = zoneRepo;
    }

    public ZoneDto createZone(String nom, double[][] coordinates) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate[] coords = new Coordinate[coordinates.length + 1];

        for (int i = 0; i < coordinates.length; i++) {
            coords[i] = new Coordinate(coordinates[i][0], coordinates[i][1]);
        }

        coords[coordinates.length] = coords[0]; // fermeture

        LinearRing shell = geometryFactory.createLinearRing(coords);
        Polygon polygon = geometryFactory.createPolygon(shell);

//        WKBWriter writer = new WKBWriter();
//        byte[] geometryBytes = writer.write(polygon);

        Zone zone = new Zone();
        zone.setCity(nom);
        zone.setGeometry(polygon);

        return ZoneDto.fromEntity(
                zoneRepository.save(zone)
        );
    }

    public ZoneDto findZoneByCoordinates(double lat, double lon) {
        String pointWKT = String.format(Locale.US, "POINT(%.7f %.7f)", lon, lat);
        Zone zone = zoneRepository.findZoneContainingPoint(pointWKT);

        log.warn("Recherche de zone pour les coordonnées : {}, {}", lat, lon);

        if (zone == null) {
            throw new RuntimeException("Aucune zone trouvée pour ce point");
        }

        return ZoneDto.fromEntity(zone);
    }

}
