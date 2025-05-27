package com.cas.sur.tout.urgents.repository;

import com.cas.sur.tout.urgents.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ZoneRepo extends JpaRepository<Zone, Integer> {

    @Query(value = """
    SELECT * FROM zones\s
    WHERE ST_Intersects(ST_GeomFromWKB(geometry), ST_PointFromText(:point))\s
    LIMIT 1
   \s""", nativeQuery = true)
    Zone findZoneContainingPoint(@Param("point") String pointWKT);

}
