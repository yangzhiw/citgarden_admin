package com.citygarden.web.rest;

import com.citygarden.domain.ProvideMerchant;
import com.citygarden.repository.ProvideMerchantRepository;
import com.citygarden.service.ProvideDishService;
import com.citygarden.web.rest.dto.ProvideDishDTO;
import com.codahale.metrics.annotation.Timed;
import com.citygarden.domain.ProvideDish;
import com.citygarden.repository.ProvideDishRepository;
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
 * REST controller for managing ProvideDish.
 */
@RestController
@RequestMapping("/api")
public class ProvideDishResource {

    private final Logger log = LoggerFactory.getLogger(ProvideDishResource.class);

    @Inject
    private ProvideDishRepository provideDishRepository;

    @Inject
    private ProvideDishService provideDishService;

    /**
     * POST  /provideDishs -> Create a new provideDish.
     */
    @RequestMapping(value = "/provideDishs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProvideDish> createProvideDish(@RequestBody ProvideDishDTO provideDishDTO) throws URISyntaxException {
        log.debug("REST request to save ProvideDish : {}", provideDishDTO);
        if (provideDishDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("provideDish", "idexists", "A new provideDish cannot already have an ID")).body(null);
        }
        ProvideDish result = provideDishService.save(provideDishDTO);
        return ResponseEntity.created(new URI("/api/provideDishs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("provideDish", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provideDishs -> Updates an existing provideDish.
     */
    @RequestMapping(value = "/provideDishs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProvideDish> updateProvideDish(@RequestBody ProvideDishDTO provideDish) throws URISyntaxException {
        log.debug("REST request to update ProvideDish : {}", provideDish);
        ProvideDish result = provideDishService.save(provideDish);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("provideDish", provideDish.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provideDishs -> get all the provideDishs.
     */
    @RequestMapping(value = "/provideDishs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ProvideDish> getAllProvideDishs() {
        log.debug("REST request to get all ProvideDishs");
        return provideDishRepository.findAll();
            }

    /**
     * GET  /provideDishs/:id -> get the "id" provideDish.
     */
    @RequestMapping(value = "/provideDishs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProvideDish> getProvideDish(@PathVariable String id) {
        log.debug("REST request to get ProvideDish : {}", id);
        ProvideDish provideDish = provideDishRepository.findOne(id);
        return Optional.ofNullable(provideDish)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /provideDishs/:id -> delete the "id" provideDish.
     */
    @RequestMapping(value = "/provideDishs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProvideDish(@PathVariable String id) {
        log.debug("REST request to delete ProvideDish : {}", id);
        provideDishRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("provideDish", id.toString())).build();
    }
}
