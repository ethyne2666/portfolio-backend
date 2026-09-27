package com.charankumar.portfolio.now.service;

import com.charankumar.portfolio.common.exception.ResourceNotFoundException;
import com.charankumar.portfolio.now.entity.NowEntry;
import com.charankumar.portfolio.now.dto.NowEntryDto;
import com.charankumar.portfolio.now.repository.NowEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class NowServiceImpl implements NowService {

    private final NowEntryRepository nowEntryRepository;

    public NowServiceImpl(NowEntryRepository nowEntryRepository) {
        this.nowEntryRepository = nowEntryRepository;
    }

    @Override
    public NowEntryDto getCurrent() {
        NowEntry entry = nowEntryRepository.findByIsCurrentTrue()
                .orElseThrow(() -> new ResourceNotFoundException("No current entry set yet"));
        return new NowEntryDto(entry.getContent(), entry.getUpdatedAt());
    }
}