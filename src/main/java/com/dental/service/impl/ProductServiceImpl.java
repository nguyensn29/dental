package com.dental.service.impl;

import com.dental.domain.Product;
import com.dental.repository.ProductRepository;
import com.dental.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> partialUpdate(Product product) {
        log.debug("Request to partially update Product : {}", product);

        return productRepository
            .findById(product.getId())
            .map(existingProduct -> {
                if (product.getName() != null) {
                    existingProduct.setName(product.getName());
                }
                if (product.getPoint() != null) {
                    existingProduct.setPoint(product.getPoint());
                }
                if (product.getDescription() != null) {
                    existingProduct.setDescription(product.getDescription());
                }
                if (product.getLiked() != null) {
                    existingProduct.setLiked(product.getLiked());
                }
                if (product.getShopifyId() != null) {
                    existingProduct.setShopifyId(product.getShopifyId());
                }
                if (product.getVendorId() != null) {
                    existingProduct.setVendorId(product.getVendorId());
                }
                if (product.getCategoryId() != null) {
                    existingProduct.setCategoryId(product.getCategoryId());
                }
                if (product.getIsCombo() != null) {
                    existingProduct.setIsCombo(product.getIsCombo());
                }
                if (product.getDiscount() != null) {
                    existingProduct.setDiscount(product.getDiscount());
                }
                if (product.getBought() != null) {
                    existingProduct.setBought(product.getBought());
                }
                if (product.getPrice() != null) {
                    existingProduct.setPrice(product.getPrice());
                }

                return existingProduct;
            })
            .map(productRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        log.debug("Request to get all Products");
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
