package com.finca.ccw.repository;

import com.finca.ccw.domain.CollectionTracking;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CollectionTracking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollectionTrackingRepository extends JpaRepository<CollectionTracking, Long> {}
