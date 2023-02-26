package com.dental.service.impl;

import com.dental.domain.Variant;
import com.dental.repository.VariantRepository;
import com.dental.service.VariantService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Variant}.
 */
@Service
@Transactional
public class VariantServiceImpl implements VariantService {

    private final Logger log = LoggerFactory.getLogger(VariantServiceImpl.class);

    private final VariantRepository variantRepository;

    public VariantServiceImpl(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;
    }

    @Override
    public Variant save(Variant variant) {
        log.debug("Request to save Variant : {}", variant);
        return variantRepository.save(variant);
    }

    @Override
    public Variant update(Variant variant) {
        log.debug("Request to save Variant : {}", variant);
        return variantRepository.save(variant);
    }

    @Override
    public Optional<Variant> partialUpdate(Variant variant) {
        log.debug("Request to partially update Variant : {}", variant);

        return variantRepository
            .findById(variant.getId())
            .map(existingVariant -> {
                if (variant.getProductId() != null) {
                    existingVariant.setProductId(variant.getProductId());
                }
                if (variant.getShopifyId() != null) {
                    existingVariant.setShopifyId(variant.getShopifyId());
                }
                if (variant.getTitle() != null) {
                    existingVariant.setTitle(variant.getTitle());
                }
                if (variant.getPrice() != null) {
                    existingVariant.setPrice(variant.getPrice());
                }
                if (variant.getDiscount() != null) {
                    existingVariant.setDiscount(variant.getDiscount());
                }
                if (variant.getOption1() != null) {
                    existingVariant.setOption1(variant.getOption1());
                }
                if (variant.getOption2() != null) {
                    existingVariant.setOption2(variant.getOption2());
                }
                if (variant.getOption3() != null) {
                    existingVariant.setOption3(variant.getOption3());
                }
                if (variant.getImageId() != null) {
                    existingVariant.setImageId(variant.getImageId());
                }
                if (variant.getWeight() != null) {
                    existingVariant.setWeight(variant.getWeight());
                }
                if (variant.getCompareAtPrice() != null) {
                    existingVariant.setCompareAtPrice(variant.getCompareAtPrice());
                }

                return existingVariant;
            })
            .map(variantRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Variant> findAll() {
        log.debug("Request to get all Variants");
        return variantRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Variant> findOne(Long id) {
        log.debug("Request to get Variant : {}", id);
        return variantRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Variant : {}", id);
        variantRepository.deleteById(id);
    }
}
