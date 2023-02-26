package com.dental.service.impl;

import com.dental.domain.Vendor;
import com.dental.repository.VendorRepository;
import com.dental.service.VendorService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Vendor}.
 */
@Service
@Transactional
public class VendorServiceImpl implements VendorService {

    private final Logger log = LoggerFactory.getLogger(VendorServiceImpl.class);

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor save(Vendor vendor) {
        log.debug("Request to save Vendor : {}", vendor);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor update(Vendor vendor) {
        log.debug("Request to save Vendor : {}", vendor);
        return vendorRepository.save(vendor);
    }

    @Override
    public Optional<Vendor> partialUpdate(Vendor vendor) {
        log.debug("Request to partially update Vendor : {}", vendor);

        return vendorRepository
            .findById(vendor.getId())
            .map(existingVendor -> {
                if (vendor.getName() != null) {
                    existingVendor.setName(vendor.getName());
                }

                return existingVendor;
            })
            .map(vendorRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vendor> findAll() {
        log.debug("Request to get all Vendors");
        return vendorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vendor> findOne(Long id) {
        log.debug("Request to get Vendor : {}", id);
        return vendorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vendor : {}", id);
        vendorRepository.deleteById(id);
    }
}
