package com.citygarden.service;

import com.citygarden.domain.Announcement;
import com.citygarden.repository.AnnouncementRepository;
import com.citygarden.web.rest.dto.AnnouncementDTO;
import com.citygarden.web.rest.mapper.AnnouncementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Announcement.
 */
@Service
public class AnnouncementService {

    private final Logger log = LoggerFactory.getLogger(AnnouncementService.class);
    
    @Inject
    private AnnouncementRepository announcementRepository;
    
    @Inject
    private AnnouncementMapper announcementMapper;
    
    /**
     * Save a announcement.
     * @return the persisted entity
     */
    public AnnouncementDTO save(AnnouncementDTO announcementDTO) {
        log.debug("Request to save Announcement : {}", announcementDTO);
        Announcement announcement = announcementMapper.announcementDTOToAnnouncement(announcementDTO);
        announcement = announcementRepository.save(announcement);
        AnnouncementDTO result = announcementMapper.announcementToAnnouncementDTO(announcement);
        return result;
    }

    /**
     *  get all the announcements.
     *  @return the list of entities
     */
    public List<AnnouncementDTO> findAll() {
        log.debug("Request to get all Announcements");
        List<AnnouncementDTO> result = announcementRepository.findAll().stream()
            .map(announcementMapper::announcementToAnnouncementDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
    }

    /**
     *  get one announcement by id.
     *  @return the entity
     */
    public AnnouncementDTO findOne(String id) {
        log.debug("Request to get Announcement : {}", id);
        Announcement announcement = announcementRepository.findOne(id);
        AnnouncementDTO announcementDTO = announcementMapper.announcementToAnnouncementDTO(announcement);
        return announcementDTO;
    }

    /**
     *  delete the  announcement by id.
     */
    public void delete(String id) {
        log.debug("Request to delete Announcement : {}", id);
        announcementRepository.delete(id);
    }
}
