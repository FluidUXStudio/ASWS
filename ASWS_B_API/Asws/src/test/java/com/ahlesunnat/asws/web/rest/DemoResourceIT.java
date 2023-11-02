package com.ahlesunnat.asws.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ahlesunnat.asws.IntegrationTest;
import com.ahlesunnat.asws.domain.Demo;
import com.ahlesunnat.asws.repository.DemoRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DemoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DemoResourceIT {

    private static final String DEFAULT_TETING = "AAAAAAAAAA";
    private static final String UPDATED_TETING = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/demos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDemoMockMvc;

    private Demo demo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demo createEntity(EntityManager em) {
        Demo demo = new Demo().teting(DEFAULT_TETING);
        return demo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Demo createUpdatedEntity(EntityManager em) {
        Demo demo = new Demo().teting(UPDATED_TETING);
        return demo;
    }

    @BeforeEach
    public void initTest() {
        demo = createEntity(em);
    }

    @Test
    @Transactional
    void createDemo() throws Exception {
        int databaseSizeBeforeCreate = demoRepository.findAll().size();
        // Create the Demo
        restDemoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demo)))
            .andExpect(status().isCreated());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeCreate + 1);
        Demo testDemo = demoList.get(demoList.size() - 1);
        assertThat(testDemo.getTeting()).isEqualTo(DEFAULT_TETING);
    }

    @Test
    @Transactional
    void createDemoWithExistingId() throws Exception {
        // Create the Demo with an existing ID
        demo.setId(1L);

        int databaseSizeBeforeCreate = demoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demo)))
            .andExpect(status().isBadRequest());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDemos() throws Exception {
        // Initialize the database
        demoRepository.saveAndFlush(demo);

        // Get all the demoList
        restDemoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demo.getId().intValue())))
            .andExpect(jsonPath("$.[*].teting").value(hasItem(DEFAULT_TETING)));
    }

    @Test
    @Transactional
    void getDemo() throws Exception {
        // Initialize the database
        demoRepository.saveAndFlush(demo);

        // Get the demo
        restDemoMockMvc
            .perform(get(ENTITY_API_URL_ID, demo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(demo.getId().intValue()))
            .andExpect(jsonPath("$.teting").value(DEFAULT_TETING));
    }

    @Test
    @Transactional
    void getNonExistingDemo() throws Exception {
        // Get the demo
        restDemoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDemo() throws Exception {
        // Initialize the database
        demoRepository.saveAndFlush(demo);

        int databaseSizeBeforeUpdate = demoRepository.findAll().size();

        // Update the demo
        Demo updatedDemo = demoRepository.findById(demo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDemo are not directly saved in db
        em.detach(updatedDemo);
        updatedDemo.teting(UPDATED_TETING);

        restDemoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDemo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDemo))
            )
            .andExpect(status().isOk());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
        Demo testDemo = demoList.get(demoList.size() - 1);
        assertThat(testDemo.getTeting()).isEqualTo(UPDATED_TETING);
    }

    @Test
    @Transactional
    void putNonExistingDemo() throws Exception {
        int databaseSizeBeforeUpdate = demoRepository.findAll().size();
        demo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, demo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDemo() throws Exception {
        int databaseSizeBeforeUpdate = demoRepository.findAll().size();
        demo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(demo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDemo() throws Exception {
        int databaseSizeBeforeUpdate = demoRepository.findAll().size();
        demo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(demo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDemoWithPatch() throws Exception {
        // Initialize the database
        demoRepository.saveAndFlush(demo);

        int databaseSizeBeforeUpdate = demoRepository.findAll().size();

        // Update the demo using partial update
        Demo partialUpdatedDemo = new Demo();
        partialUpdatedDemo.setId(demo.getId());

        partialUpdatedDemo.teting(UPDATED_TETING);

        restDemoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemo))
            )
            .andExpect(status().isOk());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
        Demo testDemo = demoList.get(demoList.size() - 1);
        assertThat(testDemo.getTeting()).isEqualTo(UPDATED_TETING);
    }

    @Test
    @Transactional
    void fullUpdateDemoWithPatch() throws Exception {
        // Initialize the database
        demoRepository.saveAndFlush(demo);

        int databaseSizeBeforeUpdate = demoRepository.findAll().size();

        // Update the demo using partial update
        Demo partialUpdatedDemo = new Demo();
        partialUpdatedDemo.setId(demo.getId());

        partialUpdatedDemo.teting(UPDATED_TETING);

        restDemoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDemo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDemo))
            )
            .andExpect(status().isOk());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
        Demo testDemo = demoList.get(demoList.size() - 1);
        assertThat(testDemo.getTeting()).isEqualTo(UPDATED_TETING);
    }

    @Test
    @Transactional
    void patchNonExistingDemo() throws Exception {
        int databaseSizeBeforeUpdate = demoRepository.findAll().size();
        demo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, demo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDemo() throws Exception {
        int databaseSizeBeforeUpdate = demoRepository.findAll().size();
        demo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(demo))
            )
            .andExpect(status().isBadRequest());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDemo() throws Exception {
        int databaseSizeBeforeUpdate = demoRepository.findAll().size();
        demo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDemoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(demo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Demo in the database
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDemo() throws Exception {
        // Initialize the database
        demoRepository.saveAndFlush(demo);

        int databaseSizeBeforeDelete = demoRepository.findAll().size();

        // Delete the demo
        restDemoMockMvc
            .perform(delete(ENTITY_API_URL_ID, demo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Demo> demoList = demoRepository.findAll();
        assertThat(demoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
