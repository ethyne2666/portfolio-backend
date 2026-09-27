package com.charankumar.portfolio.now.controller;

import com.charankumar.portfolio.common.dto.ApiResponse;
import com.charankumar.portfolio.now.dto.NowEntryDto;
import com.charankumar.portfolio.now.service.NowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/now")
public class NowController {

    private final NowService nowService;

    public NowController(NowService nowService) {
        this.nowService = nowService;
    }

    // GET /api/now
    @GetMapping
    public ApiResponse<NowEntryDto> getCurrent() {
        return ApiResponse.success(nowService.getCurrent());
    }
}