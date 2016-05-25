package com.citygarden.web.rest;

import com.citygarden.web.rest.dto.ProfitReportsDTO;
import com.codahale.metrics.annotation.Timed;
import com.citygarden.domain.ProfitReports;
import com.citygarden.repository.ProfitReportsRepository;
import com.citygarden.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProfitReports.
 */
@RestController
@RequestMapping("/api")
public class ProfitReportsResource {

    private final Logger log = LoggerFactory.getLogger(ProfitReportsResource.class);

    @Inject
    private ProfitReportsRepository profitReportsRepository;

    /**
     * POST  /profitReportss -> Create a new profitReports.
     */
    @RequestMapping(value = "/profitReportss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfitReports> createProfitReports(@RequestBody ProfitReports profitReports) throws URISyntaxException {
        log.debug("REST request to save ProfitReports : {}", profitReports);
        if (profitReports.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("profitReports", "idexists", "A new profitReports cannot already have an ID")).body(null);
        }
        ProfitReports result = profitReportsRepository.save(profitReports);
        return ResponseEntity.created(new URI("/api/profitReportss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("profitReports", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profitReportss -> Updates an existing profitReports.
     */
    @RequestMapping(value = "/profitReportss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfitReports> updateProfitReports(@RequestBody ProfitReports profitReports) throws URISyntaxException {
        log.debug("REST request to update ProfitReports : {}", profitReports);
        if (profitReports.getId() == null) {
            return createProfitReports(profitReports);
        }
        ProfitReports result = profitReportsRepository.save(profitReports);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("profitReports", profitReports.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profitReportss -> get all the profitReportss.
     */
    @RequestMapping(value = "/profitReportss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ProfitReportsDTO> getAllProfitReportss() {
        log.debug("REST request to get all ProfitReportss");
        List<ProfitReports> profitReportses =  profitReportsRepository.findAll();
        List<ProfitReportsDTO> profitReportsDTOs = new ArrayList<>(0);
        profitReportses.forEach(x->{
            ProfitReportsDTO y = new ProfitReportsDTO();
            y.setId(x.getId());
            y.setDish(x.getDish());
            y.setDishId(x.getDishId());
            y.setSaleCount(x.getSaleCount());
            y.setOrginalPrice(x.getOrginalPrice());
            y.setSalePrice(x.getSalePrice());;
            y.setSaleTotalPrice(x.getSaleTotalPrice());

            y.setInputTotalPrice(x.getOrginalPrice() * x.getSaleCount());
            y.setTotalProfit((x.getSaleTotalPrice()) - (x.getOrginalPrice() * x.getSaleCount()));

            profitReportsDTOs.add(y);
        });
        return  profitReportsDTOs;
      }

    /**
     * GET  /profitReportss/:id -> get the "id" profitReports.
     */
    @RequestMapping(value = "/profitReportss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProfitReports> getProfitReports(@PathVariable String id) {
        log.debug("REST request to get ProfitReports : {}", id);
        ProfitReports profitReports = profitReportsRepository.findOne(id);
        return Optional.ofNullable(profitReports)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /profitReportss/:id -> delete the "id" profitReports.
     */
    @RequestMapping(value = "/profitReportss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProfitReports(@PathVariable String id) {
        log.debug("REST request to delete ProfitReports : {}", id);
        profitReportsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("profitReports", id.toString())).build();
    }
}
