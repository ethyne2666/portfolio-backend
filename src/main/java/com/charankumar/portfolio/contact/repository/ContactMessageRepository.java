package com.charankumar.portfolio.contact.repository;

import com.charankumar.portfolio.contact.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    // No custom queries needed yet — save() and findAll() from JpaRepository are enough
}