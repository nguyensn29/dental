package com.dental.service.impl;

import com.dental.domain.ProductUser;
import com.dental.repository.ProductUserRepository;
import com.dental.service.ProductUserService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProductUser}.
 */
@Service
@Transactional
public class ProductUserServiceImpl implements ProductUserService {

    private final Logger log = LoggerFactory.getLogger(ProductUserServiceImpl.class);

    private final ProductUserRepository productUserRepository;

    public ProductUserServiceImpl(ProductUserRepository productUserRepository) {
        this.productUserRepository = productUserRepository;
    }

    @Override
    public ProductUser save(ProductUser productUser) {
        log.debug("Request to save ProductUser : {}", productUser);
        return productUserRepository.save(productUser);
    }

    @Override
    public ProductUser update(ProductUser productUser) {
        log.debug("Request to save ProductUser : {}", productUser);
        return productUserRepository.save(productUser);
    }

    @Override
    public Optional<ProductUser> partialUpdate(ProductUser productUser) {
        log.debug("Request to partially update ProductUser : {}", productUser);

        return productUserRepository
            .findById(productUser.getId())
            .map(existingProductUser -> {
                if (productUser.getUserId() != null) {
                    existingProductUser.setUserId(productUser.getUserId());
                }
                if (productUser.getProductId() != null) {
                    existingProductUser.setProductId(productUser.getProductId());
                }
                if (productUser.getProductType() != null) {
                    existingProductUser.setProductType(productUser.getProductType());
                }

                return existingProductUser;
            })
            .map(productUserRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductUser> findAll() {
        log.debug("Request to get all ProductUsers");
        return productUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductUser> findOne(Long id) {
        log.debug("Request to get ProductUser : {}", id);
        return productUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductUser : {}", id);
        productUserRepository.deleteById(id);
    }
}
