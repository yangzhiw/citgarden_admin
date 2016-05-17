package com.citygarden.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.citygarden.domain.ProvideMerchant;
import com.citygarden.repository.ProvideMerchantRepository;
import com.citygarden.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing ProvideMerchant.
 */
@RestController
@RequestMapping("/api")
public class ProvideMerchantResource {

    private final Logger log = LoggerFactory.getLogger(ProvideMerchantResource.class);
        
    @Inject
    private ProvideMerchantRepository provideMerchantRepository;
    
    /**
     * POST  /provideMerchants -> Create a new provideMerchant.
     */
    @RequestMapping(value = "/provideMerchants",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProvideMerchant> createProvideMerchant(@RequestBody ProvideMerchant provideMerchant) throws URISyntaxException {
        log.debug("REST request to save ProvideMerchant : {}", provideMerchant);
        if (provideMerchant.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("provideMerchant", "idexists", "A new provideMerchant cannot already have an ID")).body(null);
        }
        ProvideMerchant result = provideMerchantRepository.save(provideMerchant);
        return ResponseEntity.created(new URI("/api/provideMerchants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("provideMerchant", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provideMerchants -> Updates an existing provideMerchant.
     */
    @RequestMapping(value = "/provideMerchants",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProvideMerchant> updateProvideMerchant(@RequestBody ProvideMerchant provideMerchant) throws URISyntaxException {
        log.debug("REST request to update ProvideMerchant : {}", provideMerchant);
        if (provideMerchant.getId() == null) {
            return createProvideMerchant(provideMerchant);
        }
        ProvideMerchant result = provideMerchantRepository.save(provideMerchant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("provideMerchant", provideMerchant.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provideMerchants -> get all the provideMerchants.
     */
    @RequestMapping(value = "/provideMerchants",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ProvideMerchant> getAllProvideMerchants() {
        log.debug("REST request to get all ProvideMerchants");
        return provideMerchantRepository.findAll();
            }

    /**
     * GET  /provideMerchants/:id -> get the "id" provideMerchant.
     */
    @RequestMapping(value = "/provideMerchants/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProvideMerchant> getProvideMerchant(@PathVariable String id) {
        log.debug("REST request to get ProvideMerchant : {}", id);
        ProvideMerchant provideMerchant = provideMerchantRepository.findOne(id);
        return Optional.ofNullable(provideMerchant)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /provideMerchants/:id -> delete the "id" provideMerchant.
     */
    @RequestMapping(value = "/provideMerchants/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProvideMerchant(@PathVariable String id) {
        log.debug("REST request to delete ProvideMerchant : {}", id);
        provideMerchantRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("provideMerchant", id.toString())).build();
    }
}
