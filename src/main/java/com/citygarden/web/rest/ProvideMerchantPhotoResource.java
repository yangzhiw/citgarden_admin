package com.citygarden.web.rest;

import com.citygarden.service.DishPhotoUtilService;
import com.citygarden.service.ProvideMerchantPhotoService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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
    @RequestMapping(value = "/providePhoto/{id}",
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



      /**
     * post  /dishPhoto/:id -> get the "id" dishPhoto.
     */
      @ResponseStatus(HttpStatus.OK)
      @RequestMapping(value="/providePhoto")
      public void importProvidePhoto(@RequestParam("file") MultipartFile file , @RequestParam String pid,
                                     HttpServletResponse httpServletResponse) throws Exception {
          log.info("REST request to import  RuleConditionTemplate");
          String line = null;
          if (!file.isEmpty()) {
              InputStream is = null;
              try {
                  is = file.getInputStream();
                  provideMerchantPhotoService.importPhoto(is,pid);
              } catch (IOException e) {
                  log.error("读取文件流出错", e);
              } finally {
                  if (is != null)
                      is.close();
              }
          }
      }


}
