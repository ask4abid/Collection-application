package com.finca.ccw.web.rest;

import com.finca.ccw.domain.CollectionTracking;
import com.finca.ccw.repository.CollectionTrackingRepository;
import com.finca.ccw.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.finca.ccw.domain.CollectionTracking}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CollectionTrackingResource {
    private final Logger log = LoggerFactory.getLogger(CollectionTrackingResource.class);

    private static final String ENTITY_NAME = "collectionTracking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollectionTrackingRepository collectionTrackingRepository;

    public CollectionTrackingResource(CollectionTrackingRepository collectionTrackingRepository) {
        this.collectionTrackingRepository = collectionTrackingRepository;
    }

    /**
     * {@code POST  /collection-trackings} : Create a new collectionTracking.
     *
     * @param collectionTracking the collectionTracking to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collectionTracking, or with status {@code 400 (Bad Request)} if the collectionTracking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/collection-trackings")
    public ResponseEntity<CollectionTracking> createCollectionTracking(@Valid @RequestBody CollectionTracking collectionTracking)
        throws URISyntaxException {
        log.debug("REST request to save CollectionTracking : {}", collectionTracking);
        if (collectionTracking.getId() != null) {
            throw new BadRequestAlertException("A new collectionTracking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollectionTracking result = collectionTrackingRepository.save(collectionTracking);
        return ResponseEntity
            .created(new URI("/api/collection-trackings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /collection-trackings} : Updates an existing collectionTracking.
     *
     * @param collectionTracking the collectionTracking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionTracking,
     * or with status {@code 400 (Bad Request)} if the collectionTracking is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collectionTracking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/collection-trackings")
    public ResponseEntity<CollectionTracking> updateCollectionTracking(@Valid @RequestBody CollectionTracking collectionTracking)
        throws URISyntaxException {
        log.debug("REST request to update CollectionTracking : {}", collectionTracking);
        if (collectionTracking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CollectionTracking result = collectionTrackingRepository.save(collectionTracking);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collectionTracking.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /collection-trackings} : get all the collectionTrackings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of collectionTrackings in body.
     */
    @GetMapping("/collection-trackings")
    public List<CollectionTracking> getAllCollectionTrackings() {
        log.debug("REST request to get all CollectionTrackings");
        return collectionTrackingRepository.findAll();
    }

    /**
     * {@code GET  /collection-trackings/:id} : get the "id" collectionTracking.
     *
     * @param id the id of the collectionTracking to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collectionTracking, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/collection-trackings/{id}")
    public ResponseEntity<CollectionTracking> getCollectionTracking(@PathVariable Long id) {
        log.debug("REST request to get CollectionTracking : {}", id);
        Optional<CollectionTracking> collectionTracking = collectionTrackingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(collectionTracking);
    }

    /**
     * {@code DELETE  /collection-trackings/:id} : delete the "id" collectionTracking.
     *
     * @param id the id of the collectionTracking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/collection-trackings/{id}")
    public ResponseEntity<Void> deleteCollectionTracking(@PathVariable Long id) {
        log.debug("REST request to delete CollectionTracking : {}", id);
        collectionTrackingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
