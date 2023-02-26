package com.dental.service.impl;

import com.dental.domain.Setting;
import com.dental.repository.SettingRepository;
import com.dental.service.SettingService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Setting}.
 */
@Service
@Transactional
public class SettingServiceImpl implements SettingService {

    private final Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);

    private final SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public Setting save(Setting setting) {
        log.debug("Request to save Setting : {}", setting);
        return settingRepository.save(setting);
    }

    @Override
    public Setting update(Setting setting) {
        log.debug("Request to save Setting : {}", setting);
        return settingRepository.save(setting);
    }

    @Override
    public Optional<Setting> partialUpdate(Setting setting) {
        log.debug("Request to partially update Setting : {}", setting);

        return settingRepository
            .findById(setting.getId())
            .map(existingSetting -> {
                if (setting.getName() != null) {
                    existingSetting.setName(setting.getName());
                }
                if (setting.getKeyName() != null) {
                    existingSetting.setKeyName(setting.getKeyName());
                }
                if (setting.getValue() != null) {
                    existingSetting.setValue(setting.getValue());
                }
                if (setting.getRule() != null) {
                    existingSetting.setRule(setting.getRule());
                }
                if (setting.getIsNumber() != null) {
                    existingSetting.setIsNumber(setting.getIsNumber());
                }
                if (setting.getIsObject() != null) {
                    existingSetting.setIsObject(setting.getIsObject());
                }

                return existingSetting;
            })
            .map(settingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Setting> findAll() {
        log.debug("Request to get all Settings");
        return settingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Setting> findOne(Long id) {
        log.debug("Request to get Setting : {}", id);
        return settingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Setting : {}", id);
        settingRepository.deleteById(id);
    }
}
