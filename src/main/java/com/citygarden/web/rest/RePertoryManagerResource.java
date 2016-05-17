package com.citygarden.web.rest;

import com.citygarden.domain.RePertoryManager;
import com.codahale.metrics.annotation.Timed;
import com.citygarden.repository.RePertoryManagerRepository;
import com.citygarden.web.rest.util.HeaderUtil;
import com.citygarden.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RePertoryManager.
 */
@RestController
@RequestMapping("/api")
public class RePertoryManagerResource {

    private final Logger log = LoggerFactory.getLogger(RePertoryManagerResource.class);

    @Inject
    private RePertoryManagerRepository rePertoryManagerRepository;

    /**
     * POST  /rePertoryManagers -> Create a new rePertoryManager.
     */
    @RequestMapping(value = "/rePertoryManagers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RePertoryManager> createRePertoryManager(@RequestBody RePertoryManager rePertoryManager) throws URISyntaxException {
        log.debug("REST request to save RePertoryManager : {}", rePertoryManager);
        if (rePertoryManager.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("rePertoryManager", "idexists", "A new rePertoryManager cannot already have an ID")).body(null);
        }
        RePertoryManager result = rePertoryManagerRepository.save(rePertoryManager);
        return ResponseEntity.created(new URI("/api/rePertoryManagers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("rePertoryManager", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rePertoryManagers -> Updates an existing rePertoryManager.
     */
    @RequestMapping(value = "/rePertoryManagers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RePertoryManager> updateRePertoryManager(@RequestBody RePertoryManager rePertoryManager) throws URISyntaxException {
        log.debug("REST request to update RePertoryManager : {}", rePertoryManager);
        if (rePertoryManager.getId() == null) {
            return createRePertoryManager(rePertoryManager);
        }
        RePertoryManager result = rePertoryManagerRepository.save(rePertoryManager);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("rePertoryManager", rePertoryManager.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rePertoryManagers -> get all the rePertoryManagers.
     */
    @RequestMapping(value = "/rePertoryManagers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RePertoryManager>> getAllRePertoryManagers(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RePertoryManagers");
        Page<RePertoryManager> page = rePertoryManagerRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rePertoryManagers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rePertoryManagers/:id -> get the "id" rePertoryManager.
     */
    @RequestMapping(value = "/rePertoryManagers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RePertoryManager> getRePertoryManager(@PathVariable String id) {
        log.debug("REST request to get RePertoryManager : {}", id);
        RePertoryManager rePertoryManager = rePertoryManagerRepository.findOne(id);
        return Optional.ofNullable(rePertoryManager)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rePertoryManagers/:id -> delete the "id" rePertoryManager.
     */
    @RequestMapping(value = "/rePertoryManagers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRePertoryManager(@PathVariable String id) {
        log.debug("REST request to delete RePertoryManager : {}", id);
        rePertoryManagerRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("rePertoryManager", id.toString())).build();
    }
}
