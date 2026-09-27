package com.charankumar.portfolio.contact.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.contact.dto.ContactRequestDto;
import com.charankumar.portfolio.contact.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // POST /api/contact
    // @Valid triggers the checks in ContactRequestDto BEFORE this method body runs.
    // If validation fails, Spring throws MethodArgumentNotValidException automatically.
    @PostMapping
    public ApiResponse<String> submit(@Valid @RequestBody ContactRequestDto request) {
        contactService.submitMessage(request);
        return ApiResponse.success("Message received. Thanks for reaching out!");
    }
}