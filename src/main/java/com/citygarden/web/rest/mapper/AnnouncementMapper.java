package com.citygarden.web.rest.mapper;

import com.citygarden.domain.*;
import com.citygarden.web.rest.dto.AnnouncementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Announcement and its DTO AnnouncementDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnnouncementMapper {

    AnnouncementDTO announcementToAnnouncementDTO(Announcement announcement);

    Announcement announcementDTOToAnnouncement(AnnouncementDTO announcementDTO);
}
