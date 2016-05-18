package com.citygarden.web.rest.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Announcement entity.
 */
public class AnnouncementDTO implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnnouncementDTO announcementDTO = (AnnouncementDTO) o;

        if ( ! Objects.equals(id, announcementDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AnnouncementDTO{" +
            "id=" + id +
            '}';
    }
}
