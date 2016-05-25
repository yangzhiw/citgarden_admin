package com.citygarden.web.rest;

import com.citygarden.Application;
import com.citygarden.domain.UserLevalDefinition;
import com.citygarden.repository.UserLevalDefinitionRepository;

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
 * Test class for the UserLevalDefinitionResource REST controller.
 *
 * @see UserLevalDefinitionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UserLevalDefinitionResourceIntTest {


    @Inject
    private UserLevalDefinitionRepository userLevalDefinitionRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restUserLevalDefinitionMockMvc;

    private UserLevalDefinition userLevalDefinition;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserLevalDefinitionResource userLevalDefinitionResource = new UserLevalDefinitionResource();
        ReflectionTestUtils.setField(userLevalDefinitionResource, "userLevalDefinitionRepository", userLevalDefinitionRepository);
        this.restUserLevalDefinitionMockMvc = MockMvcBuilders.standaloneSetup(userLevalDefinitionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        userLevalDefinitionRepository.deleteAll();
        userLevalDefinition = new UserLevalDefinition();
    }

    @Test
    public void createUserLevalDefinition() throws Exception {
        int databaseSizeBeforeCreate = userLevalDefinitionRepository.findAll().size();

        // Create the UserLevalDefinition

        restUserLevalDefinitionMockMvc.perform(post("/api/userLevalDefinitions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userLevalDefinition)))
                .andExpect(status().isCreated());

        // Validate the UserLevalDefinition in the database
        List<UserLevalDefinition> userLevalDefinitions = userLevalDefinitionRepository.findAll();
        assertThat(userLevalDefinitions).hasSize(databaseSizeBeforeCreate + 1);
        UserLevalDefinition testUserLevalDefinition = userLevalDefinitions.get(userLevalDefinitions.size() - 1);
    }

    @Test
    public void getAllUserLevalDefinitions() throws Exception {
        // Initialize the database
        userLevalDefinitionRepository.save(userLevalDefinition);

        // Get all the userLevalDefinitions
        restUserLevalDefinitionMockMvc.perform(get("/api/userLevalDefinitions?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(userLevalDefinition.getId())));
    }

    @Test
    public void getUserLevalDefinition() throws Exception {
        // Initialize the database
        userLevalDefinitionRepository.save(userLevalDefinition);

        // Get the userLevalDefinition
        restUserLevalDefinitionMockMvc.perform(get("/api/userLevalDefinitions/{id}", userLevalDefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userLevalDefinition.getId()));
    }

    @Test
    public void getNonExistingUserLevalDefinition() throws Exception {
        // Get the userLevalDefinition
        restUserLevalDefinitionMockMvc.perform(get("/api/userLevalDefinitions/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserLevalDefinition() throws Exception {
        // Initialize the database
        userLevalDefinitionRepository.save(userLevalDefinition);

		int databaseSizeBeforeUpdate = userLevalDefinitionRepository.findAll().size();

        // Update the userLevalDefinition

        restUserLevalDefinitionMockMvc.perform(put("/api/userLevalDefinitions")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userLevalDefinition)))
                .andExpect(status().isOk());

        // Validate the UserLevalDefinition in the database
        List<UserLevalDefinition> userLevalDefinitions = userLevalDefinitionRepository.findAll();
        assertThat(userLevalDefinitions).hasSize(databaseSizeBeforeUpdate);
        UserLevalDefinition testUserLevalDefinition = userLevalDefinitions.get(userLevalDefinitions.size() - 1);
    }

    @Test
    public void deleteUserLevalDefinition() throws Exception {
        // Initialize the database
        userLevalDefinitionRepository.save(userLevalDefinition);

		int databaseSizeBeforeDelete = userLevalDefinitionRepository.findAll().size();

        // Get the userLevalDefinition
        restUserLevalDefinitionMockMvc.perform(delete("/api/userLevalDefinitions/{id}", userLevalDefinition.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<UserLevalDefinition> userLevalDefinitions = userLevalDefinitionRepository.findAll();
        assertThat(userLevalDefinitions).hasSize(databaseSizeBeforeDelete - 1);
    }
}
