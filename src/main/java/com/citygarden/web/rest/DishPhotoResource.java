package com.citygarden.web.rest;


import com.citygarden.service.DishPhotoUtilService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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


    /**
     * post  /dishPhoto/:id -> get the "id" dishPhoto.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/dishPhoto")
    public void importDishPhoto(@RequestParam("file") MultipartFile file , @RequestParam String did
                                  ) throws Exception {
        log.info("REST request to import  RuleConditionTemplate");
        String line = null;
        if (!file.isEmpty()) {
            InputStream is = null;
            try {
                is = file.getInputStream();
                dishPhotoUtilService.importPhoto(is, did);
            } catch (IOException e) {
                log.error("读取文件流出错", e);
            } finally {
                if (is != null)
                    is.close();
            }
        }
    }
}
