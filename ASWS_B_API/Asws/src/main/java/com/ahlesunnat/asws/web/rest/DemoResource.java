package com.ahlesunnat.asws.web.rest;

import com.ahlesunnat.asws.domain.Demo;
import com.ahlesunnat.asws.repository.DemoRepository;
import com.ahlesunnat.asws.service.DemoService;
import com.ahlesunnat.asws.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ahlesunnat.asws.domain.Demo}.
 */
@RestController
@RequestMapping("/api")
public class DemoResource {

    private final Logger log = LoggerFactory.getLogger(DemoResource.class);

    private static final String ENTITY_NAME = "aswsDemo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemoService demoService;

    private final DemoRepository demoRepository;

    public DemoResource(DemoService demoService, DemoRepository demoRepository) {
        this.demoService = demoService;
        this.demoRepository = demoRepository;
    }

    /**
     * {@code POST  /demos} : Create a new demo.
     *
     * @param demo the demo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new demo, or with status {@code 400 (Bad Request)} if the demo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/demos")
    public ResponseEntity<Demo> createDemo(@RequestBody Demo demo) throws URISyntaxException {
        log.debug("REST request to save Demo : {}", demo);
        if (demo.getId() != null) {
            throw new BadRequestAlertException("A new demo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demo result = demoService.save(demo);
        return ResponseEntity
            .created(new URI("/api/demos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /demos/:id} : Updates an existing demo.
     *
     * @param id the id of the demo to save.
     * @param demo the demo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demo,
     * or with status {@code 400 (Bad Request)} if the demo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the demo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/demos/{id}")
    public ResponseEntity<Demo> updateDemo(@PathVariable(value = "id", required = false) final Long id, @RequestBody Demo demo)
        throws URISyntaxException {
        log.debug("REST request to update Demo : {}, {}", id, demo);
        if (demo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, demo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!demoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Demo result = demoService.update(demo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /demos/:id} : Partial updates given fields of an existing demo, field will ignore if it is null
     *
     * @param id the id of the demo to save.
     * @param demo the demo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated demo,
     * or with status {@code 400 (Bad Request)} if the demo is not valid,
     * or with status {@code 404 (Not Found)} if the demo is not found,
     * or with status {@code 500 (Internal Server Error)} if the demo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/demos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Demo> partialUpdateDemo(@PathVariable(value = "id", required = false) final Long id, @RequestBody Demo demo)
        throws URISyntaxException {
        log.debug("REST request to partial update Demo partially : {}, {}", id, demo);
        if (demo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, demo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!demoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Demo> result = demoService.partialUpdate(demo);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, demo.getId().toString())
        );
    }

    /**
     * {@code GET  /demos} : get all the demos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of demos in body.
     */
    @GetMapping("/demos")
    public List<Demo> getAllDemos() {
        log.debug("REST request to get all Demos");
        return demoService.findAll();
    }

    /**
     * {@code GET  /demos/:id} : get the "id" demo.
     *
     * @param id the id of the demo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the demo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/demos/{id}")
    public ResponseEntity<Demo> getDemo(@PathVariable Long id) {
        log.debug("REST request to get Demo : {}", id);
        Optional<Demo> demo = demoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demo);
    }

    /**
     * {@code DELETE  /demos/:id} : delete the "id" demo.
     *
     * @param id the id of the demo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/demos/{id}")
    public ResponseEntity<Void> deleteDemo(@PathVariable Long id) {
        log.debug("REST request to delete Demo : {}", id);
        demoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
