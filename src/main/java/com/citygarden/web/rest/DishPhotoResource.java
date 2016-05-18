package com.citygarden.web.rest;


import com.citygarden.service.DishPhotoUtilService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for managing Cart.
 */
@RestController
@RequestMapping("/api")
public class DishPhotoResource {
    private final Logger log = LoggerFactory.getLogger(DishPhotoResource.class);

    @Inject
    private DishPhotoUtilService dishPhotoUtilService;
    /**
     * GET  /dishPhoto/:id -> get the "id" dishPhoto.
     */
    @RequestMapping(value = "/dishPhoto/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Map getPhoto(@PathVariable String name) throws Exception {
        log.debug("REST request to get Cart : {}", name);
        String dishPhoto = dishPhotoUtilService.getDishPhoto(name);
        Map<String,String> map = new HashMap<>();
        map.put("dphoto",dishPhoto);
        return map;
    }

}
