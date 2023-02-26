package com.dental.service.impl;

import com.dental.domain.Rank;
import com.dental.repository.RankRepository;
import com.dental.service.RankService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Rank}.
 */
@Service
@Transactional
public class RankServiceImpl implements RankService {

    private final Logger log = LoggerFactory.getLogger(RankServiceImpl.class);

    private final RankRepository rankRepository;

    public RankServiceImpl(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    @Override
    public Rank save(Rank rank) {
        log.debug("Request to save Rank : {}", rank);
        return rankRepository.save(rank);
    }

    @Override
    public Rank update(Rank rank) {
        log.debug("Request to save Rank : {}", rank);
        return rankRepository.save(rank);
    }

    @Override
    public Optional<Rank> partialUpdate(Rank rank) {
        log.debug("Request to partially update Rank : {}", rank);

        return rankRepository
            .findById(rank.getId())
            .map(existingRank -> {
                if (rank.getName() != null) {
                    existingRank.setName(rank.getName());
                }
                if (rank.getTurnoverCondition() != null) {
                    existingRank.setTurnoverCondition(rank.getTurnoverCondition());
                }
                if (rank.getDiscount() != null) {
                    existingRank.setDiscount(rank.getDiscount());
                }

                return existingRank;
            })
            .map(rankRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rank> findAll() {
        log.debug("Request to get all Ranks");
        return rankRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rank> findOne(Long id) {
        log.debug("Request to get Rank : {}", id);
        return rankRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Rank : {}", id);
        rankRepository.deleteById(id);
    }
}
