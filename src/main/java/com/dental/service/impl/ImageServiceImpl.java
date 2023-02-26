package com.dental.service.impl;

import com.dental.domain.Image;
import com.dental.repository.ImageRepository;
import com.dental.service.ImageService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Image}.
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(Image image) {
        log.debug("Request to save Image : {}", image);
        return imageRepository.save(image);
    }

    @Override
    public Image update(Image image) {
        log.debug("Request to save Image : {}", image);
        return imageRepository.save(image);
    }

    @Override
    public Optional<Image> partialUpdate(Image image) {
        log.debug("Request to partially update Image : {}", image);

        return imageRepository
            .findById(image.getId())
            .map(existingImage -> {
                if (image.getSrc() != null) {
                    existingImage.setSrc(image.getSrc());
                }
                if (image.getShopifyId() != null) {
                    existingImage.setShopifyId(image.getShopifyId());
                }
                if (image.getImgPublicId() != null) {
                    existingImage.setImgPublicId(image.getImgPublicId());
                }
                if (image.getIsAvatar() != null) {
                    existingImage.setIsAvatar(image.getIsAvatar());
                }
                if (image.getComboId() != null) {
                    existingImage.setComboId(image.getComboId());
                }
                if (image.getProductId() != null) {
                    existingImage.setProductId(image.getProductId());
                }

                return existingImage;
            })
            .map(imageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> findAll() {
        log.debug("Request to get all Images");
        return imageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Image> findOne(Long id) {
        log.debug("Request to get Image : {}", id);
        return imageRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Image : {}", id);
        imageRepository.deleteById(id);
    }
}
