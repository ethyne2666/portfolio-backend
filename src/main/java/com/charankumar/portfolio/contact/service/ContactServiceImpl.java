package com.charankumar.portfolio.contact.service;

import com.charankumar.portfolio.contact.dto.ContactRequestDto;
import com.charankumar.portfolio.contact.entity.ContactMessage;
import com.charankumar.portfolio.contact.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactServiceImpl(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Override
    public void submitMessage(ContactRequestDto request) {
        // Honeypot: a hidden field only bots fill in. Pretend success, store nothing.
        if (request.website() != null && !request.website().isBlank()) {
            return;
        }

        ContactMessage message = new ContactMessage();
        message.setName(request.name().trim());
        message.setEmail(request.email().trim());
        message.setMessage(request.message().trim());
        contactMessageRepository.save(message);
    }
}