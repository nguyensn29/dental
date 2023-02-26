package com.dental.service.impl;

import com.dental.domain.CollectionProduct;
import com.dental.repository.CollectionProductRepository;
import com.dental.service.CollectionProductService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CollectionProduct}.
 */
@Service
@Transactional
public class CollectionProductServiceImpl implements CollectionProductService {

    private final Logger log = LoggerFactory.getLogger(CollectionProductServiceImpl.class);

    private final CollectionProductRepository collectionProductRepository;

    public CollectionProductServiceImpl(CollectionProductRepository collectionProductRepository) {
        this.collectionProductRepository = collectionProductRepository;
    }

    @Override
    public CollectionProduct save(CollectionProduct collectionProduct) {
        log.debug("Request to save CollectionProduct : {}", collectionProduct);
        return collectionProductRepository.save(collectionProduct);
    }

    @Override
    public CollectionProduct update(CollectionProduct collectionProduct) {
        log.debug("Request to save CollectionProduct : {}", collectionProduct);
        return collectionProductRepository.save(collectionProduct);
    }

    @Override
    public Optional<CollectionProduct> partialUpdate(CollectionProduct collectionProduct) {
        log.debug("Request to partially update CollectionProduct : {}", collectionProduct);

        return collectionProductRepository
            .findById(collectionProduct.getId())
            .map(existingCollectionProduct -> {
                if (collectionProduct.getCollectionId() != null) {
                    existingCollectionProduct.setCollectionId(collectionProduct.getCollectionId());
                }
                if (collectionProduct.getProductId() != null) {
                    existingCollectionProduct.setProductId(collectionProduct.getProductId());
                }

                return existingCollectionProduct;
            })
            .map(collectionProductRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionProduct> findAll() {
        log.debug("Request to get all CollectionProducts");
        return collectionProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CollectionProduct> findOne(Long id) {
        log.debug("Request to get CollectionProduct : {}", id);
        return collectionProductRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CollectionProduct : {}", id);
        collectionProductRepository.deleteById(id);
    }
}
