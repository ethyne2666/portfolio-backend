package com.charankumar.portfolio.now.repository;

import com.charankumar.portfolio.now.entity.NowEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NowEntryRepository extends JpaRepository<NowEntry, Long> {
    Optional<NowEntry> findByIsCurrentTrue();
}