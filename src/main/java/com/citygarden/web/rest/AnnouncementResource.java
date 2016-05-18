package com.citygarden.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.citygarden.domain.Announcement;
import com.citygarden.service.AnnouncementService;
import com.citygarden.web.rest.util.HeaderUtil;
import com.citygarden.web.rest.dto.AnnouncementDTO;
import com.citygarden.web.rest.mapper.AnnouncementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Announcement.
 */
@RestController
@RequestMapping("/api")
public class AnnouncementResource {

    private final Logger log = LoggerFactory.getLogger(AnnouncementResource.class);
        
    @Inject
    private AnnouncementService announcementService;
    
    @Inject
    private AnnouncementMapper announcementMapper;
    
    /**
     * POST  /announcements -> Create a new announcement.
     */
    @RequestMapping(value = "/announcements",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AnnouncementDTO> createAnnouncement(@RequestBody AnnouncementDTO announcementDTO) throws URISyntaxException {
        log.debug("REST request to save Announcement : {}", announcementDTO);
        if (announcementDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("announcement", "idexists", "A new announcement cannot already have an ID")).body(null);
        }
        AnnouncementDTO result = announcementService.save(announcementDTO);
        return ResponseEntity.created(new URI("/api/announcements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("announcement", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /announcements -> Updates an existing announcement.
     */
    @RequestMapping(value = "/announcements",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AnnouncementDTO> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO) throws URISyntaxException {
        log.debug("REST request to update Announcement : {}", announcementDTO);
        if (announcementDTO.getId() == null) {
            return createAnnouncement(announcementDTO);
        }
        AnnouncementDTO result = announcementService.save(announcementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("announcement", announcementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /announcements -> get all the announcements.
     */
    @RequestMapping(value = "/announcements",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<AnnouncementDTO> getAllAnnouncements() {
        log.debug("REST request to get all Announcements");
        return announcementService.findAll();
            }

    /**
     * GET  /announcements/:id -> get the "id" announcement.
     */
    @RequestMapping(value = "/announcements/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable String id) {
        log.debug("REST request to get Announcement : {}", id);
        AnnouncementDTO announcementDTO = announcementService.findOne(id);
        return Optional.ofNullable(announcementDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /announcements/:id -> delete the "id" announcement.
     */
    @RequestMapping(value = "/announcements/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable String id) {
        log.debug("REST request to delete Announcement : {}", id);
        announcementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("announcement", id.toString())).build();
    }
}
