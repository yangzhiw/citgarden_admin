package com.citygarden.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.citygarden.domain.UserLevalDefinition;
import com.citygarden.repository.UserLevalDefinitionRepository;
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
 * REST controller for managing UserLevalDefinition.
 */
@RestController
@RequestMapping("/api")
public class UserLevalDefinitionResource {

    private final Logger log = LoggerFactory.getLogger(UserLevalDefinitionResource.class);
        
    @Inject
    private UserLevalDefinitionRepository userLevalDefinitionRepository;
    
    /**
     * POST  /userLevalDefinitions -> Create a new userLevalDefinition.
     */
    @RequestMapping(value = "/userLevalDefinitions",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserLevalDefinition> createUserLevalDefinition(@RequestBody UserLevalDefinition userLevalDefinition) throws URISyntaxException {
        log.debug("REST request to save UserLevalDefinition : {}", userLevalDefinition);
        if (userLevalDefinition.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("userLevalDefinition", "idexists", "A new userLevalDefinition cannot already have an ID")).body(null);
        }
        UserLevalDefinition result = userLevalDefinitionRepository.save(userLevalDefinition);
        return ResponseEntity.created(new URI("/api/userLevalDefinitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("userLevalDefinition", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /userLevalDefinitions -> Updates an existing userLevalDefinition.
     */
    @RequestMapping(value = "/userLevalDefinitions",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserLevalDefinition> updateUserLevalDefinition(@RequestBody UserLevalDefinition userLevalDefinition) throws URISyntaxException {
        log.debug("REST request to update UserLevalDefinition : {}", userLevalDefinition);
        if (userLevalDefinition.getId() == null) {
            return createUserLevalDefinition(userLevalDefinition);
        }
        UserLevalDefinition result = userLevalDefinitionRepository.save(userLevalDefinition);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("userLevalDefinition", userLevalDefinition.getId().toString()))
            .body(result);
    }

    /**
     * GET  /userLevalDefinitions -> get all the userLevalDefinitions.
     */
    @RequestMapping(value = "/userLevalDefinitions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<UserLevalDefinition> getAllUserLevalDefinitions() {
        log.debug("REST request to get all UserLevalDefinitions");
        return userLevalDefinitionRepository.findAll();
            }

    /**
     * GET  /userLevalDefinitions/:id -> get the "id" userLevalDefinition.
     */
    @RequestMapping(value = "/userLevalDefinitions/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<UserLevalDefinition> getUserLevalDefinition(@PathVariable String id) {
        log.debug("REST request to get UserLevalDefinition : {}", id);
        UserLevalDefinition userLevalDefinition = userLevalDefinitionRepository.findOne(id);
        return Optional.ofNullable(userLevalDefinition)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /userLevalDefinitions/:id -> delete the "id" userLevalDefinition.
     */
    @RequestMapping(value = "/userLevalDefinitions/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUserLevalDefinition(@PathVariable String id) {
        log.debug("REST request to delete UserLevalDefinition : {}", id);
        userLevalDefinitionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("userLevalDefinition", id.toString())).build();
    }
}
