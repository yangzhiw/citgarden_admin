package com.citygarden.web.rest;

import com.citygarden.Application;
import com.citygarden.domain.RePertoryManager;
import com.citygarden.repository.RePertoryManagerRepository;

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
 * Test class for the RePertoryManagerResource REST controller.
 *
 * @see RePertoryManagerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RePertoryManagerResourceIntTest {


    @Inject
    private RePertoryManagerRepository rePertoryManagerRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRePertoryManagerMockMvc;

    private RePertoryManager rePertoryManager;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RePertoryManagerResource rePertoryManagerResource = new RePertoryManagerResource();
        ReflectionTestUtils.setField(rePertoryManagerResource, "rePertoryManagerRepository", rePertoryManagerRepository);
        this.restRePertoryManagerMockMvc = MockMvcBuilders.standaloneSetup(rePertoryManagerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        rePertoryManagerRepository.deleteAll();
        rePertoryManager = new RePertoryManager();
    }

    @Test
    public void createRePertoryManager() throws Exception {
        int databaseSizeBeforeCreate = rePertoryManagerRepository.findAll().size();

        // Create the RePertoryManager

        restRePertoryManagerMockMvc.perform(post("/api/rePertoryManagers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(rePertoryManager)))
                .andExpect(status().isCreated());

        // Validate the RePertoryManager in the database
        List<RePertoryManager> rePertoryManagers = rePertoryManagerRepository.findAll();
        assertThat(rePertoryManagers).hasSize(databaseSizeBeforeCreate + 1);
        RePertoryManager testRePertoryManager = rePertoryManagers.get(rePertoryManagers.size() - 1);
    }

    @Test
    public void getAllRePertoryManagers() throws Exception {
        // Initialize the database
        rePertoryManagerRepository.save(rePertoryManager);

        // Get all the rePertoryManagers
        restRePertoryManagerMockMvc.perform(get("/api/rePertoryManagers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(rePertoryManager.getId())));
    }

    @Test
    public void getRePertoryManager() throws Exception {
        // Initialize the database
        rePertoryManagerRepository.save(rePertoryManager);

        // Get the rePertoryManager
        restRePertoryManagerMockMvc.perform(get("/api/rePertoryManagers/{id}", rePertoryManager.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(rePertoryManager.getId()));
    }

    @Test
    public void getNonExistingRePertoryManager() throws Exception {
        // Get the rePertoryManager
        restRePertoryManagerMockMvc.perform(get("/api/rePertoryManagers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateRePertoryManager() throws Exception {
        // Initialize the database
        rePertoryManagerRepository.save(rePertoryManager);

		int databaseSizeBeforeUpdate = rePertoryManagerRepository.findAll().size();

        // Update the rePertoryManager

        restRePertoryManagerMockMvc.perform(put("/api/rePertoryManagers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(rePertoryManager)))
                .andExpect(status().isOk());

        // Validate the RePertoryManager in the database
        List<RePertoryManager> rePertoryManagers = rePertoryManagerRepository.findAll();
        assertThat(rePertoryManagers).hasSize(databaseSizeBeforeUpdate);
        RePertoryManager testRePertoryManager = rePertoryManagers.get(rePertoryManagers.size() - 1);
    }

    @Test
    public void deleteRePertoryManager() throws Exception {
        // Initialize the database
        rePertoryManagerRepository.save(rePertoryManager);

		int databaseSizeBeforeDelete = rePertoryManagerRepository.findAll().size();

        // Get the rePertoryManager
        restRePertoryManagerMockMvc.perform(delete("/api/rePertoryManagers/{id}", rePertoryManager.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RePertoryManager> rePertoryManagers = rePertoryManagerRepository.findAll();
        assertThat(rePertoryManagers).hasSize(databaseSizeBeforeDelete - 1);
    }
}
