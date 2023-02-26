package com.dental.service.impl;

import com.dental.domain.UserPayment;
import com.dental.repository.UserPaymentRepository;
import com.dental.service.UserPaymentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserPayment}.
 */
@Service
@Transactional
public class UserPaymentServiceImpl implements UserPaymentService {

    private final Logger log = LoggerFactory.getLogger(UserPaymentServiceImpl.class);

    private final UserPaymentRepository userPaymentRepository;

    public UserPaymentServiceImpl(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    public UserPayment save(UserPayment userPayment) {
        log.debug("Request to save UserPayment : {}", userPayment);
        return userPaymentRepository.save(userPayment);
    }

    @Override
    public UserPayment update(UserPayment userPayment) {
        log.debug("Request to save UserPayment : {}", userPayment);
        return userPaymentRepository.save(userPayment);
    }

    @Override
    public Optional<UserPayment> partialUpdate(UserPayment userPayment) {
        log.debug("Request to partially update UserPayment : {}", userPayment);

        return userPaymentRepository
            .findById(userPayment.getId())
            .map(existingUserPayment -> {
                if (userPayment.getUserId() != null) {
                    existingUserPayment.setUserId(userPayment.getUserId());
                }
                if (userPayment.getName() != null) {
                    existingUserPayment.setName(userPayment.getName());
                }
                if (userPayment.getPhone() != null) {
                    existingUserPayment.setPhone(userPayment.getPhone());
                }
                if (userPayment.getAdress() != null) {
                    existingUserPayment.setAdress(userPayment.getAdress());
                }
                if (userPayment.getProvincial() != null) {
                    existingUserPayment.setProvincial(userPayment.getProvincial());
                }
                if (userPayment.getDistrict() != null) {
                    existingUserPayment.setDistrict(userPayment.getDistrict());
                }
                if (userPayment.getWard() != null) {
                    existingUserPayment.setWard(userPayment.getWard());
                }
                if (userPayment.getEmail() != null) {
                    existingUserPayment.setEmail(userPayment.getEmail());
                }
                if (userPayment.getStreet() != null) {
                    existingUserPayment.setStreet(userPayment.getStreet());
                }

                return existingUserPayment;
            })
            .map(userPaymentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPayment> findAll() {
        log.debug("Request to get all UserPayments");
        return userPaymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserPayment> findOne(Long id) {
        log.debug("Request to get UserPayment : {}", id);
        return userPaymentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserPayment : {}", id);
        userPaymentRepository.deleteById(id);
    }
}
