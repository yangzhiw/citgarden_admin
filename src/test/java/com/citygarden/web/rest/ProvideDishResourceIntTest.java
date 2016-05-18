package com.citygarden.web.rest;

import com.citygarden.Application;
import com.citygarden.domain.ProvideDish;
import com.citygarden.repository.ProvideDishRepository;

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
 * Test class for the ProvideDishResource REST controller.
 *
 * @see ProvideDishResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProvideDishResourceIntTest {


    @Inject
    private ProvideDishRepository provideDishRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProvideDishMockMvc;

    private ProvideDish provideDish;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProvideDishResource provideDishResource = new ProvideDishResource();
        ReflectionTestUtils.setField(provideDishResource, "provideDishRepository", provideDishRepository);
        this.restProvideDishMockMvc = MockMvcBuilders.standaloneSetup(provideDishResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        provideDishRepository.deleteAll();
        provideDish = new ProvideDish();
    }

    @Test
    public void createProvideDish() throws Exception {
        int databaseSizeBeforeCreate = provideDishRepository.findAll().size();

        // Create the ProvideDish

        restProvideDishMockMvc.perform(post("/api/provideDishs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(provideDish)))
                .andExpect(status().isCreated());

        // Validate the ProvideDish in the database
        List<ProvideDish> provideDishs = provideDishRepository.findAll();
        assertThat(provideDishs).hasSize(databaseSizeBeforeCreate + 1);
        ProvideDish testProvideDish = provideDishs.get(provideDishs.size() - 1);
    }

    @Test
    public void getAllProvideDishs() throws Exception {
        // Initialize the database
        provideDishRepository.save(provideDish);

        // Get all the provideDishs
        restProvideDishMockMvc.perform(get("/api/provideDishs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(provideDish.getId())));
    }

    @Test
    public void getProvideDish() throws Exception {
        // Initialize the database
        provideDishRepository.save(provideDish);

        // Get the provideDish
        restProvideDishMockMvc.perform(get("/api/provideDishs/{id}", provideDish.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(provideDish.getId()));
    }

    @Test
    public void getNonExistingProvideDish() throws Exception {
        // Get the provideDish
        restProvideDishMockMvc.perform(get("/api/provideDishs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProvideDish() throws Exception {
        // Initialize the database
        provideDishRepository.save(provideDish);

		int databaseSizeBeforeUpdate = provideDishRepository.findAll().size();

        // Update the provideDish

        restProvideDishMockMvc.perform(put("/api/provideDishs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(provideDish)))
                .andExpect(status().isOk());

        // Validate the ProvideDish in the database
        List<ProvideDish> provideDishs = provideDishRepository.findAll();
        assertThat(provideDishs).hasSize(databaseSizeBeforeUpdate);
        ProvideDish testProvideDish = provideDishs.get(provideDishs.size() - 1);
    }

    @Test
    public void deleteProvideDish() throws Exception {
        // Initialize the database
        provideDishRepository.save(provideDish);

		int databaseSizeBeforeDelete = provideDishRepository.findAll().size();

        // Get the provideDish
        restProvideDishMockMvc.perform(delete("/api/provideDishs/{id}", provideDish.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ProvideDish> provideDishs = provideDishRepository.findAll();
        assertThat(provideDishs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
