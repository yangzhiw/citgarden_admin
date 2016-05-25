package com.citygarden.web.rest;

import com.citygarden.Application;
import com.citygarden.domain.ProfitReports;
import com.citygarden.repository.ProfitReportsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ProfitReportsResource REST controller.
 *
 * @see ProfitReportsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProfitReportsResourceIntTest {


    @Inject
    private ProfitReportsRepository profitReportsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProfitReportsMockMvc;

    private ProfitReports profitReports;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProfitReportsResource profitReportsResource = new ProfitReportsResource();
        ReflectionTestUtils.setField(profitReportsResource, "profitReportsRepository", profitReportsRepository);
        this.restProfitReportsMockMvc = MockMvcBuilders.standaloneSetup(profitReportsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        profitReportsRepository.deleteAll();
        profitReports = new ProfitReports();
    }

    @Test
    public void createProfitReports() throws Exception {
        int databaseSizeBeforeCreate = profitReportsRepository.findAll().size();

        // Create the ProfitReports

        restProfitReportsMockMvc.perform(post("/api/profitReportss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(profitReports)))
                .andExpect(status().isCreated());

        // Validate the ProfitReports in the database
        List<ProfitReports> profitReportss = profitReportsRepository.findAll();
        assertThat(profitReportss).hasSize(databaseSizeBeforeCreate + 1);
        ProfitReports testProfitReports = profitReportss.get(profitReportss.size() - 1);
    }

    @Test
    public void getAllProfitReportss() throws Exception {
        // Initialize the database
        profitReportsRepository.save(profitReports);

        // Get all the profitReportss
        restProfitReportsMockMvc.perform(get("/api/profitReportss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(profitReports.getId())));
    }

    @Test
    public void getProfitReports() throws Exception {
        // Initialize the database
        profitReportsRepository.save(profitReports);

        // Get the profitReports
        restProfitReportsMockMvc.perform(get("/api/profitReportss/{id}", profitReports.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(profitReports.getId()));
    }

    @Test
    public void getNonExistingProfitReports() throws Exception {
        // Get the profitReports
        restProfitReportsMockMvc.perform(get("/api/profitReportss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProfitReports() throws Exception {
        // Initialize the database
        profitReportsRepository.save(profitReports);

		int databaseSizeBeforeUpdate = profitReportsRepository.findAll().size();

        // Update the profitReports

        restProfitReportsMockMvc.perform(put("/api/profitReportss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(profitReports)))
                .andExpect(status().isOk());

        // Validate the ProfitReports in the database
        List<ProfitReports> profitReportss = profitReportsRepository.findAll();
        assertThat(profitReportss).hasSize(databaseSizeBeforeUpdate);
        ProfitReports testProfitReports = profitReportss.get(profitReportss.size() - 1);
    }

    @Test
    public void deleteProfitReports() throws Exception {
        // Initialize the database
        profitReportsRepository.save(profitReports);

		int databaseSizeBeforeDelete = profitReportsRepository.findAll().size();

        // Get the profitReports
        restProfitReportsMockMvc.perform(delete("/api/profitReportss/{id}", profitReports.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ProfitReports> profitReportss = profitReportsRepository.findAll();
        assertThat(profitReportss).hasSize(databaseSizeBeforeDelete - 1);
    }
}
