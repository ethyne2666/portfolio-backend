package com.charankumar.portfolio.contact.service;

import com.charankumar.portfolio.contact.dto.ContactRequestDto;

public interface ContactService {
    void submitMessage(ContactRequestDto request);
}