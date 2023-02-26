package com.dental.service.impl;

import com.dental.domain.Collection;
import com.dental.repository.CollectionRepository;
import com.dental.service.CollectionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Collection}.
 */
@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    private final Logger log = LoggerFactory.getLogger(CollectionServiceImpl.class);

    private final CollectionRepository collectionRepository;

    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public Collection save(Collection collection) {
        log.debug("Request to save Collection : {}", collection);
        return collectionRepository.save(collection);
    }

    @Override
    public Collection update(Collection collection) {
        log.debug("Request to save Collection : {}", collection);
        return collectionRepository.save(collection);
    }

    @Override
    public Optional<Collection> partialUpdate(Collection collection) {
        log.debug("Request to partially update Collection : {}", collection);

        return collectionRepository
            .findById(collection.getId())
            .map(existingCollection -> {
                if (collection.getShopifyId() != null) {
                    existingCollection.setShopifyId(collection.getShopifyId());
                }
                if (collection.getTitle() != null) {
                    existingCollection.setTitle(collection.getTitle());
                }
                if (collection.getDescription() != null) {
                    existingCollection.setDescription(collection.getDescription());
                }
                if (collection.getHandle() != null) {
                    existingCollection.setHandle(collection.getHandle());
                }

                return existingCollection;
            })
            .map(collectionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Collection> findAll() {
        log.debug("Request to get all Collections");
        return collectionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Collection> findOne(Long id) {
        log.debug("Request to get Collection : {}", id);
        return collectionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Collection : {}", id);
        collectionRepository.deleteById(id);
    }
}
