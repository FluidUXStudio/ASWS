package com.ahlesunnat.asws.service.impl;

import com.ahlesunnat.asws.domain.Demo;
import com.ahlesunnat.asws.repository.DemoRepository;
import com.ahlesunnat.asws.service.DemoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Demo}.
 */
@Service
@Transactional
public class DemoServiceImpl implements DemoService {

    private final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    private final DemoRepository demoRepository;

    public DemoServiceImpl(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public Demo save(Demo demo) {
        log.debug("Request to save Demo : {}", demo);
        return demoRepository.save(demo);
    }

    @Override
    public Demo update(Demo demo) {
        log.debug("Request to update Demo : {}", demo);
        return demoRepository.save(demo);
    }

    @Override
    public Optional<Demo> partialUpdate(Demo demo) {
        log.debug("Request to partially update Demo : {}", demo);

        return demoRepository
            .findById(demo.getId())
            .map(existingDemo -> {
                if (demo.getTeting() != null) {
                    existingDemo.setTeting(demo.getTeting());
                }

                return existingDemo;
            })
            .map(demoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Demo> findAll() {
        log.debug("Request to get all Demos");
        return demoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Demo> findOne(Long id) {
        log.debug("Request to get Demo : {}", id);
        return demoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demo : {}", id);
        demoRepository.deleteById(id);
    }
}
