package com.dental.service.impl;

import com.dental.domain.Combo;
import com.dental.repository.ComboRepository;
import com.dental.service.ComboService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Combo}.
 */
@Service
@Transactional
public class ComboServiceImpl implements ComboService {

    private final Logger log = LoggerFactory.getLogger(ComboServiceImpl.class);

    private final ComboRepository comboRepository;

    public ComboServiceImpl(ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    @Override
    public Combo save(Combo combo) {
        log.debug("Request to save Combo : {}", combo);
        return comboRepository.save(combo);
    }

    @Override
    public Combo update(Combo combo) {
        log.debug("Request to save Combo : {}", combo);
        return comboRepository.save(combo);
    }

    @Override
    public Optional<Combo> partialUpdate(Combo combo) {
        log.debug("Request to partially update Combo : {}", combo);

        return comboRepository
            .findById(combo.getId())
            .map(existingCombo -> {
                if (combo.getName() != null) {
                    existingCombo.setName(combo.getName());
                }
                if (combo.getPrice() != null) {
                    existingCombo.setPrice(combo.getPrice());
                }
                if (combo.getDiscount() != null) {
                    existingCombo.setDiscount(combo.getDiscount());
                }
                if (combo.getWeight() != null) {
                    existingCombo.setWeight(combo.getWeight());
                }
                if (combo.getPoint() != null) {
                    existingCombo.setPoint(combo.getPoint());
                }
                if (combo.getDescription() != null) {
                    existingCombo.setDescription(combo.getDescription());
                }
                if (combo.getLiked() != null) {
                    existingCombo.setLiked(combo.getLiked());
                }

                return existingCombo;
            })
            .map(comboRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Combo> findAll() {
        log.debug("Request to get all Combos");
        return comboRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Combo> findOne(Long id) {
        log.debug("Request to get Combo : {}", id);
        return comboRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Combo : {}", id);
        comboRepository.deleteById(id);
    }
}
