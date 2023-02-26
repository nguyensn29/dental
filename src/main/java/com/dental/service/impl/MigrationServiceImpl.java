package com.dental.service.impl;

import com.dental.domain.Migration;
import com.dental.repository.MigrationRepository;
import com.dental.service.MigrationService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Migration}.
 */
@Service
@Transactional
public class MigrationServiceImpl implements MigrationService {

    private final Logger log = LoggerFactory.getLogger(MigrationServiceImpl.class);

    private final MigrationRepository migrationRepository;

    public MigrationServiceImpl(MigrationRepository migrationRepository) {
        this.migrationRepository = migrationRepository;
    }

    @Override
    public Migration save(Migration migration) {
        log.debug("Request to save Migration : {}", migration);
        return migrationRepository.save(migration);
    }

    @Override
    public Migration update(Migration migration) {
        log.debug("Request to save Migration : {}", migration);
        return migrationRepository.save(migration);
    }

    @Override
    public Optional<Migration> partialUpdate(Migration migration) {
        log.debug("Request to partially update Migration : {}", migration);

        return migrationRepository
            .findById(migration.getId())
            .map(existingMigration -> {
                if (migration.getMigration() != null) {
                    existingMigration.setMigration(migration.getMigration());
                }
                if (migration.getBatch() != null) {
                    existingMigration.setBatch(migration.getBatch());
                }

                return existingMigration;
            })
            .map(migrationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Migration> findAll() {
        log.debug("Request to get all Migrations");
        return migrationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Migration> findOne(Long id) {
        log.debug("Request to get Migration : {}", id);
        return migrationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Migration : {}", id);
        migrationRepository.deleteById(id);
    }
}
