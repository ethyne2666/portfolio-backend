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
        ContactMessage message = new ContactMessage();
        message.setName(request.getName());
        message.setEmail(request.getEmail());
        message.setMessage(request.getMessage());
        contactMessageRepository.save(message);
    }
}