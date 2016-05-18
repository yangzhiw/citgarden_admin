package com.citygarden.web.rest;

import com.citygarden.service.DishPhotoUtilService;
import com.citygarden.service.ProvideMerchantPhotoService;
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
 * Created by yzw on 2016/5/18 0018.
 */

@RestController
@RequestMapping("/api")
public class ProvideMerchantPhotoResource {

    private final Logger log = LoggerFactory.getLogger(ProvideMerchantPhotoResource.class);

    @Inject
    private ProvideMerchantPhotoService provideMerchantPhotoService;
    /**
     * GET  /dishPhoto/:id -> get the "id" dishPhoto.
     */
    @RequestMapping(value = "/dishPhoto/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Map getphoto(@PathVariable String name) throws Exception {
        log.debug("REST request to get Cart : {}", name);
        String providePhoto = provideMerchantPhotoService.getProvidePhoto(name);
        Map<String,String> map = new HashMap<>();
        map.put("pphoto",providePhoto);
        return map;
    }
}
